package com.basejava.webapp.util;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonFieldAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive primitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = primitive.getAsString();

        try {
            Class<?> clazz = Class.forName(className);
            return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

    @Override
    public JsonElement serialize(T t, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject value = new JsonObject();
        value.addProperty(CLASSNAME, t.getClass().getName());
        JsonElement element = jsonSerializationContext.serialize(t);
        value.add(INSTANCE, element);
        return value;
    }
}
