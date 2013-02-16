package dsq.rankle.db.api;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import dsq.rankle.db.core.DatabaseHelper;

public class DefaultDbLifecycle implements DbLifecycle {
    private DatabaseHelper helper;

    public SQLiteDatabase open(Context context) throws SQLException {
        helper = new DatabaseHelper(context);
        return helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }
}
