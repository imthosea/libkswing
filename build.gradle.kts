import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.SourcesJar

plugins {
	`java-library`
	id("org.jetbrains.kotlin.jvm") version "2.2.0"
	id("org.jetbrains.kotlin.plugin.allopen") version "2.1.21"
	id("org.jetbrains.dokka") version "2.2.0"
	id("com.vanniktech.maven.publish") version "0.36.0"
	signing
}

group = "me.thosea"
version = "1.0.0"

repositories {
	mavenCentral()
}

kotlin {
	jvmToolchain(17)
	allOpen {
		annotation("me.thosea.kswing.other.FullyOpen")
	}
}

java {
	withSourcesJar()
}
tasks.processResources {
	from("LICENSE")
}
tasks.getByName<Jar>("sourcesJar") {
	from("LICENSE")
}

signing {
	useGpgCmd()
}

mavenPublishing {
	publishToMavenCentral()
	signAllPublications()
	coordinates("io.github.imthosea", "libkswing")

	pom {
		name = "libkswing"
		description = "Kotlin library for Java Swing"
		url = "https://github.com/imthosea/libkswing"
		licenses {
			license {
				name = "BSD 2-clause"
				url = "https://github.com/imthosea/libkswing/blob/master/LICENSE"
			}
		}
		developers {
			developer {
				id = "thosea"
				name = "Thosea"
				url.set("https://github.com/imthosea/")
			}
		}
		scm {
			url = "https://github.com/imthosea/libkswing"
			connection = "scm:git:git://https://github.com/imthosea/libkswing.git"
			developerConnection = "scm:git:ssh://github.com:imthosea/libkswing.git"
		}
	}

	@Suppress("UnstableApiUsage")
	configureBasedOnAppliedPlugins(
		JavadocJar.Dokka("dokkaGeneratePublicationHtml"),
		SourcesJar.Sources()
	)
}