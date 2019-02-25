package bd.ohedulalam.jwtsecurityspring.configuration;


import bd.ohedulalam.jwtsecurityspring.security.JwtAuthenticationEntrypoint;
import bd.ohedulalam.jwtsecurityspring.security.JwtAuthenticationProvider;
import bd.ohedulalam.jwtsecurityspring.security.JwtSuccessHandler;
import bd.ohedulalam.jwtsecurityspring.security.jwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationEntrypoint entrypoint;

    @Bean
    public AuthenticationManager authenticationManager(){

        return new ProviderManager(Collections.singletonList(authenticationProvider));

    }

    @Bean
    public jwtAuthTokenFilter authTokenFilter(){
        jwtAuthTokenFilter filter = new jwtAuthTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return filter;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("**/api/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entrypoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        HeadersConfigurer<HttpSecurity>.CacheControlConfig cacheControlConfig = http.headers().cacheControl();
    }
}
