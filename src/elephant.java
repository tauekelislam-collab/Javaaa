public class elephant extends Animal {
    public elephant(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " trumpets!");
    }
}
