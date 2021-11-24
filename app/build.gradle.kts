plugins {
    kotlin(module = "jvm")
    kotlin(module = "plugin.serialization") version Kotlin.VERSION
    application
}

dependencies {
    setOf(ARROW, CSV, Ktor.NETTY, Ktor.SERIALIZATION, LOGBACK)
        .forEach(this::implementation)

    setOf(kotlin(module = "test"), Ktor.TEST)
        .forEach(this::testImplementation)
}

application.mainClass.set("AppKt")
