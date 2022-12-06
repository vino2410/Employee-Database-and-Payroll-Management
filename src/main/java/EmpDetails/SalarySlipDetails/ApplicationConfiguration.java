package EmpDetails.SalarySlipDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationConfiguration 
{
	AuthenticationManager Manager;
	@Autowired
	EmpDetailsService serv;
	
	@Bean
	public WebSecurityCustomizer custom()
	{
		return(web)->web.ignoring().antMatchers("/vinod/newone");
	}
	
	@Bean
	public PasswordEncoder Encode()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain pass(HttpSecurity sec) throws Exception
	{
		//sec.authorizeRequests().anyRequest().permitAll();
//		sec.authorizeRequests().anyRequest().authenticated();
		sec.authorizeRequests().antMatchers("/vinod/*").authenticated();
		sec.httpBasic();
		sec.cors();
		sec.formLogin();
		sec.csrf().disable();
		
		AuthenticationManagerBuilder built=sec.getSharedObject(AuthenticationManagerBuilder.class);
		built.userDetailsService(serv).passwordEncoder(Encode());
		Manager=built.build();
		sec.authenticationManager(Manager);
		
		
		return sec.build();
	}

}
