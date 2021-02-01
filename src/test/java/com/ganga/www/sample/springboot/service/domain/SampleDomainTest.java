package com.ganga.www.sample.springboot.service.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SampleDomainTest {

    @Test
    public void testSum() {
        final int num1 = 3;
        final int num2 = 4;
        final int result = 7;

        final SampleDomain sampleDomain = new SampleDomain();
        final int sum = sampleDomain.sum(num1, num2);
        assertEquals(sum, result);
    }
}