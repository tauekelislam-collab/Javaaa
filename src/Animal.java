public abstract class Animal {
    private static int counter = 1;

    private final int id;
    private String name; 
    private int age;

    public Animal(String name, int age) {
        this.id = counter++;
        this.name = name;
        this.age = age;
    }

    public final int getId() { return id; }
    public final String getName() { return name; }
    public int getAge() { return age; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public abstract void makeSound();

    @Override
    public String toString() {
        return "Animal{id=" + id + ", name='" + name + "', age=" + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal other = (Animal) o;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
