package com.ganga.www.sample.springboot.service.domain;

import org.springframework.stereotype.Component;

@Component
public class SampleDomain {

    public int sum(final int num1, final int num2) {
        return num1 + num2;
    }
}
