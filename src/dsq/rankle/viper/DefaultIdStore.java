package dsq.rankle.viper;

public class DefaultIdStore implements IdStore {
    private long value = -1;

    public long get() {
        return value;
    }

    public void set(final long id) {
        value = id;
    }

    public boolean isSet() {
        return value != -1;
    }
}
