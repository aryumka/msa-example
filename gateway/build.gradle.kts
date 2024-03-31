import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"
}

group = "aryumka"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.1"

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Gateway
	implementation("org.springframework.cloud:spring-cloud-starter-gateway")

	// Actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Discovery
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	// Config
	implementation("org.springframework.cloud:spring-cloud-starter-config")

	// Cloud Bus using RabbitMQ
	implementation("org.springframework.cloud:spring-cloud-bus")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
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
