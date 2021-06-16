package ztp.shelter.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import ztp.shelter.filter.CorsWebFilter;
import ztp.shelter.security.jwt.ShelterUserDetailsService;
import ztp.shelter.security.jwt.filters.JWTAuthenticationFilter;
import ztp.shelter.security.jwt.filters.JWTAuthorizationFilter;
import ztp.shelter.security.jwt.handlers.JWTOnSuccessfulLogoutHandler;

import static ztp.shelter.security.jwt.helpers.RoleConstants.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter
{
    @Autowired
    private ShelterUserDetailsService shelterUserDetailsService;


    @Bean
    ShelterAuthenticationEntryPoints shelterAuthenticationEntryPoints()
    {
        return new ShelterAuthenticationEntryPoints();
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsWebFilter corsWebFilter()
    {
        return new CorsWebFilter();
    }


    @Bean
    JWTOnSuccessfulLogoutHandler onSuccessfulLogoutHandler()
    {
        return new JWTOnSuccessfulLogoutHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(shelterUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/register","/getUserRole","/getUserEmail",
                                   "/interest/getAllLikesByAnimalId/{\\d+}",
                                   "/animal/getLastSix","/animal","/animal/{\\d+}");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable().addFilterBefore(corsWebFilter(), CsrfFilter.class).
                authorizeRequests().
//                antMatchers("/register").anonymous(). //to disallow logged user to register
                antMatchers(HttpMethod.OPTIONS,"/**").permitAll().
                antMatchers(HttpMethod.PUT,"/user/changePassword").hasAuthority(USER).
                antMatchers(HttpMethod.POST,"/interest").hasAuthority(USER).
                antMatchers(HttpMethod.DELETE,"/interest/{id}").hasAuthority(USER).
                antMatchers(HttpMethod.GET,"/interest/getAnimalsIdThatUserLikes").hasAuthority(USER).
                antMatchers(HttpMethod.POST,"/animal").hasAuthority(EMPLOYEE).
                antMatchers(HttpMethod.DELETE,"/animal/{id}").hasAuthority(EMPLOYEE).
                antMatchers(HttpMethod.GET,"/breed").hasAuthority(EMPLOYEE).
                antMatchers(HttpMethod.GET,"/size").hasAuthority(EMPLOYEE).
                antMatchers("/user/{id}").hasAuthority(ADMIN).
                antMatchers("/user/**").hasAuthority(ADMIN).

                anyRequest().authenticated().
                and().
                httpBasic().authenticationEntryPoint(shelterAuthenticationEntryPoints()).
                and().
                logout().logoutSuccessHandler(onSuccessfulLogoutHandler())
                .and().
                addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
}
