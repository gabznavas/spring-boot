package io.github.gabznavas.docker_example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* criar a imagem: docker build -t docker-example:0.0.1-SNAPSHOT .
* Criar o container: docker run -p 80:8080 -d docker-example:0.0.1-SNAPSHOT
* */


@RestController
public class DockerController {

    @RequestMapping("/hello-docker")
    public HelloDocker greeting() {
        var hostname = System.getenv("HOSTNAME");
        return new HelloDocker(
                "Hello Docker",
                hostname
        );
    }
}
