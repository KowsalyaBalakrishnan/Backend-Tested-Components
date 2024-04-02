package in.security.learn.config;

import in.security.learn.filter.CustomFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class SecurityConfig {

   /* @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("kowsalya")
                .password(passwordEncoder().encode("hello"))
                .authorities("read")
                .build();
        userDetailsManager.createUser(userDetails);
        return userDetailsManager;
    }*/

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Spring Security Configuration => Begin");
        httpSecurity.httpBasic();   // We need basic authentication httpBasic() or formLogin() here
        //httpSecurity.authorizeHttpRequests().anyRequest().authenticated();  // Authorize http requests and then all requests should be authenticated
        httpSecurity.authorizeHttpRequests()
                .antMatchers("/home").authenticated()
                .anyRequest().denyAll();    // Any requests other than /home will be denied even if the JSESSIONID is active
        httpSecurity.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
        System.out.println("Spring Security Configuration => End");
        return httpSecurity.build();
    }


}
