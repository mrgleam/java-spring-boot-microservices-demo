case $1 in
  "build")

    echo "creating compiler container"

    docker build . -t mathsermone-store-compiler

    docker run -v $(pwd):/home/compiler/ mathsermone-store-compiler

    docker rmi mathsermone-store-compiler --force

    echo "compiled!"

  ;;
  "run")
    docker compose up -d --force-recreate --build
;;
esac