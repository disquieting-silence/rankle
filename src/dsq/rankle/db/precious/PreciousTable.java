package dsq.rankle.db.precious;

import dsq.thedroid.db.Table;

public class PreciousTable implements Table {
    public static final String TABLE = "PRECIOUS";

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
            "INSERT INTO PRECIOUS (name) VALUES ('The Dark Knight Rises')",
            "INSERT INTO PRECIOUS (name) VALUES ('Batman')"
        };
    }

    public String[] allColumns() {
        return ALL_COLUMNS;
    }
}
