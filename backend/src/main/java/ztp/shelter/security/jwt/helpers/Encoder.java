package ztp.shelter.security.jwt.helpers;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder
{
    public static final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

}
