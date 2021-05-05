package az.test.spring.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getIndexTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("Spring web application. Hello!")));
    }

    @Test
    public void storeDataTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/foobar")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .content("someString\n")
                .accept(MediaType.TEXT_PLAIN_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("")));
    }
}
