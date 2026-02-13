package com.zoo.domain;

public class Lion extends Predator {

    private double maneLength;

    public Lion() {}

    public Lion(Integer id, String name, int age, double weightKg, double maneLength) {
        super(id, name, age, weightKg);
        setManeLength(maneLength);
    }

    public double getManeLength() { return maneLength; }
    public void setManeLength(double maneLength) {
        if (maneLength < 0) throw new IllegalArgumentException("Mane length cannot be negative");
        this.maneLength = maneLength;
    }

    @Override
    public String makeSound() {
        return "Roar";
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", maneLength=" + maneLength +
                '}';
    }
}
