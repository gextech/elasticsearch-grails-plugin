grails.project.work.dir = 'target'
grails.project.docs.output.dir = 'docs' // for the gh-pages branch

grails.project.repos.gex.url = "http://dev1.ec2.expansion.mx/nexus/content/repositories/snapshots/"

grails.project.repos."snapshots-repo".url = 'http://noams.artifactoryonline.com/noams/grails-elasticsearch-plugin-snapshots/'
grails.project.repos."rc-repo".url = 'http://noams.artifactoryonline.com/noams/grails-elasticsearch-plugin-rc/'

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        def excludes = {
            excludes 'slf4j-simple', 'persistence-api', 'commons-logging', 'jcl-over-slf4j', 'slf4j-api', 'jta'
            excludes 'spring-core', 'spring-beans', 'spring-aop', 'spring-asm', 'spring-webmvc', 'spring-tx', 'spring-context', 'spring-web', 'log4j', 'slf4j-log4j12'
            excludes group: 'org.grails', name: 'grails-core'
            excludes group: 'org.grails', name: 'grails-gorm'
            excludes group: 'org.grails', name: 'grails-test'
            excludes group: 'xml-apis', name: 'xml-apis'
            excludes 'ehcache-core'
            transitive = false
        }

        def datastoreVersion = '3.0.6.RELEASE'

        provided("org.grails:grails-datastore-gorm-plugin-support:$datastoreVersion",
                "org.grails:grails-datastore-gorm:$datastoreVersion",
                "org.grails:grails-datastore-core:$datastoreVersion",
                "org.grails:grails-datastore-web:$datastoreVersion", excludes)

        runtime 'org.elasticsearch:elasticsearch:1.1.0'
        runtime('org.elasticsearch:elasticsearch-lang-groovy:2.0.0')  {
            excludes 'junit'
            excludes 'elasticsearch'
            excludes 'groovy-all'
            excludes 'log4j'
        }
        runtime 'com.spatial4j:spatial4j:0.3'
    }

    plugins {
        build ':release:3.0.1', ':rest-client-builder:2.0.1', {
            export = false
        }

        test(':hibernate:3.6.10.13', ':tomcat:7.0.52.1') {
            export = false
        }
    }
}
