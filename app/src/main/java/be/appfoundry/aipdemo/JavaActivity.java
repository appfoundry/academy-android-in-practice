package be.appfoundry.aipdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import be.appfoundry.aipdemo.model.KotlinUser;
import be.appfoundry.aipdemo.model.User;


public class JavaActivity extends AppCompatActivity {

    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private boolean containsSpaces(User user) {
        return user.getName().contains(" ");
    }

    private boolean isTeenager(User user) {
        int age = user.getAge();
        return age >= 10 && age <= 19;
    }

    private void populateUserList() {
        userList.add(new User("Siebe Sysmans", 27));
        userList.add(new User("Charlie", 40));
        userList.add(new User("John Doe", 13));
    }

    private List<User> onlyNamesWithSpaces() {
        List<User> resultList = new ArrayList<>();
        for(User user: userList) {
            if(containsSpaces(user)) {
                resultList.add(user);
            }
        }
        return resultList;
    }

    private User findUserWithName(String nameToFind) {
        User result = null;
        for(User user: userList) {
            if(user.getName().equals(nameToFind)) {
                result = user;
                break;
            }
        }
        return result;
    }

    private String getInfo(User user) {
        return "Hi, I'm " + user.getName() + " and I'm " + user.getAge() + " old.";
    }

    private User findUserOrCreate(String nameToFind) {
        User result = findUserWithName(nameToFind);
        if(result == null) {
            result = new User(nameToFind);
        }
        return result;
    }

    private KotlinUser javaToKotlin(User user) {
        return new KotlinUser(user.getName(), user.getAge());
    }
}
