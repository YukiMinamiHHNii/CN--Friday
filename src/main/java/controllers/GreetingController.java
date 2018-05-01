package controllers;

import entities.Greeting;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

/**
 * CN--Friday on 30/04/2018.
 */
@RestController
public class GreetingController {

    private static final Logger log= LogManager.getLogger(GreetingController.class);
    private static final String template= "Hello, %s!";
    private final AtomicLong counter= new AtomicLong();

    @RequestMapping(method = RequestMethod.GET, value="/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "world")String name){
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
