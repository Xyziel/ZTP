package ztp.shelter.security.jwt.helpers;

public class JWTConstants
{
    public static final String SECRET = "JWT";
    public static final long EXPIRATION_TIME = 2000000; // 33 min
//    public static final long EXPIRATION_TIME = 20000; // 20 sec

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/registration";
}

