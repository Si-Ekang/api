import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm") version embeddedKotlinVersion
    kotlin("plugin.serialization") version embeddedKotlinVersion
    application
}

group = "com.ekang-api"
version = "1.2.0"

repositories(RepositoryHandler::mavenCentral)

dependencies {
    // Kotlin
    implementation(platform(kotlin("bom")))
    testImplementation(kotlin("test"))

    // Ktor
    fun ktor(module: String): String = "io.ktor:ktor-$module"
    implementation(platform("${ktor("bom")}:1.6.3"))
    implementation(ktor("server-netty"))
    implementation(ktor("serialization"))
    testImplementation(ktor("server-test-host"))

    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.10")

    // CSV
    implementation("io.github.kotools:csv:2.1.0")
}

application.mainClass.set("AppKt")

tasks {
    compileJava { enabled = false }
    compileTestJava { enabled = false }
    register("printVersion") {
        doLast { print(version) }
    }
    test {
        testLogging.exceptionFormat = TestExceptionFormat.FULL
        useJUnitPlatform()
    }
}
