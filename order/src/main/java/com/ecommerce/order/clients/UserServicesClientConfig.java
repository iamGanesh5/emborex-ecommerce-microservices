package com.ecommerce.order.clients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Optional;

@Configuration

public class UserServicesClientConfig {

    @Bean
    public UserServiceClient userServiceClientConfig(RestClient.Builder restclintBuilder){
        RestClient restClient=restclintBuilder.baseUrl("http://user-service").defaultStatusHandler(HttpStatusCode::is4xxClientError,(((request, response) -> Optional.empty()))).build();
        RestClientAdapter adapter= RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory= HttpServiceProxyFactory.builderFor(adapter).build();
        UserServiceClient client=factory.createClient(UserServiceClient.class);
        return client;
    }
}
