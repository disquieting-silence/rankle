package dsq.rankle.screen;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import dsq.rankle.R;
import dsq.rankle.data.precious.PreciousV;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.precious.DefaultPreciousDbAdapter;
import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.rankle.ui.precious.DefaultPreciousListDefinition;
import dsq.rankle.ui.precious.PreciousListDefinition;
import dsq.rankle.viper.*;
import dsq.thedroid.ui.Buttons;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PreciousScreen extends ListActivity
{

    private final DbLifecycle lifecycle = new DefaultDbLifecycle();

    private IdStore cid = new DefaultIdStore();
    private PreciousDbAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precious_screen);
        adapter = getAdapter();

        final PreciousListDefinition definition = new DefaultPreciousListDefinition();
        final SelectableDataList<PreciousV> list = new DefaultSelectableDataList<PreciousV>(this, adapter, definition, R.layout.precious_list_row);
        list.refresh();
    }

    private PreciousDbAdapter getAdapter() {
        final SQLiteDatabase db = lifecycle.open(this);
        return new DefaultPreciousDbAdapter(db);
    }

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
