package dsq.rankle.db.thief;

import dsq.thedroid.db.Table;

public class ThiefTable implements Table {
    public static final String TABLE = "THIEF";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String[] ALL_COLUMNS = new String[] { ID, NAME };

    public static final String CREATE_STATEMENT =
            "CREATE TABLE " + TABLE + "(" +
                    ID + " integer primary key autoincrement, " +
                    NAME + " text not null" +
                    ");";

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
            "INSERT INTO THIEF (name) VALUES ('Batman')",
            "INSERT INTO THIEF (name) VALUES ('Joker')",
            "INSERT INTO THIEF (name) VALUES ('Riddler')"
        };
    }

    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}
