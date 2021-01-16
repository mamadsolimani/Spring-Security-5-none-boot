package ir.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ir.com.domain.service.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			.antMatchers("/login", "/error").permitAll()
			.antMatchers("/create").permitAll()
			
			.antMatchers("/sec/admin").hasAuthority("ADMIN")
			.antMatchers("/sec/user").hasAnyAuthority("ADMIN", "USER")
			
			.antMatchers("/users/create").hasAuthority("CREATE_USER")
			.antMatchers("/users/edit").hasAuthority("EDIT_USER")
			.antMatchers("/users/remove").hasAuthority("REMOVE_USER")
			.antMatchers("/users/get").hasAuthority("GET_USER")
			.antMatchers("/users/findAll").hasAuthority("SEE_USERS")
			//.antMatchers("/users/lock").hasAuthority("LOCK_USER")
			
			.anyRequest().authenticated()
			
			
			
			.and()
			.rememberMe()
			.rememberMeCookieName("remember-cookie")
			.rememberMeParameter("remember-me")
			//.tokenValiditySeconds(365 * 24 * 60 * 60) // one Year
			//.tokenValiditySeconds(1 * 24 * 60 * 60) // one Day
			.tokenValiditySeconds(30 * 24 * 60 * 60) // one Month
			
			.and()
			.formLogin().loginPage("/login")
			.failureUrl("/login?error")
			
			.defaultSuccessUrl("/sec/user", false)  // boolean parameter is force to go to the page 
			// .successForwardUrl("/sec/user")
			
			.usernameParameter("username")
			.passwordParameter("password")
			
			.and()
			.logout().logoutSuccessUrl("/login?logout").logoutSuccessUrl("/login").deleteCookies("remember-cookie")
			
			.and()
			.exceptionHandling().accessDeniedPage("/error")
			
			;

		super.configure(http);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
