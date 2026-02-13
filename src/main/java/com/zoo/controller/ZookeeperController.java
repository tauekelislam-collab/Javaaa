package com.zoo.controller;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.zoo.domain.Zookeeper;
import com.zoo.service.ZookeeperServiceImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ZookeeperController implements HttpHandler {

    private final ZookeeperServiceImpl service;
    private final Gson gson = new Gson();

    public ZookeeperController(ZookeeperServiceImpl service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        try {

            if ("GET".equals(method) && path.equals("/zookeepers")) {
                List<Zookeeper> list = service.getAll();
                sendJson(exchange, 200, gson.toJson(list));
                return;
            }

            if ("POST".equals(method) && path.equals("/zookeepers")) {
                var reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                Zookeeper zk = gson.fromJson(reader, Zookeeper.class);
                sendJson(exchange, 201, gson.toJson(service.create(zk)));
                return;
            }

            if (path.startsWith("/zookeepers/")) {

                int id = Integer.parseInt(path.substring("/zookeepers/".length()));

                if ("GET".equals(method)) {
                    sendJson(exchange, 200, gson.toJson(service.getById(id)));
                    return;
                }

                if ("PUT".equals(method)) {
                    var reader = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                    Zookeeper zk = gson.fromJson(reader, Zookeeper.class);
                    zk.setId(id);
                    sendJson(exchange, 200, gson.toJson(service.update(zk)));
                    return;
                }

                if ("DELETE".equals(method)) {
                    service.delete(id);
                    sendJson(exchange, 200, "{\"message\":\"Deleted\"}");
                    return;
                }
            }

            sendJson(exchange, 404, "{\"error\":\"Not found\"}");

        } catch (Exception e) {
            e.printStackTrace();
            sendJson(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    private void sendJson(HttpExchange exchange, int code, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(code, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
