package com.basejava.webapp.util;

import com.basejava.webapp.model.Section;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;

public class JsonParser {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonFieldAdapter<>())
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {
    return GSON.fromJson(reader, clazz);
    }

    public static <T> T read(String content, Class<T> c) {
        return GSON.fromJson(content, c);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }

    public static <T> String write(T object, Class<T> c) {
        return GSON.toJson(object, c);
    }

    public static <T> void write(T object) {
        GSON.toJson(object);
    }

}
