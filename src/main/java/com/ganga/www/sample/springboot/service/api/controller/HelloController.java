package com.ganga.www.sample.springboot.service.api.controller;

import com.ganga.www.sample.springboot.service.api.constants.RequestUri;
import com.ganga.www.sample.springboot.service.api.model.SampleModel;
import com.ganga.www.sample.springboot.service.domain.SampleDomain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RequestUri.HELLO_REQ_URI)
public class HelloController {

    private SampleDomain sampleDomain;

    @Value("${sample.project:Default Name!}")
    private String testName;

    public HelloController(final SampleDomain sampleDomain) {
        this.sampleDomain = sampleDomain;
    }

    @RequestMapping(path = RequestUri.SUM_NUMS_PATH_PARAM_REQ_URI,
            method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> findSumUsingPathParam(@PathVariable("num1") int num1,
                                                        @PathVariable("num2") int num2) {
        return new ResponseEntity<String>(testName + " sum is : " + sampleDomain.sum(num1, num2),
                HttpStatus.OK);
    }

    @RequestMapping(path = RequestUri.SUM_NUMS_REQ_URI,
            method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String findSumUsingReqParam(@RequestParam(value = "num1", defaultValue = "0", required = true) int num1,
                                       @RequestParam(value = "num2", defaultValue = "0", required = true) int num2) {
        return "Final sum is : " + sampleDomain.sum(num1, num2);
    }

    @RequestMapping(path = RequestUri.SUM_NUMS_REQ_URI, method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> findSum(@RequestBody SampleModel model) {
        return new ResponseEntity<String>("Final sum is : "
                + sampleDomain.sum(model.getNum1(), model.getNum2()), HttpStatus.OK);
    }
}
