package dsq.rankle.data.precious;

import dsq.rankle.data.general.Factory;

public interface PreciousFactory extends Factory<PreciousV> {
    PreciousV nu(long id, String name);

}
