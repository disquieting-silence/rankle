package dsq.rankle.ui.precious;

import dsq.rankle.viper.IdAction;
import dsq.rankle.viper.IntentAction;
import dsq.rankle.viper.SimpleAction;

public interface UiActions {
    IdAction delete();
    IdAction rename();
    SimpleAction add();
    SimpleAction refresh();
}
