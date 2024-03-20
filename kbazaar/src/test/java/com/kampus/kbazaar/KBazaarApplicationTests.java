package com.kampus.kbazaar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KBazaarApplicationTests {

    @Test
    void contextLoads() {
        int want = 1;
        int got = 1;

        assertEquals(want, got);
    }
}
