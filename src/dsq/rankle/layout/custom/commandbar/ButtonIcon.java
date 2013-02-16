package dsq.rankle.layout.custom.commandbar;

import dsq.rankle.viper.SimpleAction;

public interface ButtonIcon {
    /* Dupe from listless. */
    void setAction(SimpleAction action);
    void setActionEnabled(boolean enabled);
    void setImages(ButtonImages images);
}
