package com.zoo.pool;

import com.zoo.domain.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnimalPool {

    private final List<Animal> animals = new ArrayList<>();

    // ================= ADD =================

    public void add(Animal animal) {
        animals.add(animal);
    }

    public void addAll(List<Animal> animals) {
        this.animals.addAll(animals);
    }

    public List<Animal> getAll() {
        return new ArrayList<>(animals);
    }

    // ================= SEARCH =================

    public Optional<Animal> findByName(String name) {
        if (name == null) return Optional.empty();

        String n = name.trim().toLowerCase();

        return animals.stream()
                .filter(a -> a.getName() != null &&
                        a.getName().toLowerCase().equals(n))
                .findFirst();
    }

    public List<Animal> findByType(Class<? extends Animal> type) {
        return animals.stream()
                .filter(type::isInstance)
                .collect(Collectors.toList());
    }

    // ================= FILTER =================

    public List<Animal> filterByMinAge(int minAge) {
        return animals.stream()
                .filter(a -> a.getAge() >= minAge)
                .collect(Collectors.toList());
    }

    // 🔥 ЭТОТ МЕТОД ТРЕБУЕТ MAIN
    public List<Animal> filterByAge(int minAge) {
        return filterByMinAge(minAge);
    }

    public List<Animal> filterByWeightRange(double minKg, double maxKg) {
        return animals.stream()
                .filter(a -> a.getWeightKg() >= minKg &&
                        a.getWeightKg() <= maxKg)
                .collect(Collectors.toList());
    }

    // ================= SORT =================

    public List<Animal> sortByAgeAsc() {
        return animals.stream()
                .sorted(Comparator.comparingInt(Animal::getAge))
                .collect(Collectors.toList());
    }

    public List<Animal> sortByNameAsc() {
        return animals.stream()
                .sorted(Comparator.comparing(
                        a -> a.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    // 🔥 ЭТОТ МЕТОД ТРЕБУЕТ MAIN
    public List<Animal> sortByName() {
        return sortByNameAsc();
    }
}
