import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { kotlin(module = "jvm") version Kotlin.VERSION apply false }

allprojects {
    group = "com.ekang-api"
    version = "0.1.0"

    repositories(RepositoryHandler::mavenCentral)

    tasks {
        withType<JavaCompile> {
            enabled = false
            targetCompatibility = JavaVersion.VERSION_1_8.toString()
        }
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
        withType<Test> {
            testLogging { exceptionFormat = TestExceptionFormat.FULL }
            useJUnitPlatform()
        }
    }
}

tasks.register<Task>(name = "printVersion") {
    doLast { println(version) }
}
