import java.util.*;

public class Zoo {
    private String name;
    private Zookeeper zookeeper;
    private List<Animal> animals = new ArrayList<>();

    public Zoo(String name, Zookeeper zookeeper) {
        this.name = name;
        this.zookeeper = zookeeper;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public Animal findById(int id) {
        for (Animal a : animals) {
            if (a.getId() == id) return a;
        }
        return null;
    }

    public List<Animal> sortByAge() {
        animals.sort(Comparator.comparingInt(Animal::getAge));
        return animals;
    }

    public void feedAllAnimals() {
        for (Animal a : animals) {
            zookeeper.feedAnimal(a);
        }
    }
}
