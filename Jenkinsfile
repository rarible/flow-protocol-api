@Library('shared-library') _

pipeline {
	agent any

	options {
		disableConcurrentBuilds()
	}

	stages {
		stage('deploy') {
			when {
				branch 'hackathon'
			}
			steps {
				sh 'mvn clean'
        deployToMaven('nexus-ci')
			}
		}
	}
}