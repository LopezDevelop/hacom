package hacom.test.developer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import hacom.test.developer.RouterHandlers;

@Configuration
public class ReactiveConfig {

    @Bean
    RouterFunction<?> routerFunction(RouterHandlers routerHandlers) {

        return RouterFunctions.route(RequestPredicates.GET("/rest/tracemsg/all"), routerHandlers::getAll)
        		.andRoute(RequestPredicates.POST("/rest/tracemsg"), routerHandlers::saveTrace);
        
        
    }

}
