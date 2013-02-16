package dsq.rankle.viper;

public interface ItemAction<A> {
    void run(long id, A v);
}