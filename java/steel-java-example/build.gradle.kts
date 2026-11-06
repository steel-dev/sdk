plugins {
    id("steel.java")
    application
}

dependencies {
    implementation(project(":steel-java"))
}

application {
    mainClass = "dev.steel.example.${findProperty("example") ?: "Quickstart"}"
}

tasks.withType<Javadoc>().configureEach {
    enabled = false
}
