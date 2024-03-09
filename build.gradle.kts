plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

group = "com.tablenine"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation ("org.springframework.cloud:spring-cloud-config-server")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<JavaCompile> {
	options.compilerArgs.add("--enable-preview")
	sourceCompatibility = JavaVersion.VERSION_21.toString()
	targetCompatibility = JavaVersion.VERSION_21.toString()
}

tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs("--enable-preview")
}

tasks.withType<JavaExec> {
	jvmArgs("--enable-preview")
}
