package dsq.rankle.ui.precious;

import android.database.Cursor;
import android.view.View;
import dsq.rankle.R;
import dsq.rankle.data.precious.*;
import dsq.rankle.db.precious.PreciousTable;

public class DefaultPreciousListDefinition implements PreciousListDefinition {

    private final PreciousFactory factory =  new DefaultPreciousFactory();

    @Override
    public String[] sources() {
        return new String[] { PreciousTable.NAME };
    }

    @Override
    public int[] destinations() {
        return new int[] { R.id.precious_name };
    }

    @Override
    public PreciousV build(final Cursor c) {
        return c.moveToFirst() ? safeBuild(c) : factory.nu(-1, "");
    }

    private PreciousV safeBuild(final Cursor c) {
        final int idCol = c.getColumnIndex(PreciousTable.ID);
        final int id = idCol < c.getColumnCount() ? c.getInt(idCol) : -1;
        final int nameCol = c.getColumnIndex(PreciousTable.NAME);
        final String name = c.getString(nameCol);
        return factory.nu(id, name);
    }

    @Override
    public boolean setViewValue(final View view, final Cursor cursor, final int columnIndex) {
        return false;
    }
}
