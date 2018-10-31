package com.apap.tutorial7.controller;

import com.apap.tutorial7.rest.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

@RestController
@RequestMapping("/model")
public class ModelController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping()
    private Object modelCar(@RequestParam("factory") String factory) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String path = Setting.carQueryAPI + "/?&cmd=getModels&year=2018&make=" + factory;
        Object response = restTemplate.exchange(path, HttpMethod.GET,entity,Object.class);
        return response;
    }
}