package com.example.clientfailuretolerant;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RestController
public class ClientFailureTolerantApplication {

    @Autowired
    HelloProxy client;

    Logger logger = LoggerFactory.getLogger("ClientFailureTolerantApplication.class");

    public static void main(String[] args) {
        SpringApplication.run(ClientFailureTolerantApplication.class, args);
    }

    @GetMapping("/hello")
    @Retry(name= "hello-api", fallbackMethod = "helloFallback")
    public String hello(){
        logger.info("Executing an /hello request");
        return "The hello service is returning: " + client.helloHello();
    }

    public String helloFallback(){
        logger.info("call fallback method");
        return "Hello from fallBackMethod, try again later! ;)";
    }

}
