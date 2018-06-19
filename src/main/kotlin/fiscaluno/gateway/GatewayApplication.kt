package fiscaluno.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean


@SpringBootApplication
class GatewayApplication {
    @Bean
    fun configureRoutes(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
                .route { it.path("/user/auth").uri("https://fiscaluno-mu.herokuapp.com/users") }

                .route { it.path("/users").uri("https://crudgo.herokuapp.com/users") }

                .route { it.path("/institutions").uri("https://fiscaluno-hyoga.herokuapp.com/") }

                .route("intitution_path", {it.path("/institution/**")
                        .filters { it.rewritePath("/institution/(?<ID>.*)","/\${ID}") }
                        .uri("https://fiscaluno-hyoga.herokuapp.com")})


                .build()

    }
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}
