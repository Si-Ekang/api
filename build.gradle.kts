import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm") version embeddedKotlinVersion
    kotlin("plugin.serialization") version embeddedKotlinVersion
    application
}

group = "com.ekang-api"
version = "1.2.1"

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

    // Kotools
    fun kotools(module: String, version: String): String =
        "io.github.kotools:$module:$version"
    implementation(kotools("csv", "[2.1,2.2["))
    implementation(kotools("types", "[2.0,2.1["))
    testImplementation(kotools("assert", "[2.1,2.2["))

    // Logging
    implementation("ch.qos.logback:logback-classic:1.2.10")
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
