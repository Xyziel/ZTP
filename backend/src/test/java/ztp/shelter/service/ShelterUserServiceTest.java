package ztp.shelter.service;

import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ztp.shelter.exceptions.CustomException;
import ztp.shelter.exceptions.DataAlreadyExistsException;
import ztp.shelter.model.entity.User;
import ztp.shelter.model.repository.RoleRepo;
import ztp.shelter.model.repository.UserRepo;
import ztp.shelter.security.jwt.helpers.Encoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ShelterUserServiceTest
{
    @TestConfiguration
    static class ShelterUserContext
    {
        @Bean
        public ShelterUserService shelterUserService()
        {
            return new ShelterUserService();
        }
    }

    @Autowired
    ShelterUserService shelterUserService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private RoleRepo roleRepo;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void changeUserPasswordWhenOldPasswordEqualsNewPassword() throws Exception
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oldPassword", "witam");
        jsonObject.put("newPassword", "witam");
        jsonObject.put("repeatedPassword", "witam2");
        String email = "user";
        User user = mock(User.class);
        when(user.getPassword()).thenReturn(Encoder.bcrypt.encode(jsonObject.getAsString("oldPassword")));
        when(userRepo.findUserByEmail(email)).thenReturn(user);
        when(bCryptPasswordEncoder.matches(jsonObject.getAsString("oldPassword"), user.getPassword()))
                .thenReturn(true);
        System.out.println(userRepo.findUserByEmail(email).getPassword());
        CustomException exception = assertThrows(CustomException.class,
                                                 () -> shelterUserService.changeUserPassword(jsonObject, email));
        assertEquals("Old password equals new password", exception.getMessage());
        assertEquals(Integer.valueOf(406), exception.getHttpStatus());
    }

    @Test
    public void createUserWhenHeAlreadyExists() throws Exception
    {
        User user = mock(User.class);
        String email = "user@gmail.com";
        String role = "USER";
        when(user.getEmail()).thenReturn(email);
        when(userRepo.findUserByEmail(email)).thenReturn(user);

        DataAlreadyExistsException exception = assertThrows(DataAlreadyExistsException.class,
                                                            () -> shelterUserService.createUser(user, role));

        assertEquals("Such data already exists", exception.getMessage());
        assertEquals(Integer.valueOf(409), exception.getHttpStatus());
    }




}
