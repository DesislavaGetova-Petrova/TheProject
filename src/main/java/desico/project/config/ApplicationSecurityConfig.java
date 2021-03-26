package desico.project.config;

import desico.project.service.impl.ProjectUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ProjectUserService projectUserService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(ProjectUserService projectUserService, PasswordEncoder passwordEncoder) {
        this.projectUserService = projectUserService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.
                authorizeRequests().

                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                        antMatchers("/","/info","/offer","/users/login", "/users/register").permitAll().
                        antMatchers("/roles/delete","/unit/add", "/roles/add","/chapter/add","/video/add","/video/addLimited","/video/delete").hasRole("ADMIN").
                        antMatchers("/statistics").hasRole("VIPUSER").
                        antMatchers("/video/view", "/unit/view","/chapter/view","/video/viewAll").authenticated().
                and().

                        formLogin().
                        loginPage("/users/login").
                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                        passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                        defaultSuccessUrl("/video/viewAll").
                        failureForwardUrl("/users/login-error").
        and().
                logout().

                        logoutUrl("/logout").
                        logoutSuccessUrl("/").
                        invalidateHttpSession(true).
                        deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(projectUserService).
                passwordEncoder(passwordEncoder);
    }
}
