package dsq.rankle.db.api;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface DbLifecycle {
    SQLiteDatabase open(Context context) throws SQLException;
    void close();
}
