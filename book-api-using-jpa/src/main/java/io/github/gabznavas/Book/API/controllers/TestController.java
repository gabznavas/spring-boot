package io.github.gabznavas.Book.API.controllers;

import io.github.gabznavas.Book.API.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {


    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @GetMapping("/test")
    public String testLog() {
        logger.info("This is an INFO log");
        logger.warn("This is an WARNING log");
        logger.debug("This is an DEBUG log");
        logger.error("This is an ERROR log");
        return "Logs generated successfully!";
    }
}
