provider "docker" {
  # host    = "unix:///var/run/docker.sock"  # For Linux
   host    = "npipe:////./pipe/docker_engine" # For Windows
}

resource "docker_container" "web_server" {
  name  = "my-web-server"
  image = "nginx:latest"
  ports {
    internal = 80
    external = 8080
  }
}