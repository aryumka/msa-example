package aryumka.app2

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/")
class Controller(
    private val restTemplate: RestTemplate
) {
    @GetMapping
    fun hello(): Greeting {
        val result = this.restTemplate
            .getForEntity("lb://app1/", Greeting::class.java)

        return result.body!!
    }
}

data class Greeting(val content: String)