package com.zoo.builder;

import com.zoo.domain.Lion;

public class LionBuilder {

    private Integer id;
    private String name;
    private int age;
    private double weightKg;
    private double maneLength;

    public LionBuilder id(Integer id) {
        this.id = id;
        return this;

    }

    public LionBuilder name(String name) {
        this.name = name;
        return this;
    }

    public LionBuilder age(int age) {
        this.age = age;
        return this;
    }

    public LionBuilder weight(double weightKg) {
        this.weightKg = weightKg;
        return this;
    }

    public LionBuilder mane(double maneLength) {
        this.maneLength = maneLength;
        return this;
    }

    public Lion build() {
        return new Lion(id, name, age, weightKg, maneLength);
    }
}
