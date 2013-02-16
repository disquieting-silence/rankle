package dsq.rankle.db.precious;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import dsq.thedroid.db.DbAccess;
import dsq.thedroid.db.DefaultDbAccess;

public class DefaultPreciousDbAdapter implements PreciousDbAdapter {
    private DbAccess dbAccess = new DefaultDbAccess();
    private final SQLiteDatabase db;

    private static final String TABLE = "PRECIOUS";

    public DefaultPreciousDbAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public long create(final String name) {
        final ContentValues values = new ContentValues();
        values.put(PreciousTable.NAME, name);
        return dbAccess.create(db, TABLE, values);
    }

    public boolean update(final long id, final String name) {
        final ContentValues values = new ContentValues();
        values.put(PreciousTable.NAME, name);
        return dbAccess.update(db, TABLE, id, values);
    }

    public boolean deleteById(final long id) {
        return dbAccess.deleteById(db, TABLE, id);
    }


    public Cursor fetchAll() {
        return dbAccess.fetchAll(db, TABLE, PreciousTable.ALL_COLUMNS);
    }

    public Cursor fetchById(final long id) {
        return dbAccess.fetchById(db, TABLE, PreciousTable.ALL_COLUMNS, id);
    }
}
