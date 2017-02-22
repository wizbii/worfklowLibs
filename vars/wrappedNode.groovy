def call(Closure body=null) {
  node {
    wrap([$class: 'TimestamperBuildWrapper']) {
      wrap([$class: 'AnsiColorBuildWrapper']) {
        step([$class: 'WsCleanup'])
        if (body) {
          try {
            body()
          } catch(e){
            currentBuild.result = "FAILED"
            slackSend color: 'danger', message: "💥 ${env.JOB_NAME} has failed. (${env.BUILD_URL})"
            throw e
          }
          if(!currentBuild.rawBuild.getPreviousBuild()?.getResult().toString().equals("SUCCESS")) {
            slackSend color: 'good', message: "🦄 ${env.JOB_NAME} is back to normal !"
          }
        }
      }
    }
  }
}
