package ztp.shelter.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


import org.springframework.test.web.servlet.MvcResult;
import ztp.shelter.exceptions.DataDoesNotExistException;
import ztp.shelter.exceptions.GlobalExceptionHandler;
import ztp.shelter.exceptions.SentDataIsNullException;
import ztp.shelter.model.repository.AnimalRepo;
import ztp.shelter.security.jwt.ShelterUserDetailsService;
import ztp.shelter.service.AnimalService;

@RunWith(SpringRunner.class)
@WebMvcTest(AnimalController.class)
public class AnimalControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnimalService animalService;

    @MockBean
    private ShelterUserDetailsService shelterUserDetailsService;


    @Test
    public void getAnimalByIdTestIfDataDoesNotExist() throws Exception
    {
        when(animalService.getAnimalById(999))
                .thenThrow(new DataDoesNotExistException("Such data does not exist", 404));
        mockMvc.perform(get("/animal/{id}", 999).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(404)).
                andExpect(r -> assertTrue(r.getResolvedException() instanceof DataDoesNotExistException)).
                andDo(print()).
                andReturn();
    }

    @Test
    public void addAnimalWhenSentDataIsMissing() throws Exception
    {
        mockMvc.perform(post("/animal").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().is(422)).
                andExpect(jsonPath("$.message").value("Wrong format for sent data or empty")).
                andExpect(r -> assertTrue(r.getResolvedException() instanceof HttpMessageNotReadableException)).
                andDo(print());
    }



}
