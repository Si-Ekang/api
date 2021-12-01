const val CSV: String = "com.github.doyaaaaaken:kotlin-csv-jvm:[1.2, 1.3["
const val LOGBACK: String = "ch.qos.logback:logback-classic:[1.2, 1.3["

object Kotlin {
    const val VERSION: String = "1.5.31"
}

object Ktor {
    private const val GROUP: String = "io.ktor"
    private const val VERSION: String = "1.6.3"

    const val NETTY: String = "$GROUP:ktor-server-netty:$VERSION"
    const val SERIALIZATION: String = "$GROUP:ktor-serialization:$VERSION"
    const val TEST: String = "$GROUP:ktor-server-test-host:$VERSION"
}
