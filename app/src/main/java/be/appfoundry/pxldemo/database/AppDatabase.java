package be.appfoundry.pxldemo.database;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.DATABASE_NAME, version = AppDatabase.VERSION)
public class AppDatabase {

    public static final String DATABASE_NAME = "PXLDemoDatabase";
    public static final int VERSION = 1;

}
