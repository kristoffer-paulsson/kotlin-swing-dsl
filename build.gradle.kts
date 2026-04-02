plugins {
    kotlin("jvm") version "1.5.31"
}

tasks.register("runAllSteps") {
    group = "build"
    description = "Runs all ten steps"
    doLast {
        for (i in 1..10) {
            println("Running step $i")
        }
    }
}

test {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
}