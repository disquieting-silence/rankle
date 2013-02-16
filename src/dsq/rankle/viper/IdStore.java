package dsq.rankle.viper;

public interface IdStore {
    long get();
    void set(long id);
    boolean isSet();
}
