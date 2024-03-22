package com.kampus.kbazaar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("integration-test")
class KBazaarApplicationIT {

    @Test
    void contextLoads() {
        int want = 1;
        int got = 1;

        assertEquals(want, got);
    }
}
