package dsq.rankle.ui.precious;

import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.rankle.viper.IdAction;
import dsq.rankle.viper.IntentAction;
import dsq.rankle.viper.SimpleAction;

public interface UiActions {
    IdAction delete(PreciousDbAdapter adapter);
    IdAction rename(PreciousDbAdapter adapter);
    SimpleAction add(PreciousDbAdapter adapter);
    SimpleAction refresh(PreciousDbAdapter adapter);
}
