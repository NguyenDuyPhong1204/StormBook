plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.api"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// Spring Boot Test
	testImplementation ("org.springframework.boot:spring-boot-starter-test")

	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	//mail
	implementation("org.springframework.boot:spring-boot-starter-mail")

	//redis
//	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	// OAuth2 client cho Spring Boot
	implementation ("org.springframework.boot:spring-boot-starter-oauth2-client")

	// Google API Client
	implementation ("com.google.api-client:google-api-client:2.2.0")
	implementation ("com.google.oauth-client:google-oauth-client:1.34.1")
	implementation ("com.google.http-client:google-http-client-gson:1.43.1")
	//
	implementation("com.mysql:mysql-connector-j:8.3.0")


}

tasks.withType<Test> {
	useJUnitPlatform()
}
