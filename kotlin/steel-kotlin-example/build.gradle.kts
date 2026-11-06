plugins {
    id("steel.kotlin")
    application
}

dependencies {
    implementation(project(":steel-kotlin"))
}

application {
    mainClass = "dev.steel.example.${findProperty("example") ?: "Quickstart"}Kt"
}

tasks.withType<Javadoc>().configureEach {
    enabled = false
}
