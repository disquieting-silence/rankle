package dsq.rankle.screen;

import android.app.Activity;
import android.app.ListActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import dsq.rankle.R;
import dsq.rankle.data.evidence.EvidenceV;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.evidence.DefaultEvidenceDbAdapter;
import dsq.rankle.db.evidence.EvidenceDbAdapter;
import dsq.rankle.db.precious.DefaultPreciousDbAdapter;
import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.rankle.ui.common.DefaultTabbars;
import dsq.rankle.ui.common.Tabbars;
import dsq.rankle.ui.evidence.DefaultEvidenceListDefinition;
import dsq.rankle.ui.evidence.EvidenceListDefinition;
import dsq.sycophant.datalist.DefaultSelectableDataList;
import dsq.sycophant.datalist.SelectableDataList;
import dsq.sycophant.store.DefaultIdStore;
import dsq.sycophant.store.IdStore;
import dsq.sycophant.ui.tabbar.Tabbar;

public class EvidenceScreen extends ListActivity {
    private final DbLifecycle lifecycle = new DefaultDbLifecycle();
    private IdStore cid = new DefaultIdStore();
    private EvidenceDbAdapter adapter;
    private final Tabbars tabbars = new DefaultTabbars();
    private SelectableDataList<EvidenceV> list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evidence_screen);
        final Tabbar tabs = tabbars.add(this);
        tabs.select(R.id.tab_evidence);

        adapter = getAdapter();

        final EvidenceListDefinition definition = new DefaultEvidenceListDefinition();
        list = new DefaultSelectableDataList<EvidenceV>(this, adapter, definition, R.layout.evidence_list_row);

        list.refresh();
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

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }
}
