plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version embeddedKotlinVersion
    application
}

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
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.2.0")
}

application.mainClass.set("AppKt")
