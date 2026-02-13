package com.zoo.domain;

public abstract class Predator extends Animal {

    protected Predator() {}

    protected Predator(Integer id, String name, int age, double weightKg) {
        super(id, name, age, weightKg);
    }

    @Override
    public String dietType() {
        return "Meat";
    }
}
