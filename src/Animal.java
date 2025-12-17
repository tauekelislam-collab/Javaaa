public class Animal {
    private String name;
    private String species;
    private int age;
    public Animal(String name, String species, int age) {
        this.name = name;
        this.species = species;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getSpecies() {
        return species;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSpecies(String species) {
        this.species = species;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void makeSound() {
        System.out.println(name + " makes a sound.");
    }
}