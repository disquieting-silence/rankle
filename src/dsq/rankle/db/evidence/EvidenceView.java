package dsq.rankle.db.evidence;

import dsq.thedroid.db.Table;

public class EvidenceView implements Table {
    public static final String TABLE = "EVIDENCE_VIEW";

    public static final String ID = "_id";
    public static final String THIEF_ID = "thief_id";
    public static final String PRECIOUS_ID = "precious_id";
    public static final String THIEF_NAME = "thief_name";
    public static final String PRECIOUS_NAME = "precious_name";
    public static final String[] ALL_COLUMNS = new String[] { ID, PRECIOUS_ID, PRECIOUS_NAME, THIEF_ID, THIEF_NAME};

    public static final String CREATE_STATEMENT =
            "CREATE VIEW EVIDENCE_VIEW AS SELECT e._id, e.precious_id, e.thief_id, p.name as precious_name, t.name as thief_name " +
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
        return new String[0];
    }

    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}
