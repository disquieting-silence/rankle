package dsq.rankle.data.evidence;

import android.content.ContentValues;
import dsq.rankle.data.precious.PreciousId;
import dsq.rankle.data.precious.PreciousName;
import dsq.rankle.data.thief.ThiefId;
import dsq.rankle.data.thief.ThiefName;
import dsq.rankle.db.evidence.EvidenceTable;
import dsq.rankle.db.precious.PreciousTable;
import dsq.rankle.db.thief.ThiefTable;

public class DefaultEvidenceFactory implements EvidenceFactory {
    @Override
    public EvidenceV nu(final long id, final long pId, final String pName, final long tId, final String tName) {
        final PreciousId preciousId = new PreciousId(pId);
        final PreciousName preciousName = new PreciousName(pName);
        final ThiefId thiefId = new ThiefId(tId);
        final ThiefName thiefName = new ThiefName(tName);
        return new EvidenceV(new EvidenceId(id), preciousId, preciousName, thiefId, thiefName);
    }

    @Override
    public EvidenceV nu(final long id, final ContentValues values) {
        final long precious = values.getAsLong(EvidenceTable.PRECIOUS_ID);
        final String pName = values.getAsString("precious_name");
        final long thief = values.getAsLong(EvidenceTable.THIEF_ID);
        final String tName = values.getAsString("thief_name");
        return nu(id, precious, pName, thief, tName);
    }
}
