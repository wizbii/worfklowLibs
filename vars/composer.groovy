def call() {
  docker.image('composer/composer')
        .inside("-v ${pwd()}:/app -v /var/cache/composer:/var/cache/composer -e COMPOSER_CACHE_DIR=/var/cache/composer"){
    stage('Vendor'){
      sh 'composer install --no-dev --optimize-autoloader --no-scripts --prefer-dist --no-progress --no-interaction --ignore-platform-reqs'
    }
  } 
}
