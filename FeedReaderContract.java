package com.example.vanil_singh.datab;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    private FeedReaderContract() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "RaceCars";
        public static final String CARNAME = "carname";
        public static final String SPEED = "speed";
        public static final String TANKSIZE ="tanksize";
        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                        FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                        FeedEntry.CARNAME + TEXT_TYPE +
                        COMMA_SEP +
                        FeedEntry.SPEED + TEXT_TYPE +
                        COMMA_SEP +
                        FeedEntry.TANKSIZE + " INTEGER" + ")";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}

