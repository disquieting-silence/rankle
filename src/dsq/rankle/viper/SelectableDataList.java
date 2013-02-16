package dsq.rankle.viper;

public interface SelectableDataList<A> {
    long selected();
    void refresh();
    void onSelect(ItemAction<A> action);
}