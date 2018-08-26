//node {
// stage('Checkout') {
//  deleteDir()
//  checkout scm
// }
// }
node {
	stage ('Checkout and Setup'){
  deleteDir()
  checkout scm
		sh 'cd fastlane'
	}
	stage ('Test'){
		sh 'fastlane test'
	}
	stage ('Build'){
// 		def build_number = env.BUILD_NUMBER
// 		sh "fastlane build build_number:${build_number}"
        sh 'fastlane buildDebug'
	}
	stage ('Upload Hockeyapp'){
// 		archive 'reports/, dist/'
		sh 'fastlane uploadToHockeyApp'
	}
}
