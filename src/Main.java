public class Main {
    public static void main(String[] args) {
        Zookeeper keeper = new Zookeeper(30, 5, "Alex");
        Zoo zoo = new Zoo("Central Zoo", 2, keeper);
        Animal lion = new Animal("Leo", "Lion", 4);
        Animal elephant = new Animal("Dumbo", "Elephant", 10);
        zoo.addAnimal(lion);
        zoo.addAnimal(elephant);
        zoo.feedAllAnimals();
        // Comparison (requirement)
        if (lion.getAge() > elephant.getAge()) {
            System.out.println(lion.getName() + " is older than " + elephant.getName());
        } else {
            System.out.println(elephant.getName() + " is older than " + lion.getName());
        }
    }
}