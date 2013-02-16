package dsq.rankle.ui.precious;

import dsq.rankle.viper.IdAction;
import dsq.rankle.viper.SimpleAction;

public class DefaultUiActions implements UiActions {
    @Override
    public IdAction delete() {
        return new IdAction() {
            @Override
            public void run(final long id) {

            }
        };
    }

    @Override
    public IdAction rename() {
        return new IdAction() {
            @Override
            public void run(final long id) {

            }
        };
    }

    @Override
    public SimpleAction add() {
        return new SimpleAction() {
            @Override
            public void run() {

            }
        };
    }

    @Override
    public SimpleAction refresh() {
        return new SimpleAction() {
            @Override
            public void run() {

            }
        };
    }
}
