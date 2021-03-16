import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val dotenv_kotlin_version: String by project
val kotlin_telegram_bot_version: String by project
val logback_version: String by project
val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.4.30"
}

group = "io.kraftsman"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://dl.bintray.com/kotlin/ktor")
}

dependencies {
    implementation("io.github.cdimascio:dotenv-kotlin:$dotenv_kotlin_version")
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:$kotlin_telegram_bot_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
