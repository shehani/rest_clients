package org.example.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Idea;
import org.example.model.Response;
import org.example.proxy.IdeaProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

@Slf4j
@RestController
public class IdeaRestController {

    @Autowired
    IdeaProxy ideaProxy;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @GetMapping("/getIdea")
    public List<Idea> getIdea (@RequestParam("status") String status){
        return ideaProxy.getIdeaByStatus(status);
    }

    @PostMapping("/saveIdea")
    public ResponseEntity<Response> saveIdea(@RequestBody Idea idea){
        String url = "http://localhost:8080/api/idea/saveIdea";
        HttpHeaders header = new HttpHeaders();
        header.add("invocationFrom" , "RestTemplateClient");
        HttpEntity<Idea> httpEntity = new HttpEntity<>(idea,header);
        ResponseEntity<Response> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity,Response.class);
        return response;
    }

    @PostMapping("postIdea")
    public Mono<Response> saveIdeaWebClient(@RequestBody Idea idea){
        String url = "http://localhost:8080/api/idea/saveIdea";

        return webClient.post().uri(url).header
                ("invocationFrom" , "WebClient").body(Mono.just(idea),Idea.class)
                .retrieve().bodyToMono(Response.class);
    }
}
