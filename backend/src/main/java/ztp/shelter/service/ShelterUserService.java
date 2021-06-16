package ztp.shelter.service;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ztp.shelter.exceptions.CustomException;
import ztp.shelter.model.entity.User;
import ztp.shelter.model.repository.RoleRepo;
import ztp.shelter.model.repository.UserRepo;
import ztp.shelter.service.helpers.ExceptionThrower;

import java.util.List;

@Service
public class ShelterUserService
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User getUserByEmail(String email)
    {
        User user = userRepo.findUserByEmail(email);
        ExceptionThrower.throwIfDataDoesNotExist(user);
        return user;
    }

    public User getUserById(Integer idUser)
    {
        ExceptionThrower.throwIfDataIsNull(idUser);
        User user = userRepo.findUserByIdUser(idUser);
        ExceptionThrower.throwIfDataDoesNotExist(user);
        return user;
    }

    public Integer getUserIdByEmail(String email)
    {
        ExceptionThrower.throwIfDataIsNull(email);
        User user = userRepo.findUserByEmail(email);
        ExceptionThrower.throwIfDataDoesNotExist(user);
        return user.getIdUser();
    }

    public List<User> getAllUsersWithoutAdmin()
    {
        List<User> users = userRepo.findAllWithoutAdminRole();
        users.stream().forEach(user -> user.setPassword("NOT VISIBLE"));
        return users;
    }

    public void createUser(User newUser, String role)
    {
        ExceptionThrower.throwIfDataIsNull(newUser);
        User existingUser = userRepo.findUserByEmail(newUser.getEmail());
        ExceptionThrower.throwIfDataAlreadyExists(existingUser);

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setRole(roleRepo.findRoleByName(role));
        userRepo.save(newUser);
    }

    public void removeUserById(Integer idUser)
    {
        ExceptionThrower.throwIfDataIsNull(idUser);
        User user = getUserById(idUser);
        ExceptionThrower.throwIfDataDoesNotExist(user);
        userRepo.removeUserByIdUser(idUser);
    }

    public void changeUserPassword(JSONObject jsonObject, String email)
    {
        ExceptionThrower.throwIfDataIsNull(jsonObject);

        String oldPassword = jsonObject.getAsString("oldPassword");
        String newPassword = jsonObject.getAsString("newPassword");
        String repeatedPassword = jsonObject.getAsString("repeatedPassword");

        ExceptionThrower.throwIfDataIsNull(oldPassword, newPassword, repeatedPassword);
        User user = getUserByEmail(email);
        verifyPasswordInDb(oldPassword, user.getPassword());


        verifyPassword(oldPassword,newPassword,repeatedPassword);

        userRepo.updateUserPasswordByEmail(bCryptPasswordEncoder.encode(newPassword),email);
    }

    private void verifyPasswordInDb(String oldPassword, String dbPassword)
    {
        if(!bCryptPasswordEncoder.matches(oldPassword,dbPassword))
        {
            throw new CustomException("Your current password does not match",406);
        }
    }

    private void verifyPassword(String oldPassword, String newPassword, String repeatedPassword)
    {

        if(oldPassword.equals(newPassword))
        {
            throw new CustomException("Old password equals new password",406);
        }

        if(!newPassword.equals(repeatedPassword))
        {
            throw new CustomException("New password does not equal to repeated password",406);
        }
    }
}
