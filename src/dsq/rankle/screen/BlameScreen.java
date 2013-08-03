package dsq.rankle.screen;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import dsq.rankle.R;
import dsq.rankle.db.api.DbLifecycle;
import dsq.rankle.db.api.DefaultDbLifecycle;
import dsq.rankle.db.evidence.EvidenceTable;
import dsq.rankle.db.precious.DefaultPreciousDbAdapter;
import dsq.rankle.db.precious.PreciousTable;
import dsq.rankle.db.thief.DefaultThiefDbAdapter;
import dsq.rankle.db.thief.ThiefDbAdapter;
import dsq.rankle.db.thief.ThiefTable;
import dsq.rankle.ui.dialog.DialogConstants;
import dsq.thedroid.db.DbAdapter;

import static dsq.rankle.ui.dialog.DialogConstants.ASSIGN_PRECIOUS_ID;

public class BlameScreen extends Activity {
    private final DbLifecycle lifecycle = new DefaultDbLifecycle();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blame_screen);

        final SQLiteDatabase db = lifecycle.open(this);

        final ThiefDbAdapter thiefAdapter = new DefaultThiefDbAdapter(db);
        final DbAdapter preciousAdapter = new DefaultPreciousDbAdapter(db);
        final SimpleCursorAdapter thieves = configure(thiefAdapter, ThiefTable.NAME);
        final SimpleCursorAdapter preciouses = configure(preciousAdapter, PreciousTable.NAME);

        final Button cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        final Spinner precious = (Spinner)findViewById(R.id.precious_spinner);
        precious.setAdapter(preciouses);
        final Spinner thief = (Spinner)findViewById(R.id.thief_spinner);
        thief.setAdapter(thieves);
        final Button ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent();
                final long selectedItemId = precious.isEnabled() ? precious.getSelectedItemId() : getEditId(savedInstanceState);
                intent.putExtra(EvidenceTable.PRECIOUS_ID, selectedItemId);
                intent.putExtra(EvidenceTable.THIEF_ID, thief.getSelectedItemId());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        long preciousId = getEditId(savedInstanceState);
        if (preciousId > -1) precious.setEnabled(false);
    }

    private long getEditId(final Bundle state) {
        final Intent intent = getIntent();
        if (state != null && intent != null) {
           return state.getLong(ASSIGN_PRECIOUS_ID, intent.getLongExtra(ASSIGN_PRECIOUS_ID, -1));
        } else if (state != null) {
           return state.getLong(ASSIGN_PRECIOUS_ID, -1);
        } else if (intent != null) {
           return intent.getLongExtra(ASSIGN_PRECIOUS_ID, -1);
        } else {
            return -1;
        }
    }

    private SimpleCursorAdapter configure(final DbAdapter adapter, final String field) {
        final Cursor cursor = adapter.fetchAll();
        final String[] from = {field};
        final int[] to = {android.R.id.text1};
        final SimpleCursorAdapter spinnerAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, from, to);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return spinnerAdapter;
    }

    @Override
    protected void onDestroy() {
        lifecycle.close();
        super.onDestroy();
    }
}
