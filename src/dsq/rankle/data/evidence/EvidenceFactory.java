package dsq.rankle.data.evidence;

import dsq.rankle.data.general.Factory;
import dsq.rankle.data.precious.PreciousV;

public interface EvidenceFactory extends Factory<EvidenceV> {
    EvidenceV nu(final long id, final long pId, final String pName, final long tId, final String tName);

}
