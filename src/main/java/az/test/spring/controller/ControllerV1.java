package az.test.spring.controller;

import az.test.spring.persister.DataPersister;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ControllerV1 {

    @Qualifier("fileDataPersister")
    final DataPersister dataPersister;

    @RequestMapping("/data")
    public String index() {
        return "Spring web application. Data manipulation controller.";
    }

    @PostMapping(path = "/data/{streamName}",
            consumes = MediaType.TEXT_PLAIN_VALUE)
    public void storeData(@PathVariable String streamName, @RequestBody String body) {
        dataPersister.store(streamName, body);
    }
}
