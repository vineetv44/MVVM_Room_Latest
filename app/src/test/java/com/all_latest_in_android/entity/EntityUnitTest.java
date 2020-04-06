package com.all_latest_in_android.entity;

import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.data.local.entity.SourceModelEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static junit.framework.Assert.assertEquals;

@RunWith(JUnit4.class)
public class EntityUnitTest {


    @Test
    public void testSourceModelEntity(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        SourceModelEntity sourceModelEntity = new SourceModelEntity();
        sourceModelEntity.setId(1000);
        sourceModelEntity.setName("test_source");
        headlineEntity.setSource(sourceModelEntity);
        assertEquals(headlineEntity.getSource(), sourceModelEntity);
    }

    @Test
    public void testAuthor(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setAuthor("test_author");
        assertEquals(headlineEntity.getAuthor(), "test_author");
    }

    @Test
    public void testTitle(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setTitle("test_title");
        assertEquals(headlineEntity.getTitle(), "test_title");
    }

    @Test
    public void testDescription(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setDescription("test_description");
        assertEquals(headlineEntity.getDescription(), "test_description");
    }

    @Test
    public void testUrl(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setUrl("test_url");
        assertEquals(headlineEntity.getUrl(), "test_url");
    }

    @Test
    public void testUrlToImage(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setUrlToImage("test_url_to_image");
        assertEquals(headlineEntity.getUrlToImage(), "test_url_to_image");
    }

    @Test
    public void testPublishAt(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setPublishedAt("test_published_at");
        assertEquals(headlineEntity.getPublishedAt(), "test_published_at");
    }

    @Test
    public void testContent(){
        HeadlineEntity headlineEntity = new HeadlineEntity();
        headlineEntity.setContent("test_content");
        assertEquals(headlineEntity.getContent(), "test_content");
    }
}
