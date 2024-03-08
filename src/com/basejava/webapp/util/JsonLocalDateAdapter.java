package com.basejava.webapp.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateUtil.getFORMATTER();

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String[] parts = jsonElement.getAsString().split("/");
        return LocalDate.of(
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[0]), 1);
    }

    @Override
    public JsonElement serialize(LocalDate date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(date.format(FORMATTER));
    }
}
