plugins {
    java
    kotlin("jvm") version "1.4.20"
    application
}

group = "net.profitview"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        setUrl("https://jitpack.io/")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("commons-codec:commons-codec:1.14")
    implementation("org.json:json:20201115")
    implementation ("com.github.jkcclemens:khttp:-SNAPSHOT")
    implementation("io.socket:socket.io-client:1.0.0")
    implementation("com.tictactec:ta-lib:0.4.0")
    testImplementation("junit", "junit", "4.12")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application {
    mainClass.set("webinar1.ExecuteExampleKt")
}