public class lion extends Animal {
    public lion(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " roars!");
    }
}
