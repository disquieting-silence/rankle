package dsq.rankle.ui.common;

import android.app.Activity;
import dsq.rankle.R;
import dsq.rankle.screen.EvidenceScreen;
import dsq.rankle.screen.PreciousScreen;
import dsq.rankle.screen.ThiefScreen;
import dsq.sycophant.ui.tabbar.ActivityTabbar;
import dsq.sycophant.ui.tabbar.Tabbar;

import java.util.HashMap;
import java.util.Map;

public class DefaultTabbars implements Tabbars {
    @Override
    public Tabbar add(Activity target) {
        final Map<Integer, Class<?>> tabActions = new HashMap<Integer, Class<?>>();
        tabActions.put(R.id.tab_precious, PreciousScreen.class);
        tabActions.put(R.id.tab_thief, ThiefScreen.class);
        tabActions.put(R.id.tab_evidence, EvidenceScreen.class);
        final Tabbar tabs = new ActivityTabbar(target, tabActions);
        tabs.register();
        return tabs;
    }
}
