package aryumka.app1

import net.devh.boot.grpc.client.inject.GrpcClient
import net.devh.boot.grpc.examples.lib.HelloRequest
import net.devh.boot.grpc.examples.lib.MyServiceGrpc.MyServiceBlockingStub
import org.springframework.stereotype.Service


@Service
class HelloService {

    @GrpcClient("app2")
    private val myServiceStub: MyServiceBlockingStub? = null

    fun receiveGreeting(name: String?): String? {
        val request = HelloRequest.newBuilder()
            .setName(name)
            .build()
        return myServiceStub!!.sayHello(request).message
    }

}