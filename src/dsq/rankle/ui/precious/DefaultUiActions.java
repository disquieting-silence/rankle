package dsq.rankle.ui.precious;

import dsq.rankle.db.precious.PreciousDbAdapter;
import dsq.sycophant.action.IdAction;
import dsq.sycophant.action.SimpleAction;

public class DefaultUiActions implements UiActions {
    @Override
    public IdAction delete(final PreciousDbAdapter adapter) {
        return new IdAction() {
            @Override
            public void run(final long id) {


            }
        };
    }

    @Override
    public IdAction rename(final PreciousDbAdapter adapter) {
        return new IdAction() {
            @Override
            public void run(final long id) {

            }
        };
    }

    @Override
    public SimpleAction add(final PreciousDbAdapter adapter) {
        return new SimpleAction() {
            @Override
            public void run() {

            }
        };
    }

    @Override
    public SimpleAction refresh(final PreciousDbAdapter adapter) {
        return new SimpleAction() {
            @Override
            public void run() {

            }
        };
    }
}
