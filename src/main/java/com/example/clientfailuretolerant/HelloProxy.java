package com.example.clientfailuretolerant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("DEMO-TARGET-SERVICE")
public interface HelloProxy {

    @GetMapping("/hello")
    String helloHello();
}
