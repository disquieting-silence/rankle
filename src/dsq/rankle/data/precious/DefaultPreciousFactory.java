package dsq.rankle.data.precious;

import android.content.ContentValues;
import dsq.rankle.db.precious.PreciousTable;

public class DefaultPreciousFactory implements PreciousFactory {
    @Override
    public PreciousV nu(final long id, final ContentValues values) {
        final String name = values.getAsString(PreciousTable.NAME);
        return nu(id, name);
    }

    @Override
    public PreciousV nu(final long id, final String name) {
        final PreciousId pid = new PreciousId(id);
        final PreciousName pname = new PreciousName(name);
        return new PreciousV(pid, pname);
    }
}
