package pl.ssdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.ssdemo.security.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ComponentScan("pl.ssdemo.security")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(myUserDetailsService);
//        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .and().formLogin().permitAll()
                //login configuration
//                .loginPage("/customLogin.jsp")
//                .loginProcessingUrl("/appLogin")
//                .usernameParameter("app_username")
//                .passwordParameter("app_password")
//                .defaultSuccessUrl("/test")
//                .and().logout()    //logout configuration
//                .logoutUrl("/appLogout")
//                .logoutSuccessUrl("/test")
                .and().csrf().disable();
        http.formLogin().defaultSuccessUrl("/test");
//                .and().exceptionHandling() //exception handling configuration
//                .accessDeniedPage("/user/error");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Autowired
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }


}
