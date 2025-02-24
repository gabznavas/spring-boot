package io.github.gabznavas.rest_with_spring_boot_and_java_erudio.controller;

import io.github.gabznavas.rest_with_spring_boot_and_java_erudio.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreatingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // http:localhost:8080/greeting?name=gabznavas
    @GetMapping("/greeting")
    public Greeting greating(
            @RequestParam(value = "name", defaultValue = "anonymous")
            String name
    ) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
