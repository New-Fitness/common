plugins {
    `java-library`
    `maven-publish`
}

group = "org.mlgym"
version = "1.0.0" // при изменении DTO повышай версию

java {
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "common"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/New-Fitness/common")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("PACKAGE_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("PACKAGE_TOKEN")
            }
        }
    }
}

dependencies {
    compileOnly("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}