package dsq.rankle.db.evidence;

import dsq.rankle.db.precious.PreciousTable;
import dsq.rankle.db.thief.ThiefTable;
import dsq.thedroid.db.Table;

public class EvidenceTable implements Table {
    public static final String TABLE = "EVIDENCE";

    public static final String ID = "_id";
    public static final String THIEF_ID = "thief_id";
    public static final String PRECIOUS_ID = "precious_id";
    public static final String[] ALL_COLUMNS = new String[] { ID, PRECIOUS_ID, THIEF_ID};

    public static final String CREATE_STATEMENT =
            "CREATE TABLE " + TABLE + "(" +
                    ID + " integer primary key autoincrement, " +
                    PRECIOUS_ID + " integer not null, " +
                    THIEF_ID + " integer not null, " +
                    "FOREIGN KEY (" + PRECIOUS_ID + ") references " + PreciousTable.TABLE + "(" + PreciousTable.ID + ") ON DELETE CASCADE, " +
                    "FOREIGN KEY (" + THIEF_ID + ") references " + ThiefTable.TABLE + "(" + ThiefTable.ID + ") ON DELETE CASCADE" +
            ");" +
            "CREATE VIEW EVIDENCE_VIEW SELECT e._id, e.precious_id, e.thief_id, p.name as precious_name, t.name as thief_name " +
                    "FROM evidence as e, precious as p, thief as t " +
                    "WHERE e.thief_id = t._id AND e.precious_id = p._id;";


    public static final String DROP_STATEMENT = "DROP TABLE IF EXISTS " + TABLE;

    public String create() {
        return CREATE_STATEMENT;
    }

    public String name() {
        return TABLE;
    }

    public String drop() {
        return DROP_STATEMENT;
    }

    public String[] load() {
        return new String[] {
                "INSERT INTO EVIDENCE (thief_id, precious_id) VALUES (1, 1)"
        };
    }

    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}
