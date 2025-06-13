package com.udemy.gateway.beans;

import org.springframework.context.annotation.Configuration;

import java.util.Set;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Profile;

import com.udemy.gateway.filters.AuthFilter;

import lombok.AllArgsConstructor;



//Esta clase suele llamarse tambien GatewayConfig

@Configuration
// Para ocupar  "@AllArgsConstructor" requiero lombok
@AllArgsConstructor

public class GatewayBeans {

	private final AuthFilter authFilter;
	
// Este es el mismo que el de abajo pero este no tiene perfil	
//  @Bean
//  //@Profile(value = "eureka-off")
//  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//      return builder
//              .routes()
//              .route(route -> route
//                      .path("/companies-crud/company/*")
//                      .uri("http://localhost:8081")
//              )
//              .route(route -> route
//                      .path("/report-ms/report/*")
//                      .uri("http://localhost:7070")
//              )
//              .build();
//  }
	
	
	
    @Bean
    @Profile(value = "eureka-off")
    public RouteLocator routeLocatorEurekaOff(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/**")
                        .uri("http://localhost:8081")
                )
                .route(route -> route
                        .path("/report-ms/report/**")
                        .uri("http://localhost:7070")
                )
                .build();
    }

    @Bean
    @Profile(value = "eureka-on")
    public RouteLocator routeLocatorEurekaOn(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/**")
                        .uri("lb://companies-crud")
                        //lb: Balanceo de carga 
                        // seguido del microservicio
                )
                .route(route -> route
                        .path("/report-ms/report/**")
                        .uri("lb://report-ms")
                )
                .build();
    }
    
    
    
    //Eureka on con el circuit breaker encendido 
    @Bean
    @Profile(value = "eureka-on-cb")
    public RouteLocator routeLocatorEurekaOnCB(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(route -> route
                        .path("/companies-crud/company/**")
                        .filters(filter -> {
                            filter.circuitBreaker(config -> config
                                    .setName("gateway-cb")
                                    .setStatusCodes(Set.of( "300"))
                                    .setFallbackUri("forward:/companies-crud-fallback/company/*"));
                            return filter;
                        })
                        .uri("lb://companies-crud")
                )
                
                
                .route(route -> route
                        .path("/report-ms/report/**")
                        .uri("lb://report-ms")
                )
                //Esta es la nueva ruta que se agrega
                .route(route -> route
                        .path("/companies-crud-fallback/company/**")
                        .uri("lb://companies-crud-fallback")
                )
                
                
                // Despues quitar esta ruta porque se debe considerar solo abajo.  
//                .route(route -> route
//                        .path("/product/api/auth/login")
////                        .uri("lb://auth-server")
//                          .uri("lb://product")
//                       ) 
                .build();
    }
        
        @Bean
        @Profile(value = "oauth2")
        public RouteLocator routeLocatorOauth2(RouteLocatorBuilder builder) {
            return builder
                    .routes()
                    .route(route -> route
                            .path("/companies-crud/company/**")
                            .filters(filter -> {
                                filter.circuitBreaker(config -> config
                                        .setName("gateway-cb")
                                        .setStatusCodes(Set.of("500", "400"))
                                        .setFallbackUri("forward:/companies-crud-fallback/company/*"));
                                filter.filter(this.authFilter);
                                return filter;
                            })
                            .uri("lb://companies-crud")
                    )
                    .route(route -> route
                            .path("/report-ms/report/**")
                            .filters(filter -> filter.filter(this.authFilter))
                            .uri("lb://report-ms")
                    )
                    .route(route -> route
                            .path("/companies-crud-fallback/company/**")
                            .filters(filter -> filter.filter(this.authFilter))
                            .uri("lb://companies-crud-fallback")
                    )
                    .route(route -> route
                            .path("/auth-server/auth/**")
                            .uri("lb://auth-server")
                    )
                    .build();
        
        
    }
    
}
