package az.test.spring.persister;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoggerDataPersister implements DataPersister {
    @Override
    public void store(String streamName, String body) {
        log.info("Stream: {}, Strings: {}", streamName, body);
    }
}
