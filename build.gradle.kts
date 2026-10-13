plugins {
    kotlin("jvm") version "2.1.20"
    java
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.jetbrains.lincheck:lincheck:3.7")
}

repositories {
    mavenCentral()
}

sourceSets.main {
    java.srcDir("src")
}

sourceSets.test {
    java.srcDir("test")
}

tasks {
    test {
        maxHeapSize = "10g"
        jvmArgs("-XX:+EnableDynamicAgentLoading")
        // Print the full Lincheck error explanation (incorrect results and the interleaving).
        testLogging {
            events("passed", "skipped", "failed")
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = false
        }
    }
}

kotlin {
    jvmToolchain(21)
}
