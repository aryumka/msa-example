package aryumka.app1

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
@RefreshScope
class Controller(
    private val helloService: HelloService
) {
    @Value("\${setting.value}")
    val value: String? = null

    @GetMapping
    fun hello(): Greeting {
        return Greeting("Hello, $value")
    }

    @GetMapping("/grpc/{name}")
    fun grpc(@PathVariable name: String) =
        this.helloService.receiveGreeting(name)
}

data class Greeting(val content: String)