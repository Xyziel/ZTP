package ztp.shelter.security.jwt;

import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ztp.shelter.model.entity.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
public class ShelterUserDetails implements UserDetails
{
    private User user;

    public ShelterUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
        grantedAuthority.add(new SimpleGrantedAuthority(user.getRole().getName()));
        return grantedAuthority;
    }

    //TODO carefully probably
    //User is null?
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
