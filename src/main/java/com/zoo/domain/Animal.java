package com.zoo.domain;

import java.util.Objects;

public abstract class Animal {
    private Integer id;
    private String name;
    private int age;
    private double weightKg;

    protected Animal() {}

    protected Animal(Integer id, String name, int age, double weightKg) {
        this.id = id;
        setName(name);
        setAge(age);
        setWeightKg(weightKg);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; } // id обычно ставит БД

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Animal name cannot be empty");
        this.name = name.trim();
    }

    public int getAge() { return age; }
    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
        this.age = age;
    }

    public double getWeightKg() { return weightKg; }
    public void setWeightKg(double weightKg) {
        if (weightKg <= 0) throw new IllegalArgumentException("Weight must be > 0");
        this.weightKg = weightKg;
    }

    // Полиморфизм: разные животные реализуют по-разному
    public abstract String makeSound();

    // Ещё один полиморфный метод
    public abstract String dietType(); // "Meat" / "Plants"

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weightKg=" + weightKg +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(id, animal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
