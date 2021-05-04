package az.test.spring.persister;

import java.util.List;

public interface DataPersister {
    public void store(String streamName, String body);
}
