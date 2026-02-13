package com.zoo.domain;

public abstract class Herbivore extends Animal {

    protected Herbivore() {}

    protected Herbivore(Integer id, String name, int age, double weightKg) {
        super(id, name, age, weightKg);
    }

    @Override
    public String dietType() {
        return "Plants";
    }
}
