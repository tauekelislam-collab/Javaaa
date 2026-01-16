public class Main {

    public static void main(String[] args) {

        // ===== OOP PART =====
        Zookeeper keeper = new Zookeeper("Alex");
        Zoo zoo = new Zoo("Central Zoo", keeper);

        Animal lion1 = new lion("Leo", 4);
        Animal lion2 = new lion("Leo", 4);
        Animal elephant = new elephant("Dumbo", 10);

        zoo.addAnimal(lion1);
        zoo.addAnimal(lion2);
        zoo.addAnimal(elephant);

        zoo.feedAllAnimals();

        System.out.println("\nCompare lion1 and lion2:");
        System.out.println(lion1.equals(lion2));

        System.out.println("\nFind by id " + lion1.getId() + ":");
        System.out.println(zoo.findById(lion1.getId()));

        // ===== DATABASE PART =====
        try {
            AnimalRepository repo = new AnimalRepository();

            System.out.println("\n=== SAVE TO DATABASE ===");

            repo.save(new lion("Simba", 5), 1);
            repo.save(new elephant("Bimbo", 8), null);

            System.out.println("\n=== FROM DATABASE ===");
            for (Animal a : repo.findAll()) {
                System.out.println(a.getName() + " age=" + a.getAge());
            }

            System.out.println("\n=== UPDATE AGE ID=1 ===");
            repo.updateAge(1, 6);

            System.out.println("\n=== DELETE ID=2 ===");
            repo.delete(2);

        } catch (Exception e) {
            System.out.println("Database error:");
            e.printStackTrace();
        }
    }
}
