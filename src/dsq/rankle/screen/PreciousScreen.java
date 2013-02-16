package dsq.rankle.screen;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import dsq.rankle.R;
import dsq.rankle.data.precious.PreciousV;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.precious.DefaultPreciousDbAdapter;
import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.rankle.db.precious.PreciousTable;
import dsq.rankle.ui.dialog.DialogConstants;
import dsq.rankle.ui.precious.DefaultPreciousListDefinition;
import dsq.rankle.ui.precious.PreciousListDefinition;
import dsq.sycophant.action.IdAction;
import dsq.sycophant.action.ItemAction;
import dsq.sycophant.action.SimpleAction;
import dsq.sycophant.action.SingleAction;
import dsq.sycophant.datalist.DefaultSelectableDataList;
import dsq.sycophant.datalist.SelectableDataList;
import dsq.sycophant.store.DefaultIdStore;
import dsq.sycophant.store.IdStore;
import dsq.sycophant.ui.button.Buttons;
import dsq.sycophant.ui.button.DefaultButtons;
import dsq.sycophant.ui.commandbar.Commandbar;
import dsq.sycophant.ui.commandbar.DefaultCommandbar;
import dsq.sycophant.ui.dialog.DefaultDialogs;
import dsq.sycophant.ui.dialog.Dialogs;

import java.util.HashMap;
import java.util.Map;

public class PreciousScreen extends ListActivity
{

    private final DbLifecycle lifecycle = new DefaultDbLifecycle();

    private IdStore cid = new DefaultIdStore();
    private PreciousDbAdapter adapter;
    private final Dialogs dialogs = new DefaultDialogs();
    private SelectableDataList<PreciousV> list;

    private SingleAction<PreciousV> refreshList = new SingleAction<PreciousV>() {
        @Override
        public void run(final PreciousV preciousV) {
            list.refresh();
        }
    };
    private Commandbar commands;
    private Buttons buttons;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precious_screen);
        adapter = getAdapter();

        final PreciousListDefinition definition = new DefaultPreciousListDefinition();
        list = new DefaultSelectableDataList<PreciousV>(this, adapter, definition, R.layout.precious_list_row);
        list.onSelect(new ItemAction<PreciousV>() {
            @Override
            public void run(final long id, final PreciousV v) {
                Log.v("RANKLE", "Selecting: " + id);
                cid.set(id);
                commands.update();
            }
        });
        list.refresh();

        final Map<Integer, IdAction> actions = new HashMap<Integer, IdAction>();
        actions.put(R.id.precious_screen_edit, new IdAction() {
            @Override
            public void run(final long id) {
                showDialogFor(id, DialogConstants.RENAME_PRECIOUS_DIALOG, DialogConstants.RENAME_PRECIOUS);

            }
        });
        actions.put(R.id.precious_screen_delete, new IdAction() {
            @Override
            public void run(final long id) {
                adapter.deleteById(id);
                list.refresh();
            }
        });
        commands = new DefaultCommandbar(this, actions, cid);
        commands.register();

        final Map<Integer, SimpleAction> buttonActions = new HashMap<Integer, SimpleAction>();
        buttonActions.put(R.id.precious_screen_add, new SimpleAction() {
            @Override
            public void run() {
                displayDialog(DialogConstants.ADD_PRECIOUS);
            }
        });
        buttons = new DefaultButtons(this, buttonActions);
        buttons.register();
    }

    private void showDialogFor(final long id, final String dialogTag, final int dialogId) {
        Intent intent = new Intent();
        intent.putExtra(dialogTag, id);
        setIntent(intent);
        displayDialog(dialogId);
    }

    private void displayDialog(final int dialogId) {
        removeDialog(dialogId);
        showDialog(dialogId);
    }

    private PreciousDbAdapter getAdapter() {
        final SQLiteDatabase db = lifecycle.open(this);
        return new DefaultPreciousDbAdapter(db);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id) {
            case DialogConstants.ADD_PRECIOUS: {
                return dialogs.create(this, adapter, "Precious: ", PreciousTable.NAME, refreshList);
            }
            case DialogConstants.RENAME_PRECIOUS: {
                return dialogs.update(this, adapter, "Rename: ", DialogConstants.RENAME_PRECIOUS_DIALOG, PreciousTable.NAME, refreshList);
            }
            default: {
                break;
            }
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }


    /*
    /**
     * Called when the activity is first created.
     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.riposte_list);
//
//        final SQLiteDatabase db = lifecycle.open(this);
//        final Rabbit<RiposteV> rabbit = new DefaultRabbit<RiposteV>();
//        final DefaultActionFactory factory = new DefaultActionFactory();
//        actions = factory.nu(this, db, rabbit);
//
//        options = setupOptions();
//        responses = setupResponses();
//
//        commands = setupCommands();
//        buttons = setupButtons();
//
////        registerForContextMenu(getListView());
//
//        final ButtonIcon toggleButton = commands.get(R.id.command_toggle);
//
//        final ItemAction<RiposteV> onSelect = new ItemAction<RiposteV>() {
//            public void run(final long id, final RiposteV v) {
//
//                cid.set(v.id);
//                Log.v("ERSATZ", String.valueOf(id));
//                commands.update();
//
//                toggleButton.setImages(v.enabled ? new OnButtonImages() : new OffButtonImages());
//            }
//        };
//
//        toggleButton.setImages(new OffButtonImages());
//        rabbit.getList().onSelect(onSelect);
//        commands.update();
//
//        commands.register();
//        buttons.register();
//    }
//
//    private Buttons setupButtons() {
//        final Map<Integer, SimpleAction> mapping = new HashMap<Integer, SimpleAction>();
//        mapping.put(R.id.new_riposte, actions.launchAdd());
//        return new DefaultButtons(this, mapping);
//
//    }
//
//    private Commandbar setupCommands() {
//        final Map<Integer, IdAction> mapping = new HashMap<Integer, IdAction>();
//        mapping.put(R.id.command_toggle, actions.toggleEnabled());
//        mapping.put(R.id.command_edit, actions.launchEdit());
//        mapping.put(R.id.command_delete, actions.delete());
//        return new DefaultCommandbar(this, mapping, cid);
//    }
//
//    private Options setupOptions() {
//        final Map<Integer, SimpleAction> mapping = new HashMap<Integer, SimpleAction>();
//        mapping.put(R.id.settings, actions.launchSettings());
//        mapping.put(R.id.hack, new SimpleAction() {
//            public void run() {
//                final Intent intent = new Intent(LocationReceiver.LOCATION_UPDATE);
//                intent.putExtra("formatted time", new Date(System.currentTimeMillis()).toString());
//                new DefaultLocationIntents().send(MainFrame.this, intent);
//            }
//        });
//        return new DefaultOptions(this, mapping);
//    }
//
//    private Responses setupResponses() {
//        final Map<Integer, IntentAction> failure = new HashMap<Integer, IntentAction>();
//        failure.put(Requests.ADD_RIPOSTE_REQUEST, actions.cancel());
//        final Map<Integer, IntentAction> success = new HashMap<Integer, IntentAction>();
//        final IntentAction refresher = new IntentAction() {
//            // FIX 22/12/12 Don't really think this is a general UI action, but it might be.
//            public void run(final Intent intent) {
//                final SimpleAction runner = actions.refresh();
//                runner.run();
//            }
//        };
//        success.put(Requests.ADD_RIPOSTE_REQUEST, refresher);
//        success.put(Requests.EDIT_RIPOSTE_REQUEST, refresher);
//        return new DefaultResponses(success, failure);
//    }
//
//    protected void onDestroy() {
//        lifecycle.close();
//        super.onDestroy();
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return options.onCreate(menu, R.menu.main);
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        final boolean result = options.onClick(item);
//        return result ? result : super.onOptionsItemSelected(item);
//    }
//
//    public void onActivityResult(int reqCode, int resultCode, Intent data) {
//        super.onActivityResult(reqCode, resultCode, data);
//        responses.onResult(reqCode, resultCode, data);
//    }

}
