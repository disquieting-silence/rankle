package dsq.rankle.screen;

import android.app.Dialog;
import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import dsq.rankle.R;
import dsq.rankle.data.thief.ThiefV;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.thief.DefaultThiefDbAdapter;
import dsq.rankle.db.thief.ThiefDbAdapter;
import dsq.rankle.ui.common.DefaultTabbars;
import dsq.rankle.ui.common.Tabbars;
import dsq.rankle.ui.thief.DefaultThiefListDefinition;
import dsq.rankle.ui.thief.ThiefListDefinition;
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

import static dsq.rankle.db.thief.ThiefTable.NAME;
import static dsq.rankle.ui.dialog.DialogConstants.*;

public class ThiefScreen extends ListActivity {
    private final DbLifecycle lifecycle = new DefaultDbLifecycle();

    private IdStore cid = new DefaultIdStore();
    private ThiefDbAdapter adapter;
    private final Dialogs dialogs = new DefaultDialogs();
    private SelectableDataList<ThiefV> list;
    private final Tabbars tabbars = new DefaultTabbars();

    private SingleAction<ThiefV> refreshList = new SingleAction<ThiefV>() {
        @Override
        public void run(final ThiefV v) {
            list.refresh();
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thief_screen);
        adapter = getAdapter();

        final ThiefListDefinition definition = new DefaultThiefListDefinition();
        list = new DefaultSelectableDataList<ThiefV>(this, adapter, definition, R.layout.thief_list_row);

        list.refresh();

        final Commandbar commands = setupCommands();
        commands.register();

        final Buttons buttons = setupButtons();
        buttons.register();

        final Tabbar tabs = tabbars.add(this);
        tabs.select(R.id.tab_thief);

        list.onSelect(new ItemAction<ThiefV>() {
            @Override
            public void run(final long id, final ThiefV v) {
                Log.v("RANKLE", "Selecting: " + id);
                cid.set(id);
                commands.update();
            }
        });
    }

    private Buttons setupButtons() {
        final Map<Integer, SimpleAction> buttonActions = new HashMap<Integer, SimpleAction>();
        buttonActions.put(R.id.thief_screen_add, new TextDialogSimpleAction(this, ADD_THIEF));

        return new DefaultButtons(this, buttonActions);
    }

    private Commandbar setupCommands() {
        final Map<Integer, IdAction> actions = new HashMap<Integer, IdAction>();
        actions.put(R.id.thief_screen_edit, new TextDialogIdAction(this, RENAME_THIEF_DIALOG, RENAME_THIEF));
        actions.put(R.id.thief_screen_delete, new IdAction() {
            @Override
            public void run(final long id) {
                adapter.deleteById(id);
                list.refresh();
            }
        });
        return new DefaultCommandbar(this, actions, cid);
    }

    private ThiefDbAdapter getAdapter() {
        final SQLiteDatabase db = lifecycle.open(this);
        return new DefaultThiefDbAdapter(db);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.onPause();
    }

    @Override
    protected Dialog onCreateDialog(final int id) {
        switch (id) {
            case ADD_THIEF: return dialogs.create(this, adapter, "Thief: ", NAME, refreshList);
            case RENAME_THIEF: return dialogs.update(this, adapter, "Rename: ", RENAME_THIEF_DIALOG, NAME, refreshList);
            default: return null;
        }
    }

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }

}
