package com.zoo.domain;

public class Elephant extends Herbivore {

    private double trunkLength;

    public Elephant() {}

    public Elephant(Integer id, String name, int age, double weightKg, double trunkLength) {
        super(id, name, age, weightKg);
        setTrunkLength(trunkLength);
    }

    public double getTrunkLength() { return trunkLength; }
    public void setTrunkLength(double trunkLength) {
        if (trunkLength < 0) throw new IllegalArgumentException("Trunk length cannot be negative");
        this.trunkLength = trunkLength;
    }

    @Override
    public String makeSound() {
        return "Trumpet";
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", trunkLength=" + trunkLength +
                '}';
    }
}
