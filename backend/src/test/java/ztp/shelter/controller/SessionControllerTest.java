package ztp.shelter.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import ztp.shelter.security.jwt.ShelterUserDetailsService;
import ztp.shelter.service.AnimalService;
import ztp.shelter.service.SessionService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @MockBean
    private ShelterUserDetailsService shelterUserDetailsService;

    @Test
    public void getUserEmailWhenNoToken() throws Exception
    {
        when(sessionService.getUserEmail(null)).thenReturn("UNLOGGED");
        mockMvc.perform(get("/getUserEmail")).
                andExpect(content().string("UNLOGGED")).
                andDo(print());
    }




}
