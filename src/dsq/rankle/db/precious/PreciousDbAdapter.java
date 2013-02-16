package dsq.rankle.db.precious;

import dsq.thedroid.db.DbAdapter;

public interface PreciousDbAdapter extends DbAdapter {
    long create(String name);
    boolean update(long id, String name);
}
