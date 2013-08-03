package dsq.rankle.ui.evidence;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;
import dsq.rankle.R;
import dsq.rankle.data.evidence.DefaultEvidenceFactory;
import dsq.rankle.data.evidence.EvidenceFactory;
import dsq.rankle.data.evidence.EvidenceV;
import dsq.rankle.db.evidence.EvidenceTable;
import dsq.rankle.db.precious.PreciousTable;

public class DefaultEvidenceListDefinition implements EvidenceListDefinition {
    private final EvidenceFactory factory = new DefaultEvidenceFactory();

    @Override
    public String[] sources() {
        return new String[] { "precious_name", "thief_name" };
    }

    @Override
    public int[] destinations() {
        return new int[] { R.id.precious_name, R.id.thief_name };
    }

    @Override
    public EvidenceV build(final Cursor c) {
        return c.moveToFirst() ? safeBuild(c) :factory.nu(-1, -1, "", -1, "");
    }

    private EvidenceV safeBuild(final Cursor c) {
        final int id = getIdColumn(c, EvidenceTable.ID);
        final int preciousId = getIdColumn(c, EvidenceTable.PRECIOUS_ID);
        final String preciousName = getStrColumn(c, "precious_name");
        final int thiefId = getIdColumn(c, EvidenceTable.THIEF_ID);
        final String thiefName = getStrColumn(c, "thief_name");
        return factory.nu(id, preciousId, preciousName, thiefId, thiefName);
    }

    private int getIdColumn(final Cursor c, final String name) {
        final int idCol = c.getColumnIndex(name);
        return idCol < c.getColumnCount() ? c.getInt(idCol) : -1;
    }

    private String getStrColumn(final Cursor c, final String name) {
        final int nameCol = c.getColumnIndex(name);
        return c.getString(nameCol);
    }

    @Override
    public boolean setViewValue(final View view, final Cursor cursor, final int columnIndex) {
        return false;
    }
}
