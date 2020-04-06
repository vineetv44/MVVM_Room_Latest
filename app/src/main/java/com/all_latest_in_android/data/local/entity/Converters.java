package com.all_latest_in_android.data.local.entity;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Converters {
    @TypeConverter
    public static SourceModelEntity fromString(String value) {
        Type listType = new TypeToken<SourceModelEntity>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayLisr(SourceModelEntity sourceModelEntity) {
        Gson gson = new Gson();
        String json = gson.toJson(sourceModelEntity);
        return json;
    }
}
