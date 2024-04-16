package dev.silas.kontext

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(
    classes = [KontextApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
class KontextApplicationTests
    @Autowired
    constructor(
        private val webTestClient: WebTestClient,
    ) {
        @Test
        fun `header is not present should be ok`() {
            webTestClient
                .get()
                .uri("/hello")
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .isEqualTo("My context has nope")
        }

        @Test
        fun `header is present should be returned`() {
            webTestClient
                .get()
                .uri("/hello")
                .headers { headers ->
                    headers["x-sample-header"] = "testing"
                }
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .isEqualTo("My context has testing")
        }
    }
