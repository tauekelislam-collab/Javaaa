public class Zookeeper {
    private String name;

    public Zookeeper(String name) {
        this.name = name;
    }

    public void feedAnimal(Animal animal) {
        System.out.print(name + " feeds " + animal.getName() + " (id=" + animal.getId() + "). ");
        animal.makeSound();
    }
}
