package com.all_latest_in_android.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.all_latest_in_android.data.local.HeadlineDatabase;
import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.data.local.entity.SourceModelEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class HeadlineDaoTest {

    private HeadlineDatabase headlineDatabase;

    @Before
    public void init(){
        headlineDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), HeadlineDatabase.class).build();
    }

    @After
    public void uninIt(){
        headlineDatabase.close();
    }
    @Test
    public void testLoadHeadlineData(){
        List<HeadlineEntity> headlineEntities = new ArrayList<>();
        HeadlineEntity entity = new HeadlineEntity();
        SourceModelEntity sourceModelEntity = new SourceModelEntity();
        sourceModelEntity.setName("test_source");
        sourceModelEntity.setId(1000);
        entity.setSource(sourceModelEntity);
        entity.setAuthor("test_author");
        entity.setTitle("test_title");
        entity.setDescription("test_description");
        entity.setUrl("test_url");
        entity.setUrlToImage("test_url_to_image");
        entity.setPublishedAt("test_published_at");
        entity.setPublishedAt("test_content");
        headlineEntities.add(entity);

        headlineDatabase.headlineDao().saveHeadlines(headlineEntities);
        LiveData<List<HeadlineEntity>> headlineListData =  headlineDatabase.headlineDao().loadHeadlines();
        assertNotNull(headlineListData);
    }
}
