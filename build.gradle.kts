plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
    id("signing")
}

group = "info.mking"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}