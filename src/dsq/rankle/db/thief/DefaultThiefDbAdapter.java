package dsq.rankle.db.thief;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import dsq.rankle.data.thief.DefaultThiefFactory;
import dsq.rankle.data.thief.ThiefFactory;
import dsq.rankle.data.thief.ThiefV;
import dsq.thedroid.db.DbAccess;
import dsq.thedroid.db.DefaultDbAccess;

import static dsq.rankle.db.thief.ThiefTable.ALL_COLUMNS;
import static dsq.rankle.db.thief.ThiefTable.TABLE;

public class DefaultThiefDbAdapter implements ThiefDbAdapter {
    private final DbAccess dbAccess = new DefaultDbAccess();
    private final SQLiteDatabase db;
    private final ThiefFactory factory = new DefaultThiefFactory();

    public DefaultThiefDbAdapter(final SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public ThiefV create(final ContentValues values) {
        final long id = dbAccess.create(db, TABLE, values);
        return factory.nu(id, values);
    }

    @Override
    public ThiefV update(final long id, final ContentValues values) {
        final boolean updated = dbAccess.update(db, TABLE, id, values);
        // need to consider didn't work
        return factory.nu(id, values);
    }

    @Override
    public boolean deleteById(final long id) {
        return dbAccess.deleteById(db, TABLE, id);
    }

    @Override
    public Cursor fetchAll() {
        return dbAccess.fetchAll(db, TABLE, ALL_COLUMNS);
    }

    @Override
    public Cursor fetchById(final long id) throws SQLException {
        return dbAccess.fetchById(db, TABLE, ALL_COLUMNS, id);
    }
}
