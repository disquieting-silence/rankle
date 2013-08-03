package dsq.rankle.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import dsq.rankle.R;
import dsq.rankle.ui.common.DefaultTabbars;
import dsq.rankle.ui.common.Tabbars;
import dsq.sycophant.ui.tabbar.Tabbar;

public class EvidenceScreen extends Activity {
    private final Tabbars tabbars = new DefaultTabbars();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evidence_screen);
        final Tabbar tabs = tabbars.add(this);
        tabs.select(R.id.tab_evidence);
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
