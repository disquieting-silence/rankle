package dsq.rankle.ui.thief;

import android.database.Cursor;
import android.view.View;
import dsq.rankle.R;
import dsq.rankle.data.thief.DefaultThiefFactory;
import dsq.rankle.data.thief.ThiefFactory;
import dsq.rankle.data.thief.ThiefV;
import dsq.rankle.db.thief.ThiefTable;

public class DefaultThiefListDefinition implements ThiefListDefinition {

    private final ThiefFactory factory = new DefaultThiefFactory();

    @Override
    public String[] sources() {
        return new String[] { ThiefTable.NAME };
    }

    @Override
    public int[] destinations() {
        return new int[] { R.id.thief_name };
    }

    @Override
    public ThiefV build(final Cursor c) {
        return c.moveToFirst()  ? safeBuild(c) : factory.nu(-1, "");
    }

    private ThiefV safeBuild(final Cursor c) {
        final int idCol = c.getColumnIndex(ThiefTable.ID);
        final int id = idCol < c.getColumnCount() ? c.getInt(idCol) : -1;
        final int nameCol = c.getColumnIndex(ThiefTable.NAME);
        final String name = c.getString(nameCol);
        return factory.nu(id, name);
    }

    @Override
    public boolean setViewValue(final View view, final Cursor cursor, final int columnIndex) {
        return false;
    }
}
