plugins {
	java
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "crypto.analytics"
version = "0.0.1-SNAPSHOT"
description = "Market Data Service"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2024.0.2"
extra["springCloudGcpVersion"] = "5.4.0"

dependencies {
    implementation("org.springframework.kafka:spring-kafka:3.3.11")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux") // WebClient
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Google Cloud
    implementation("com.google.cloud:spring-cloud-gcp-starter")
    implementation("com.google.cloud:spring-cloud-gcp-starter-secretmanager")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        mavenBom("com.google.cloud:spring-cloud-gcp-dependencies:${property("springCloudGcpVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
