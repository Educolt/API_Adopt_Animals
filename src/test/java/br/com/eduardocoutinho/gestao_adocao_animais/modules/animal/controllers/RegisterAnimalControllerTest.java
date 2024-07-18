package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.CreateAnimalUseCase;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterAnimalControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @MockBean
    private CreateAnimalUseCase createAnimalUseCase;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Should be able to register a new animal")
    public void should_be_able_to_register_new_animal() throws Exception {

        var animal = AnimalEntity.builder()
                .name("Nick")
                .description("A brown dachshund.")
                .imageUrl("image.jpg")
                .category("dog")
                .bornAt("2024-07-16T17:45:30")
                .age(14)
                .status(true)
                .build();

        when(createAnimalUseCase.execute(any(AnimalEntity.class))).thenReturn(animal);

        var result = mvc.perform(MockMvcRequestBuilders.post("/animal/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(animal)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A brown dachshund."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("image.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("dog"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bornAt").value("2024-07-16T17:45:30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true));

        verify(createAnimalUseCase).execute(any(AnimalEntity.class));

        System.out.println(result);
    }

    private static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}