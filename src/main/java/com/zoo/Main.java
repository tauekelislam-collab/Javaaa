package com.zoo;

import com.sun.net.httpserver.HttpServer;
import com.zoo.controller.AnimalController;
import com.zoo.controller.ZookeeperController;
import com.zoo.domain.Animal;
import com.zoo.domain.Elephant;
import com.zoo.domain.Lion;
import com.zoo.domain.Zookeeper;
import com.zoo.repository.AnimalRepositoryImpl;
import com.zoo.repository.CrudRepository;
import com.zoo.repository.ZookeeperRepositoryImpl;
import com.zoo.service.AnimalServiceImpl;
import com.zoo.service.ZookeeperServiceImpl;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {

        // ===== init layers =====
        CrudRepository<Animal> animalRepo = new AnimalRepositoryImpl();
        var animalService = new AnimalServiceImpl(animalRepo);

        var zookeeperRepo = new ZookeeperRepositoryImpl();
        var zookeeperService = new ZookeeperServiceImpl(zookeeperRepo);

        // ===== seed data (ONLY if tables are empty) =====
        if (animalService.getAll().isEmpty()) {
            animalService.create(new Lion(null, "Simba", 5, 200, 30));
            // или вместо льва:
            // animalService.create(new Elephant(null, "Dumbo", 12, 800, 1.5));
            System.out.println("[SEED] 1 animal inserted");
        } else {
            System.out.println("[SEED] animals table not empty -> skip");
        }

        if (zookeeperService.getAll().isEmpty()) {
            zookeeperService.create(new Zookeeper(null, "Alex Keeper", "alex@zoo.com", 7));
            System.out.println("[SEED] 1 zookeeper inserted");
        } else {
            System.out.println("[SEED] zookeepers table not empty -> skip");
        }

        // ===== start REST server =====
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/animals", new AnimalController(animalService));
        server.createContext("/zookeepers", new ZookeeperController(zookeeperService));
        server.start();

        System.out.println("REST API started at http://localhost:8080");
    }
}
