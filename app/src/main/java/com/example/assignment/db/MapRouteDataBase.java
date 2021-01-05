package com.example.assignment.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.example.assignment.RouteBean;

@Database(entities = {RouteBean.class}, version = 1)
public abstract class MapRouteDataBase extends RoomDatabase {
    private static volatile MapRouteDataBase INSTANCE;
    public static MapRouteDataBase getDatabase(Application application) {
        if (INSTANCE == null) {
            synchronized (PicDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application, MapRouteDataBase.class, "RouteBean.db")
                            .allowMainThreadQueries()
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                }
                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

    public abstract MapRouteDao getMapRouteDao();
}
