package dsq.rankle.screen;

import android.app.ListActivity;
import android.os.Bundle;
import dsq.rankle.R;
import dsq.rankle.ui.thief.DefaultThiefListDefinition;
import dsq.rankle.ui.thief.ThiefListDefinition;
import dsq.sycophant.action.IdAction;
import dsq.sycophant.action.SimpleAction;
import dsq.sycophant.store.DefaultIdStore;
import dsq.sycophant.store.IdStore;
import dsq.sycophant.ui.button.Buttons;
import dsq.sycophant.ui.button.DefaultButtons;
import dsq.sycophant.ui.commandbar.Commandbar;
import dsq.sycophant.ui.commandbar.DefaultCommandbar;
import dsq.sycophant.ui.tabbar.DefaultTabbar;
import dsq.sycophant.ui.tabbar.Tabbar;

import java.util.HashMap;
import java.util.Map;

public class ThiefScreen extends ListActivity {
    private IdStore cid = new DefaultIdStore();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thief_screen);
//        adapter = getAdapter();

        final ThiefListDefinition definition = new DefaultThiefListDefinition();
//        list = new DefaultSelectableDataList<ThiefV>(this, adapter, definition, R.layout.thief_list_row);
//
//        list.refresh();

        final Map<Integer, IdAction> actions = new HashMap<Integer, IdAction>();
//        actions.put(R.id.thief_screen_edit, new TextDialogIdAction(this, RENAME_THIEF_DIALOG, RENAME_THIEF));
//        actions.put(R.id.thief_screen_delete, new IdAction() {
//            @Override
//            public void run(final long id) {
//                adapter.deleteById(id);
//                list.refresh();
//            }
//        });
        final Commandbar commands = new DefaultCommandbar(this, actions, cid);
        commands.register();

        final Map<Integer, SimpleAction> buttonActions = new HashMap<Integer, SimpleAction>();
//        buttonActions.put(R.id.thief_screen_add, new TextDialogSimpleAction(this, ADD_THIEF));

        final Buttons buttons = new DefaultButtons(this, buttonActions);
        buttons.register();

        final Map<Integer, Class<?>> tabActions = new HashMap<Integer, Class<?>>();
        tabActions.put(R.id.thief_tab_precious, PreciousScreen.class);
        tabActions.put(R.id.thief_tab_thief, ThiefScreen.class);
        tabActions.put(R.id.thief_tab_evidence, EvidenceScreen.class);
        final Tabbar tabs = new DefaultTabbar(this, tabActions);
        tabs.register();

        tabs.select(R.id.thief_tab_thief);

//        list.onSelect(new ItemAction<ThiefV>() {
//            @Override
//            public void run(final long id, final ThiefV v) {
//                Log.v("RANKLE", "Selecting: " + id);
//                cid.set(id);
//                commands.update();
//            }
//        });
    }
}
