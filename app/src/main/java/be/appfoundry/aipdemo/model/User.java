package be.appfoundry.aipdemo.model;

public class User {

    private String name;
    private int age;

    public User(String name) {
        this(name, -1);
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean canVote() {
        return age >= 18;
    }

}
