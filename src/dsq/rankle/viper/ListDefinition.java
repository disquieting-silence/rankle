package dsq.rankle.viper;

import android.database.Cursor;
import android.view.View;

public interface ListDefinition<A> {
    String[] sources();
    int[] destinations();
    A build(Cursor c);
    boolean setViewValue(View view, Cursor cursor, int columnIndex);
}
