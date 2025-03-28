package io.github.gabznavas.Book.API.controllers;

import io.github.gabznavas.Book.API.services.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/logs")
public class LogsController {

    private Logger logger = LoggerFactory.getLogger(PersonService.class.getName());

    @GetMapping()
    public String testLog() {
        logger.debug("This is an DEBUG log");
        logger.info("This is an INFO log");
        logger.warn("This is an WARN log");
        logger.error("This is an ERROR log");
        return "Logs generated successfully!";
    }
}
