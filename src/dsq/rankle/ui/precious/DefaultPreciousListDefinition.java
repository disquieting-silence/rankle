package dsq.rankle.ui.precious;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.View;
import dsq.rankle.R;
import dsq.rankle.data.precious.PreciousId;
import dsq.rankle.data.precious.PreciousName;
import dsq.rankle.data.precious.PreciousV;
import dsq.rankle.db.precious.PreciousTable;

public class DefaultPreciousListDefinition implements PreciousListDefinition {

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
        return c.moveToFirst() ? safeBuild(c) : none();
    }

    private PreciousV none() {
        final PreciousId id = new PreciousId(-1);
        final PreciousName name = new PreciousName("");
        return new PreciousV(id, name);
    }

    private PreciousV safeBuild(final Cursor c) {
        final int idCol = c.getColumnIndex(PreciousTable.ID);
        final int id = idCol < c.getColumnCount() ? c.getInt(idCol) : -1;
        final int nameCol = c.getColumnIndex(PreciousTable.NAME);
        final String name = c.getString(nameCol);
        return nu(id, name);
    }

    private PreciousV nu(final int id, final String name) {
        final PreciousId pid = new PreciousId(id);
        final PreciousName pname = new PreciousName(name);
        return new PreciousV(pid, pname);
    }

    @Override
    public boolean setViewValue(final View view, final Cursor cursor, final int columnIndex) {
        return false;
    }
}
