package be.appfoundry.aipdemo.model


class KotlinUser(val name: String, val age: Int = -1) {

  fun canVote() = age >= 18
}