package dsq.rankle.screen;

import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import dsq.rankle.R;
import dsq.rankle.data.evidence.EvidenceV;
import dsq.rankle.data.precious.PreciousV;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.evidence.DefaultEvidenceDbAdapter;
import dsq.rankle.db.evidence.EvidenceDbAdapter;
import dsq.rankle.db.evidence.EvidenceTable;
import dsq.rankle.db.precious.DefaultPreciousDbAdapter;
import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.rankle.db.thief.DefaultThiefDbAdapter;
import dsq.rankle.db.thief.ThiefTable;
import dsq.rankle.ui.common.DefaultTabbars;
import dsq.rankle.ui.common.Tabbars;
import dsq.rankle.ui.dialog.DialogConstants;
import dsq.rankle.ui.evidence.DefaultEvidenceListDefinition;
import dsq.rankle.ui.evidence.EvidenceListDefinition;
import dsq.sycophant.action.IdAction;
import dsq.sycophant.action.ItemAction;
import dsq.sycophant.action.SimpleAction;
import dsq.sycophant.datalist.DefaultSelectableDataList;
import dsq.sycophant.datalist.SelectableDataList;
import dsq.sycophant.store.DefaultIdStore;
import dsq.sycophant.store.IdStore;
import dsq.sycophant.ui.button.Buttons;
import dsq.sycophant.ui.button.DefaultButtons;
import dsq.sycophant.ui.commandbar.Commandbar;
import dsq.sycophant.ui.commandbar.DefaultCommandbar;
import dsq.sycophant.ui.dialog.TextDialogIdAction;
import dsq.sycophant.ui.dialog.TextDialogSimpleAction;
import dsq.sycophant.ui.tabbar.Tabbar;

import java.util.HashMap;
import java.util.Map;

import static dsq.rankle.ui.dialog.DialogConstants.ADD_PRECIOUS;
import static dsq.rankle.ui.dialog.DialogConstants.RENAME_PRECIOUS;
import static dsq.rankle.ui.dialog.DialogConstants.RENAME_PRECIOUS_DIALOG;

public class EvidenceScreen extends ListActivity {
    private final DbLifecycle lifecycle = new DefaultDbLifecycle();
    private IdStore cid = new DefaultIdStore();
    private EvidenceDbAdapter adapter;
    private final Tabbars tabbars = new DefaultTabbars();
    private SelectableDataList<EvidenceV> list;

    private Activity self = this;

    private EvidenceV evidence;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evidence_screen);
        final Tabbar tabs = tabbars.add(this);
        tabs.select(R.id.tab_evidence);

        adapter = getAdapter();

        final EvidenceListDefinition definition = new DefaultEvidenceListDefinition();
        list = new DefaultSelectableDataList<EvidenceV>(this, adapter, definition, R.layout.evidence_list_row);

        final Commandbar commands = setupCommandbar();
        commands.register();

        final Buttons buttons = setupButtons();
        buttons.register();

        list.onSelect(new ItemAction<EvidenceV>() {
            @Override
            public void run(final long id, final EvidenceV v) {
                cid.set(id);
                evidence = v;
                commands.update();
            }
        });
        list.refresh();
    }

    private Buttons setupButtons() {
        final Map<Integer, SimpleAction> buttonActions = new HashMap<Integer, SimpleAction>();
        buttonActions.put(R.id.evidence_screen_add, new SimpleAction() {
            @Override
            public void run() {
                final Intent intent = new Intent(self, BlameScreen.class);
                startActivityForResult(intent, DialogConstants.ADD_EVIDENCE);
            }
        });

        return new DefaultButtons(this, buttonActions);
    }

    private Commandbar setupCommandbar() {
        final Map<Integer, IdAction> actions = new HashMap<Integer, IdAction>();
        actions.put(R.id.evidence_screen_edit, new IdAction() {
            @Override
            public void run(final long id) {
                final Intent intent = new Intent(self, BlameScreen.class);
                if (evidence != null) {
                    intent.putExtra(DialogConstants.ASSIGN_PRECIOUS_ID, evidence.precious.value);
                    intent.putExtra(DialogConstants.ASSIGN_THIEF_ID, evidence.thief.value);
                }

                startActivityForResult(intent, DialogConstants.EDIT_EVIDENCE);
            }
        });
        actions.put(R.id.evidence_screen_delete, new IdAction() {
            @Override
            public void run(final long id) {
                adapter.deleteById(id);
                list.refresh();
            }
        });
        return new DefaultCommandbar(this, actions, cid);
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

    private EvidenceDbAdapter getAdapter() {
        final SQLiteDatabase db = lifecycle.open(this);
        return new DefaultEvidenceDbAdapter(db);
    }

    private ContentValues generate(final Intent data) {
        final ContentValues values = new ContentValues();
        final long preciousId = data.getLongExtra(EvidenceTable.PRECIOUS_ID, -1);
        final long thiefId = data.getLongExtra(EvidenceTable.THIEF_ID, -1);
        values.put(EvidenceTable.PRECIOUS_ID, preciousId);
        values.put(EvidenceTable.THIEF_ID, thiefId);
        // FIX: Evil, evil, evil.
        return preciousId > -1 && thiefId > -1 ? values : null;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == DialogConstants.ADD_EVIDENCE && resultCode == Activity.RESULT_OK) {
            final ContentValues values = generate(data);
            if (values != null) {
                adapter.create(values);
                list.refresh();
            }
        } else if (requestCode == DialogConstants.EDIT_EVIDENCE && resultCode == Activity.RESULT_OK) {
            final ContentValues values = generate(data);
            if (values != null) {
                adapter.update(cid.get(), values);
                list.refresh();
            }
        }
    }

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }
}