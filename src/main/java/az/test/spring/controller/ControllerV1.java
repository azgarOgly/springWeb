package az.test.spring.controller;

import az.test.spring.persister.DataPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControllerV1 {

    final DataPersister dataPersister;

    @RequestMapping("/")
    public String index() {
        return "Spring web application. Hello!";
    }

    @PostMapping(path = "/{streamName}",
            consumes = MediaType.TEXT_PLAIN_VALUE)
    public void storeData(@PathVariable String streamName, @RequestBody String body) {
        dataPersister.store(streamName, body);
    }
}
