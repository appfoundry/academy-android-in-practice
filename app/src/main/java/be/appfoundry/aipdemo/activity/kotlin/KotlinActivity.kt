package be.appfoundry.aipdemo.activity.kotlin

import android.support.v7.app.AppCompatActivity
import be.appfoundry.aipdemo.model.KotlinUser
import be.appfoundry.aipdemo.model.User


class KotlinActivity : AppCompatActivity() {

    private val userList = mutableListOf<KotlinUser>()

    private fun populateUserList() {
        userList.add(KotlinUser("Siebe Sysmans", 27))
        userList.add(KotlinUser("Charlie", 40))
        userList.add(KotlinUser("John Doe", 13))
    }

    fun KotlinUser.containsSpaces() = this.name.contains(" ")

    fun KotlinUser.isTeenager() = this.age in 10..19

    private fun onlyNamesWithSpaces(): List<KotlinUser> {
        return userList.filter { it.containsSpaces() }
    }

    private fun findUserWithName(nameToFind: String): KotlinUser? {
        return userList.find { it.name == nameToFind }
    }

    fun KotlinUser.getInfo() = "Hi, I'm ${this.name} and I'm ${this.age} old."

    private fun findUserOrCreate(nameToFind: String): KotlinUser {
        return findUserWithName(nameToFind) ?: KotlinUser("Unknown")
    }

    private fun kotlinToJava(kotlinUser: KotlinUser): User {
        return User(kotlinUser.name, kotlinUser.age)
    }
}