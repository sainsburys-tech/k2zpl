import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
    id("signing")
    id("version-catalog")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "info.mking.k2zpl"
version = "0.0.7"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation(libs.mockk)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
}

tasks.test {
    useJUnitPlatform()
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates("info.mking.k2zpl", "k2zpl", "0.0.7")
    pom {
        name.set("k2zpl")
        description.set("Kotlin DSL for ZPL (Zebra Programming Language)")
        inceptionYear.set("2024")
        url.set("https://github.com/itsmattking/k2zpl/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("itsmattking")
                name.set("Matt King")
                url.set("https://github.com/itsmattking/")
            }
        }
        scm {
            url.set("https://github.com/itsmattking/k2zpl/")
            connection.set("scm:git:git://github.com/itsmattking/k2zpl.git")
            developerConnection.set("scm:git:ssh://git@github.com/itsmattking/k2zpl.git")
        }
    }
}