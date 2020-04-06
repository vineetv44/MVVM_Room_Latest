package com.all_latest_in_android.data.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.all_latest_in_android.data.local.entity.HeadlineEntity;

import java.util.List;

@Dao
public interface HeadlineDao {
    @Query("SELECT * FROM headlines")
    LiveData<List<HeadlineEntity>> loadHeadlines();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveHeadlines(List<HeadlineEntity> headlineEntities);

}
