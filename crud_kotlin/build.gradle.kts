import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.8.22"

	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	id("io.kotest") version "0.4.10"

	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	idea
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	val kotestVersion = "5.5.5"
	val querydslVersion = "5.0.0"

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.redisson:redisson-spring-boot-starter:3.23.1")

	implementation ("com.querydsl:querydsl-jpa:${querydslVersion}:jakarta")
	kapt ("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")
	kapt ("jakarta.annotation:jakarta.annotation-api")
	kapt ("jakarta.persistence:jakarta.persistence-api")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")

	testImplementation("io.kotest:kotest-runner-junit5-jvm:${kotestVersion}")
	testImplementation("io.kotest:kotest-assertions-core-jvm:${kotestVersion}")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

	testImplementation("io.mockk:mockk:1.13.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

idea {
	module {
		val kaptMain = file("build/generated/source/kapt/main")
		sourceDirs.add(kaptMain)
		generatedSourceDirs.add(kaptMain)
	}
}