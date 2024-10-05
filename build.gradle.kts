plugins {
    id("java")
    application
}

application {
    mainClass.set("ru.aston.KeyboardLogger")
}

group = "ru.aston"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    runtimeOnly("com.github.kwhat:jnativehook:2.2.2")
    implementation("com.github.kwhat:jnativehook:2.2.2")
}

tasks.test {
    useJUnitPlatform()
}