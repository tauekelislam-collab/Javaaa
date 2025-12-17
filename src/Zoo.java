public class Zoo {
    private String zooName;
    private Animal[] animals;
    private Zookeeper zookeeper;
    private int animalCount;
    public Zoo(String zooName, int capacity, Zookeeper zookeeper) {
        this.zooName = zooName;
        animals = new Animal[capacity];
        this.zookeeper = zookeeper;
        animalCount = 0;
    }
    public String getZooName() {
        return zooName;
    }
    public Animal[] getAnimals() {
        return animals;
    }
    public Zookeeper getZookeeper() {
        return zookeeper;
    }
    public int getAnimalCount() {
        return animalCount;
    }
    public void setZooName(String zooName) {
        this.zooName = zooName;
    }
    public void setZookeeper(Zookeeper zookeeper) {
        this.zookeeper = zookeeper;
    }
    public void addAnimal(Animal animal) {
        if (animalCount < animals.length) {
            animals[animalCount] = animal;
            animalCount = animalCount + 1;
        } else {
            System.out.println("Cannot add animal. Zoo is full.");
        }
    }
    public void feedAllAnimals() {
        if (animalCount == 0) {
            System.out.println("There are no animals to feed.");
        } else {
            for (int i = 0; i < animalCount; i++) {
                zookeeper.feedAnimal(animals[i]);
            }
        }
    }
}
