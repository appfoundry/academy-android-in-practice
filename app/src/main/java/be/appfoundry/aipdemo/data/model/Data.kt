package be.appfoundry.aipdemo.data.model

class Data<T>(
    val data: T,
    val source: Source
) {

    enum class Source {
        UNKNOWN, NETWORK, DATABASE
    }
}
