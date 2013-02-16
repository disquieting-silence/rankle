package dsq.rankle.ui.precious;

import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.sycophant.action.IdAction;
import dsq.sycophant.action.SimpleAction;


public interface UiActions {
    IdAction delete(PreciousDbAdapter adapter);
    IdAction rename(PreciousDbAdapter adapter);
    SimpleAction add(PreciousDbAdapter adapter);
    SimpleAction refresh(PreciousDbAdapter adapter);
}
