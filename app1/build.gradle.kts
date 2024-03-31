import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.23"
	kotlin("plugin.spring") version "1.9.23"

	id("com.google.protobuf") version "0.8.18"
}

group = "aryumka"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

extra["springCloudVersion"] = "2023.0.1"

dependencies {
	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// Config Client
	implementation("org.springframework.cloud:spring-cloud-starter-config")

	// Discovery Client
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	// Mongo
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

	// Cloud Bus using RabbitMQ
	implementation("org.springframework.cloud:spring-cloud-bus")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-rabbit")

	// gRPC
	implementation("io.grpc:grpc-protobuf:1.58.0")
	implementation("io.grpc:grpc-stub:1.58.0")
	implementation("io.grpc:grpc-kotlin-stub:1.2.0")
	implementation("net.devh:grpc-client-spring-boot-starter:3.0.0.RELEASE")

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
		jvmTarget = "21"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.23.4"
	}

	generatedFilesBaseDir = "$projectDir/src/generated"

	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.58.0"
		}
	}

	generateProtoTasks {
		all().forEach {
			it.plugins {
				id("grpc")
			}
		}
	}
}

