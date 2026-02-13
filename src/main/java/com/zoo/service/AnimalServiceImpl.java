package com.zoo.service;

import com.zoo.domain.Animal;
import com.zoo.exception.EntityNotFoundException;
import com.zoo.repository.CrudRepository;
import com.zoo.service.AnimalService;

import java.util.List;

public class AnimalServiceImpl implements AnimalService {

    private final CrudRepository<Animal> repo;   // ✅ DIP: зависим от интерфейса

    public AnimalServiceImpl(CrudRepository<Animal> repo) {
        this.repo = repo;
    }

    @Override
    public Animal create(Animal animal) {
        return repo.save(animal);
    }

    @Override
    public List<Animal> getAll() {
        return repo.findAll();
    }

    @Override
    public Animal getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal with id " + id + " not found"));
    }

    @Override
    public Animal update(Animal animal) {
        getById(animal.getId());
        return repo.update(animal);
    }

    @Override
    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }

    @Override
    public String feed(Animal animal) {
        return "Feeding " + animal.getName() + " with " + animal.dietType()
                + ". Sound: " + animal.makeSound();
    }
}
