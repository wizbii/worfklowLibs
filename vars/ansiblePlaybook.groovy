def call(args){
  retry(2){
    ansiblePlaybook(args)
  }
}
