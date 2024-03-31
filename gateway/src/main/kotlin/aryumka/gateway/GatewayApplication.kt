package aryumka.gateway

import io.netty.resolver.DefaultAddressResolverGroup
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient


@SpringBootApplication
class GatewayApplication {
	@Bean
	@Primary
	fun webClient(): HttpClient {
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE)
	}
}

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
