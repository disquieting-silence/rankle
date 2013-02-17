package dsq.rankle.data.thief;

import dsq.rankle.data.general.Factory;

public interface ThiefFactory extends Factory<ThiefV> {
    ThiefV nu(long id, String name);
}
