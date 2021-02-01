package com.ganga.www.sample.springboot.service.api.controller;

import com.ganga.www.sample.springboot.service.api.constants.RequestUri;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RequestUri.SHOPPING_REQ_URI)
public class HelloController {

    @ResponseBody
    @RequestMapping(RequestUri.TEST_REQ_URI)
    public ResponseEntity<String> test() {
        return new ResponseEntity("Hello !", HttpStatus.OK);
    }
}
