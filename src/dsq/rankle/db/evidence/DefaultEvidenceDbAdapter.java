package dsq.rankle.db.evidence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import dsq.rankle.data.evidence.DefaultEvidenceFactory;
import dsq.rankle.data.evidence.EvidenceFactory;
import dsq.rankle.data.evidence.EvidenceV;
import dsq.thedroid.db.DbAccess;
import dsq.thedroid.db.DefaultDbAccess;


public class DefaultEvidenceDbAdapter implements EvidenceDbAdapter {
    private DbAccess dbAccess = new DefaultDbAccess();
    private final SQLiteDatabase db;
    private final EvidenceFactory factory = new DefaultEvidenceFactory();
    public static final String[] VIEW_COLUMNS = new String[]{"_id", "precious_id", "precious_name", "thief_id", "thief_name"};

    public DefaultEvidenceDbAdapter(final SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    public EvidenceV create(final ContentValues values) {
        /* OK .. for creating evidence, you need a "thief" and a "precious" and a status. */
        final long id = dbAccess.create(db, EvidenceTable.TABLE, values);
        return factory.nu(id, values);
    }

    @Override
    public EvidenceV update(final long id, final ContentValues values) {
        final boolean updated = dbAccess.update(db, EvidenceTable.TABLE, id, values);
        // Should really make these options.
        return factory.nu(id, values);
    }

    public boolean deleteById(final long id) {
        return dbAccess.deleteById(db, EvidenceTable.TABLE, id);
    }

    public Cursor fetchAll() {
        return dbAccess.fetchAll(db, EvidenceView.TABLE, EvidenceView.ALL_COLUMNS);
    }

    public Cursor fetchById(final long id) {
        return dbAccess.fetchById(db, EvidenceView.TABLE, EvidenceView.ALL_COLUMNS, id);
    }
}
