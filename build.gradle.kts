plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    testImplementation("com.h2database:h2:2.2.224")
    implementation("mysql:mysql-connector-java:8.0.33")
    compileOnly("org.projectlombok:lombok:1.18.30")


}

tasks.test {
    useJUnitPlatform()
}