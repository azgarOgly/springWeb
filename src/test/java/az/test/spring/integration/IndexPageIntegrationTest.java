package az.test.spring.integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexPageIntegrationTest {

    @LocalServerPort
    private int port;
    private URL baseUrl;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.baseUrl = new URL(String.format("http://localhost:%d/", port));
    }

    @Test
    public void getIndex() {
        ResponseEntity<String> response = template.getForEntity(baseUrl.toString(), String.class);
        Assertions.assertThat(response.getBody()).isEqualTo("Spring web application. Hello!");
    }
}
