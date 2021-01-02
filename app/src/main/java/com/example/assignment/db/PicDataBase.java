package com.example.assignment.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assignment.PicBean;




@Database(entities = {PicBean.class}, version = 1)
public abstract class PicDataBase extends RoomDatabase {
    private static volatile PicDataBase INSTANCE;
    public static PicDataBase getDatabase(Application application) {
        if (INSTANCE == null) {
            synchronized (PicDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application, PicDataBase.class, "Pic.db")
                            // Set whether query operations are allowed in the main thread设置是否允许在主线程做查询操作
                            .allowMainThreadQueries()
                            // Set the logic of database upgrade (migration)设置数据库升级(迁移)的逻辑
//                            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                            // setJournalMode(@NonNull JournalMode journalMode)  设置数据库的日志模式
                            // Set up the migration database. If an error occurs, the database will be recreated instead of a crash设置迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                            // .fallbackToDestructiveMigration() will cleans the data in the table and is not recommended会清理表中的数据 ，不建议这样做
                            //Set up to migrate the database from a certain version. If an error occurs, the database will be recreated instead of a crash设置从某个版本开始迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                            //.fallbackToDestructiveMigrationFrom(int... startVersions);
                            .addCallback(new RoomDatabase.Callback() {
                                // Open and create the database to listen进行数据库的打开和创建监听
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                }

                                @Override
                                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                                    super.onOpen(db);
                                }
                            })

                            //The default value is frameworksqliteopenhelperfactory, which sets the factory of the database. 默认值是FrameworkSQLiteOpenHelperFactory，设置数据库的factory。
                            // For example, if we want to change the storage path of the database, we can implement this function 比如我们想改变数据库的存储路径可以通过这个函数来实现
                            // .openHelperFactory(SupportSQLiteOpenHelper.Factory factory);
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

    public abstract PicDao getUserDao();
}