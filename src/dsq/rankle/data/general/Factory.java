package dsq.rankle.data.general;

import android.content.ContentValues;

public interface Factory<A> {
    A nu(long id, ContentValues values);
}
