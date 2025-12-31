public class Main {
    public static void main(String[] args) {

        Zookeeper keeper = new Zookeeper("Alex");
        Zoo zoo = new Zoo("Central Zoo", keeper);

        Animal lion1 = new lion("Leo", 4);
        Animal lion2 = new lion("Leo", 4); // одинаковые данные, но разные ID
        Animal elephant = new elephant("Dumbo", 10);

        zoo.addAnimal(lion1);
        zoo.addAnimal(lion2);
        zoo.addAnimal(elephant);

        zoo.feedAllAnimals();

        System.out.println("\nCompare lion1 and lion2:");
        System.out.println(lion1.equals(lion2)); // false

        System.out.println("\nFind by id " + lion1.getId() + ":");
        System.out.println(zoo.findById(lion1.getId()));
    }
}
