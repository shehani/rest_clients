package org.example.proxy;

import feign.Headers;
import org.example.config.ProjectConfiguration;
import org.example.model.Idea;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "idea" , url = "http://localhost:8080/api/idea" , configuration = ProjectConfiguration.class)
public interface IdeaProxy {

    @RequestMapping(method = RequestMethod.GET , value = "/getIdea")
    //@Headers(value = "Content-Type: application/json") - commented line since api is not required a request body
    public List<Idea> getIdeaByStatus(@RequestParam(value = "status") String status);
}
