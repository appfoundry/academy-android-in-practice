package be.appfoundry.aipdemo.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLogger @Inject constructor(val user: User) {

    fun log() = "Hi, my name is ${user.name}"
}