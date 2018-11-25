import com.jfrog.bintray.gradle.BintrayExtension
import com.jfrog.bintray.gradle.BintrayPlugin
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import java.util.Properties

plugins {
    id("io.spring.dependency-management").version("1.0.6.RELEASE").apply(false)
    id("com.jfrog.bintray").version("1.8.4").apply(false)
}

subprojects {
    apply<JavaLibraryPlugin>()
    apply<DependencyManagementPlugin>()
    apply<JacocoPlugin>()
    apply<MavenPublishPlugin>()
    apply<BintrayPlugin>()

    repositories {
        jcenter()
    }

    configure<DependencyManagementExtension> {
        imports {
            mavenBom("org.junit:junit-bom:5.2.0")
            mavenBom("com.amazonaws:aws-java-sdk-bom:1.11.79")
            mavenBom("software.amazon.awssdk:bom:2.0.0")
        }
    }

    dependencies {
        val api by configurations
        val testImplementation by configurations
        val testRuntime by configurations

        api("org.junit.jupiter:junit-jupiter-api")

        testImplementation("org.junit.jupiter:junit-jupiter-api")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testRuntime("org.junit.jupiter:junit-jupiter-engine")
    }

    configure<JacocoPluginExtension> {
        toolVersion = "0.8.2"
    }

    configure<BintrayExtension> {
        user = System.getenv("BINTRAY_USER")
        key = System.getenv("BINTRAY_KEY")
    }

    tasks {
        withType<Test> {
            systemProperties = Properties().apply {
                load(File(rootDir, "gitlab/test.sys").bufferedReader())
            }.mapKeys { entry -> entry.key.toString() }
            useJUnitPlatform()
            testLogging {
                showStandardStreams = true
            }
        }

        withType<JacocoReport> {
            reports {
                xml.isEnabled = true
                html.isEnabled = true
            }
        }
    }
}

tasks {
    val wrapper by creating(Wrapper::class) {
        gradleVersion = "4.10.2"
        distributionType = Wrapper.DistributionType.ALL
    }
}
