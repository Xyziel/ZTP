package ztp.shelter.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ztp.shelter.model.entity.User;
import ztp.shelter.service.ShelterUserService;

@Service
public class ShelterUserDetailsService implements UserDetailsService
{
    @Autowired
    private ShelterUserService shelterUserService;

    //Username is email actually
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = shelterUserService.getUserByEmail(email);
        if(user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        return new ShelterUserDetails(user);
    }
}
