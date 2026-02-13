package com.zoo.factory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.zoo.domain.Animal;
import com.zoo.domain.Elephant;
import com.zoo.domain.Lion;

public class AnimalFactory {

    private static final Gson gson = new Gson();

    private AnimalFactory() {}

    public static Animal create(JsonObject json) {

        String type = json.get("type").getAsString();

        if ("LION".equalsIgnoreCase(type)) {
            return gson.fromJson(json, Lion.class);
        }

        if ("ELEPHANT".equalsIgnoreCase(type)) {
            return gson.fromJson(json, Elephant.class);
        }

        throw new IllegalArgumentException("Unknown animal type");
    }
}
