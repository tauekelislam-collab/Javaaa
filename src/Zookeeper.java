public class Zookeeper {
    private int age;
    private int ex;
    private String name;
    public Zookeeper(int age,int ex,String name) {
        this.age=age;
        this.ex=ex;
        this.name=name;
    }
    public int getAge() {
        return age;
    }
    public int getEx() {
        return ex;
    }
    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void feedAnimal(Animal animal) {
        System.out.println(name + " feeds " + animal.getName());
        animal.makeSound();
    }
}
