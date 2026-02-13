package com.zoo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.zoo.domain.Animal;
import com.zoo.factory.AnimalFactory;
import com.zoo.service.AnimalService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AnimalController implements HttpHandler {

    private static final String BASE = "/animals";

    private final AnimalService service;
    private final Gson gson = new Gson();

    public AnimalController(AnimalService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

// убираем все "/" в конце: "/animals/" -> "/animals"
        path = path.replaceAll("/+$", "");
        if (path.isEmpty()) path = "/";


        // ЛОГ чтобы ты видел в консоли
        System.out.println("[API] " + method + " " + path);

        try {

            // ===== /animals =====
            if (path.equals(BASE)) {

                if ("GET".equals(method)) {
                    List<Animal> list = service.getAll();
                    sendJson(exchange, 200, gson.toJson(list));
                    return;
                }

                if ("POST".equals(method)) {
                    Animal animal = readAnimal(exchange);
                    Animal created = service.create(animal);
                    sendJson(exchange, 201, gson.toJson(created));
                    return;
                }

                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            // ===== /animals/{id} =====
            if (path.startsWith(BASE + "/")) {

                int id = Integer.parseInt(path.substring((BASE + "/").length()));

                if ("GET".equals(method)) {
                    sendJson(exchange, 200, gson.toJson(service.getById(id)));
                    return;
                }

                if ("PUT".equals(method)) {
                    Animal updated = readAnimal(exchange);
                    updated.setId(id);
                    sendJson(exchange, 200, gson.toJson(service.update(updated)));
                    return;
                }

                if ("DELETE".equals(method)) {
                    service.delete(id);
                    sendJson(exchange, 200, "{\"message\":\"Deleted\"}");
                    return;
                }

                sendJson(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            // ===== unknown path =====
            sendJson(exchange, 404, "{\"error\":\"Not found\"}");

        } catch (Exception e) {
            e.printStackTrace();
            sendJson(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private Animal readAnimal(HttpExchange exchange) throws IOException {
        var reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        JsonObject json = gson.fromJson(reader, JsonObject.class);
        return AnimalFactory.create(json);
    }

    private void sendJson(HttpExchange exchange, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
        exchange.sendResponseHeaders(code, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
