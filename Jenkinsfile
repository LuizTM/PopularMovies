stage 'Checkout'
 node('slave') {
  deleteDir()
  checkout scm
 }
//stage 'Checkout'
//	node ('slave'){
//		deleteDir()
//		checkout scm
//		sh 'cd fastlane'
//	}
//stage 'Test'
//	node ('slave'){
//		sh 'fastlane test'
//	}
//stage 'Build'
//	node ('slave'){
//// 		def build_number = env.BUILD_NUMBER
//// 		sh "fastlane build build_number:${build_number}"
//        	sh 'fastlane buildDebug'
//	}
//stage 'Upload'
//	node ('slave'){
//// 		archive 'reports/, dist/'
//		sh 'fastlane uploadToHockeyApp'
//	}
