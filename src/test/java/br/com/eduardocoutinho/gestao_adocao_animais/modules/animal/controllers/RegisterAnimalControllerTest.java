package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

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
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.CreateAnimalUseCase;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterAnimalControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CreateAnimalUseCase createAnimalUseCase;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Should be able to register a new animal")
    public void should_be_able_to_register_new_animal() throws Exception {

        var animal = AnimalEntity.builder()
                .name("Nick")
                .description("A brown dachshund.")
                .imageUrl("image.jpg")
                .category("dog")
                .bornAt(LocalDateTime.parse("2024-07-16T17:45:30"))
                .age(14)
                .status(true)
                .build();

        when(createAnimalUseCase.execute(any(AnimalEntity.class))).thenReturn(animal);

        mvc.perform(MockMvcRequestBuilders.post("/animal/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animal))) // Use objectMapper from setup
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nick"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A brown dachshund."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("image.jpg"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("dog"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bornAt").value("2024-07-16T17:45:30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(14))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true));

        verify(createAnimalUseCase).execute(any(AnimalEntity.class));
    }
}