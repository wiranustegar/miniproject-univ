package id.co.indivara.jdt12.university.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("student").password("{noop}inistudent").roles("STUDENT")
                .and()
                .withUser("lecturer").password("{noop}inilecturer").roles("LECTURER")
                .and()
                .withUser("admin").password("{noop}iniadmin").roles("STUDENT", "LECTURER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                //ADMIN ONLY ROLE
                .antMatchers(HttpMethod.POST, "/report/register/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/report/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/classroom/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/classroom/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/classroom/{classroomId}/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/student/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/student/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/student/{studentId}/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/student/{studentId}/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/lecturer/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/lecturer/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/lecturer/{lecturerId}/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/lecturer/{lecturerId}/").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/subject/").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/subject/").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/subject/{subjectId}/").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/subject/{subjectId}/").hasRole("ADMIN")

                //LECTURER ROLE
                .antMatchers(HttpMethod.PUT, "/report/input-record/").hasRole("LECTURER")
                .antMatchers(HttpMethod.GET, "/report/{classroomId}/").hasRole("LECTURER")
                .antMatchers(HttpMethod.GET, "/report/student/{classroomId}/").hasRole("LECTURER")
                .antMatchers(HttpMethod.GET, "/lecturer/{lecturerId}/").hasRole("LECTURER")

                //STUDENT ROLE
                .antMatchers(HttpMethod.GET, "/student/{studentId}/").hasRole("STUDENT")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
