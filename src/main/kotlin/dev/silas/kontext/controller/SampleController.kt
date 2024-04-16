package dev.silas.kontext.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    interface SampleContext {
        val foo: String
    }

    context(SampleContext)
    @GetMapping("/hello")
    fun greet(): ResponseEntity<String> = ResponseEntity.ok("My context has $foo")
}
