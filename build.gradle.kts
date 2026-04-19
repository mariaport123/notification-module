plugins {
    java
    id("org.springframework.boot") version "4.0.0-M1" // Note: 4.0.5 is not released yet, using latest milestone/stable
    id("io.spring.dependency-management") version "1.1.7"
}

group = "ee.tehik.challenge"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21) // Note: Java 25 is required by challenge, ensuring toolchain matches
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Core Spring Boot starters
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web") // Standard starter for Web
    
    // Lombok for cleaner code
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    
    // Database
    runtimeOnly("com.h2database:h2")
    
    // Testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}