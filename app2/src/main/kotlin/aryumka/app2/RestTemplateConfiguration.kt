package aryumka.app2

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class RestTemplateConfiguration {
    @LoadBalanced
    @Bean
    fun restTemplate() = RestTemplate()
}