plugins {
    kotlin("jvm") version "1.9.23"
    id("maven-publish")
    id("signing")
    id("version-catalog")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "com.sainsburys"
version = "0.2.5"

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

publishing {
    repositories {
        maven("https://maven.pkg.github.com/sainsburys-tech/k2zpl") {
            name = "GitHubPackages"
            credentials {
                password = System.getenv("GITHUB_TOKEN")
                username = System.getenv("GITHUB_ACTOR")
            }
        }
    }
}
mavenPublishing {
    coordinates("com.sainsburys", "k2zpl", "0.2.5")
    pom {
        name.set("k2zpl")
        description.set("Kotlin DSL for ZPL (Zebra Programming Language)")
        inceptionYear.set("2024")
        url.set("https://github.com/sainsburys-tech/k2zpl/")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/sainsburys-tech/k2zpl/blob/main/LICENSE")
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
            url.set("https://github.com/sainsburys-tech/k2zpl/")
            connection.set("scm:git:git://github.com/sainsburys-tech/k2zpl.git")
            developerConnection.set("scm:git:ssh://git@github.com/sainsburys-tech/k2zpl.git")
        }
    }
}
