package dsq.rankle.screen;

import android.app.Dialog;
import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import dsq.rankle.R;
import dsq.rankle.data.precious.PreciousV;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.precious.DefaultPreciousDbAdapter;
import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.rankle.ui.common.DefaultTabbars;
import dsq.rankle.ui.common.Tabbars;
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
import dsq.sycophant.ui.dialog.TextDialogIdAction;
import dsq.sycophant.ui.dialog.TextDialogSimpleAction;
import dsq.sycophant.ui.tabbar.ActivityTabbar;
import dsq.sycophant.ui.tabbar.Tabbar;

import java.util.HashMap;
import java.util.Map;

import static dsq.rankle.db.precious.PreciousTable.NAME;
import static dsq.rankle.ui.dialog.DialogConstants.*;

public class PreciousScreen extends ListActivity {

    private final DbLifecycle lifecycle = new DefaultDbLifecycle();

    private IdStore cid = new DefaultIdStore();
    private PreciousDbAdapter adapter;
    private final Dialogs dialogs = new DefaultDialogs();
    private SelectableDataList<PreciousV> list;
    private final Tabbars tabbars = new DefaultTabbars();

    private SingleAction<PreciousV> refreshList = new SingleAction<PreciousV>() {
        @Override
        public void run(final PreciousV preciousV) {
            list.refresh();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precious_screen);
        adapter = getAdapter();

        final PreciousListDefinition definition = new DefaultPreciousListDefinition();
        list = new DefaultSelectableDataList<PreciousV>(this, adapter, definition, R.layout.precious_list_row);

        list.refresh();

        final Map<Integer, IdAction> actions = new HashMap<Integer, IdAction>();
        actions.put(R.id.precious_screen_edit, new TextDialogIdAction(this, RENAME_PRECIOUS_DIALOG, RENAME_PRECIOUS));
        actions.put(R.id.precious_screen_delete, new IdAction() {
            @Override
            public void run(final long id) {
                adapter.deleteById(id);
                list.refresh();
            }
        });
        final Commandbar commands = new DefaultCommandbar(this, actions, cid);
        commands.register();

        final Map<Integer, SimpleAction> buttonActions = new HashMap<Integer, SimpleAction>();
        buttonActions.put(R.id.precious_screen_add, new TextDialogSimpleAction(this, ADD_PRECIOUS));

        final Buttons buttons = new DefaultButtons(this, buttonActions);
        buttons.register();

        final Tabbar tabs = tabbars.add(this);
        tabs.select(R.id.precious_tab_precious);

        list.onSelect(new ItemAction<PreciousV>() {
            @Override
            public void run(final long id, final PreciousV v) {
                Log.v("RANKLE", "Selecting: " + id);
                cid.set(id);
                commands.update();
            }
        });
    }

    private PreciousDbAdapter getAdapter() {
        final SQLiteDatabase db = lifecycle.open(this);
        return new DefaultPreciousDbAdapter(db);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id) {
            case ADD_PRECIOUS: return dialogs.create(this, adapter, "Precious: ", NAME, refreshList);
            case RENAME_PRECIOUS: return dialogs.update(this, adapter, "Rename: ", RENAME_PRECIOUS_DIALOG, NAME, refreshList);
            default: return null;
        }
    }

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }
}
