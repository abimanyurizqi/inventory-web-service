package com.enigma.restservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping(produces = "text/html")
    public String testGet(@RequestParam("name") String[] names,
                          HttpServletRequest request, HttpServletResponse response){
        Enumeration<String> e = request.getHeaderNames();
        String result = "";
        while (e.hasMoreElements()){
            String header = e.nextElement();
            String value = request.getHeader(header);
            result += header + ": " + value + "<br>";
        }
        result += "<br>";

        for (String name : names
             ) {

            result += "[GET] <b>Hello!</b> " + name + "<br>";
        }
        return result;
    }

    @PostMapping
    public String testPost(@RequestParam String name, @RequestParam int age){

        return"[POST] <b>Hello!</b> " + name + " your age is " + age;
    }
}
