package com.all_latest_in_android.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.all_latest_in_android.data.local.dao.HeadlineDao;
import com.all_latest_in_android.data.local.entity.Converters;
import com.all_latest_in_android.data.local.entity.HeadlineEntity;

@Database(entities = {HeadlineEntity.class}, version = 3, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class HeadlineDatabase extends RoomDatabase {
    public abstract HeadlineDao headlineDao();

    //when we change the version of Database, we have to provide the migration
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };

}