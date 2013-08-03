package dsq.rankle.data.evidence;

import dsq.rankle.data.precious.PreciousId;
import dsq.rankle.data.precious.PreciousName;
import dsq.rankle.data.thief.ThiefId;
import dsq.rankle.data.thief.ThiefName;

public class EvidenceV {
    public final EvidenceId id;
    public final ThiefId thief;
    public final ThiefName tName;
    public final PreciousId precious;
    public final PreciousName pName;

    public EvidenceV(final EvidenceId id, final PreciousId precious, final PreciousName pName, final ThiefId thief, final ThiefName tName) {
        this.id = id;
        this.precious = precious;
        this.thief = thief;
        this.pName = pName;
        this.tName = tName;
    }
}
