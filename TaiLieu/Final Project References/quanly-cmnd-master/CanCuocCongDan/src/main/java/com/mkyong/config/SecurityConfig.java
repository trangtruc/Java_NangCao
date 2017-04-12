package com.mkyong.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("SELECT USER_NAME, PASSWORD, TRANG_THAI FROM tai_khoan WHERE USER_NAME = ?")
			.authoritiesByUsernameQuery("SELECT A.USER_NAME, B.TEN_QUYEN FROM tai_khoan A, quyen B, quyen_tai_khoan C WHERE C.MA_QUYEN = B.MA_QUYEN AND A.USER_NAME = C.USER_NAME AND A.USER_NAME = ?");
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").access("hasRole('QUAN_TRI')")
			.and()
				.formLogin().loginPage("/login").failureUrl("/login?error")
					.usernameParameter("USER_NAME").passwordParameter("PASSWORD").defaultSuccessUrl("/thong-tin-tai-khoan")
			.and()
				.logout().logoutSuccessUrl("/login?logout")
			.and()
				.exceptionHandling().accessDeniedPage("/403")
			.and()
				.csrf();
		
	}
}