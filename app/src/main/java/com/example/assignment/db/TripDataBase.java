package com.example.assignment.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assignment.TripBean;

/**
 * @Author: Qibin Liang
 * @methodName: TripDataBase
 * @Description: this class is used to get the TripDataBase instance
 * **/
@Database(entities = {TripBean.class}, version = 1)
public abstract class TripDataBase extends RoomDatabase {
    private static volatile TripDataBase INSTANCE;
    public static TripDataBase getDatabase(Application application) {
        if (INSTANCE == null) {
            synchronized (PicDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application, TripDataBase.class, "TripBean.db")
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

    public abstract TripDao getTripDao();
}
