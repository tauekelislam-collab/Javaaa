package com.zoo;

import com.sun.net.httpserver.HttpServer;
import com.zoo.controller.AnimalController;
import com.zoo.controller.ZookeeperController;
import com.zoo.domain.Animal;
import com.zoo.domain.Elephant;
import com.zoo.domain.Lion;
import com.zoo.domain.Zookeeper;
import com.zoo.pool.AnimalPool;
import com.zoo.repository.AnimalRepositoryImpl;
import com.zoo.repository.CrudRepository;
import com.zoo.repository.ZookeeperRepositoryImpl;
import com.zoo.service.AnimalServiceImpl;
import com.zoo.service.ZookeeperServiceImpl;

import java.net.InetSocketAddress;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("===== ZOO MANAGEMENT DEMO START =====");

        // ===== INIT LAYERS =====
        CrudRepository<Animal> animalRepo = new AnimalRepositoryImpl();
        AnimalServiceImpl animalService = new AnimalServiceImpl(animalRepo);

        ZookeeperRepositoryImpl zookeeperRepo = new ZookeeperRepositoryImpl();
        ZookeeperServiceImpl zookeeperService = new ZookeeperServiceImpl(zookeeperRepo);

        AnimalPool pool = new AnimalPool();

        // ===== CONSOLE CRUD DEMO =====

        System.out.println("\n--- CREATE DEMO ---");

        Animal lion = animalService.create(
                new Lion(null, "ConsoleSimba", 6, 210, 37)
        );

        Animal elephant = animalService.create(
                new Elephant(null, "ConsoleDumbo", 12, 800, 1.5)
        );

        Zookeeper zk = zookeeperService.create(
                new Zookeeper(null, "ConsoleKeeper", "keeper@zoo.com", 5)
        );

        System.out.println("Created Animal: " + lion);
        System.out.println("Created Animal: " + elephant);
        System.out.println("Created Zookeeper: " + zk);

        // ===== READ DEMO =====
        System.out.println("\n--- GET ALL ANIMALS ---");
        List<Animal> animals = animalService.getAll();
        animals.forEach(System.out::println);

        // ===== POLYMORPHISM DEMO =====
        System.out.println("\n--- POLYMORPHISM (makeSound) ---");
        for (Animal a : animals) {
            System.out.println(a.getName() + " says " + a.makeSound());
        }

        // ===== POOL DEMO (Streams/Lambda) =====
        System.out.println("\n--- ANIMAL POOL DEMO ---");

        pool.addAll(animals);

        System.out.println("Filter age >= 6:");
        pool.filterByAge(6).forEach(System.out::println);

        System.out.println("\nSort by name:");
        pool.sortByName().forEach(System.out::println);

        // ===== UPDATE DEMO =====
        System.out.println("\n--- UPDATE DEMO ---");
        lion.setName("UpdatedConsoleSimba");
        Animal updated = animalService.update(lion);
        System.out.println("Updated Animal: " + updated);

        // ===== DELETE DEMO =====
        System.out.println("\n--- DELETE DEMO ---");
        animalService.delete(elephant.getId());
        System.out.println("Deleted Animal with id=" + elephant.getId());

        System.out.println("\nRemaining animals:");
        animalService.getAll().forEach(System.out::println);

        // ===== START REST SERVER =====
        System.out.println("\n===== STARTING REST API =====");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/animals", new AnimalController(animalService));
        server.createContext("/zookeepers", new ZookeeperController(zookeeperService));
        server.start();

        System.out.println("REST API started at http://localhost:8080");
    }
}
