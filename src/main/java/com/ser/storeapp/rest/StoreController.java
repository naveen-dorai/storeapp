package com.ser.storeapp.rest;

import com.ser.storeapp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class StoreController {
    @Autowired
    private WebClient webClient;

    /*@GetMapping("/getProductById/{productId}")
    public Product getProductById(@PathVariable Long productId){
        System.out.println("Inside StoreController...");
        String apiUrl = "http://localhost:8081/getProduct/"+ productId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> responseEntity =  restTemplate.getForEntity(apiUrl, Product.class);
        Product product = responseEntity.getBody();
        return product;
    }*/
    @GetMapping("/getProductById/{productId}")
    public Product getProductById(@PathVariable Long productId){
        System.out.println("Inside StoreController...using WebClient...");
        String apiUrl = "http://localhost:8081/getProduct/"+ productId;
        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<Product> responseEntity =  restTemplate.getForEntity(apiUrl, Product.class);
        //Product product = responseEntity.getBody();

        Product product = WebClient.create().get().uri(apiUrl).retrieve().bodyToMono(Product.class).block();
        return product;
    }

    /*@PostMapping("/createProduct")
    public void createProduct(@RequestBody Product product){
        System.out.println("Inside StoreController...");
        String apiUrl = "http://localhost:8081/createProduct";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(apiUrl,product,Void.class);
    }*/

    @PostMapping("/createProduct")
    public Mono<Void> createProduct(@RequestBody Product product){
        System.out.println("Inside StoreController...using WebClient...");
        String apiUrl = "http://localhost:8081/createProduct";
        Mono<Void> bodyToMono = webClient.post()
                .uri(apiUrl)
                .body(BodyInserters.fromValue(product))
                .retrieve()
                .bodyToMono(Void.class);
        return bodyToMono;
    }
}
