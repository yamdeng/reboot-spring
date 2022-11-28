package com.yamdeng.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JavaTest {

    @Test
    void test2() {
        double result = (double)73 / (double)60;
        log.info("result : {}", Math.ceil(result * 10) / 10.0);
    }

}
