package com.zoo.service;

import com.zoo.domain.Animal;

import java.util.List;

public interface AnimalService {
    Animal create(Animal animal);
    List<Animal> getAll();
    Animal getById(int id);
    Animal update(Animal animal);
    void delete(int id);

    // чисто чтобы показать полиморфизм на защите:
    String feed(Animal animal);
}
