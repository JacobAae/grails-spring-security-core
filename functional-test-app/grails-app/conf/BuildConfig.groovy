grails.servlet.version = '3.0'
grails.project.work.dir = 'target'
grails.project.target.level = 1.6
grails.project.source.level = 1.6

grails.project.dependency.resolver = 'maven'
grails.project.dependency.resolution = {
	inherits 'global'
	log 'error'
	checksums true
	legacyResolve false

	repositories {
		inherits true

		grailsPlugins()
		grailsHome()
		mavenLocal()
		grailsCentral()
		mavenCentral()
	}

	String gebVersion = '0.9.3'
	String seleniumVersion = '2.42.2'

	dependencies {
		test "org.seleniumhq.selenium:selenium-chrome-driver:$seleniumVersion"
		test "org.seleniumhq.selenium:selenium-firefox-driver:$seleniumVersion"
		test 'com.github.detro:phantomjsdriver:1.2.0', {
			transitive = false
		}
		test 'org.gebish:geb-spock:0.9.3'
	}

	plugins {
		build ':tomcat:7.0.54'

		compile ':scaffolding:2.0.3'
		compile ':cache:1.1.7'

		runtime ':database-migration:1.4.0'
		runtime ':hibernate:3.6.10.16'
		runtime ':jquery:1.11.1'
		runtime ':resources:1.2.8'
		runtime ':spring-security-core:2.0-SNAPSHOT'

		test ":geb:$gebVersion"
	}
}

def file = new File('testconfig')
String testconfig = file.exists() ? file.text.trim() : ''
switch (testconfig) {
	case 'annotation':
		grails.testing.patterns = ['Role', 'User', 'AnnotationSecurity', 'NamespaceSecurity', 'InheritanceSecurity']
		break
	case 'basic':
		grails.testing.patterns = ['Role', 'User', 'BasicAuthSecurity']
		break
	case 'bcrypt':
		grails.testing.patterns = ['BCrypt']
		break
	case 'misc':
		grails.testing.patterns = ['Misc', 'Disable']
		break
	case 'requestmap':
		grails.testing.patterns = ['Requestmap', 'Role', 'User', 'RequestmapSecurity']
		break
	case 'static':
		grails.testing.patterns = ['Role', 'User', 'StaticSecurity']
		break
}
grails.server.port.http = 8238
