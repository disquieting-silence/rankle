package dsq.rankle.data.thief;

import android.content.ContentValues;
import dsq.rankle.db.thief.ThiefTable;

public class DefaultThiefFactory implements ThiefFactory {
    @Override
    public ThiefV nu(final long id, final ContentValues values) {
        final String name = values.getAsString(ThiefTable.NAME);
        return nu(id, name);
    }

    @Override
    public ThiefV nu(final long id, final String name) {
        final ThiefId tid = new ThiefId(id);
        final ThiefName tname = new ThiefName(name);
        return new ThiefV(tid, tname);
    }
}
