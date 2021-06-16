package ztp.shelter.service;


import org.h2.engine.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class SessionServiceTest
{
    @TestConfiguration
    static class SessionServiceContext
    {

        @Bean
        public SessionService sessionService()
        {
            return new SessionService();
        }
    }

    @Autowired
    private SessionService sessionService;


    @Test
    public void getUserEmailWhenTokenViolated()
    {
        assertEquals("UNLOGGED",sessionService.getUserEmail("Impostor "));
    }


}
