/**
 * 
 */
package com.neoris.tcl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.neoris.tcl.security.models.Rol;
import com.neoris.tcl.security.service.UserDetailsServiceImpl;

/**
 * @author marco.vargas
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//        http.
//            authorizeRequests()
//            .antMatchers("/").authenticated()
//            .antMatchers("/javax.faces.resource/**").permitAll()
//            .antMatchers("/admin/**").hasAuthority(Rol.ADMIN.name())
//            .antMatchers("/index.xhtml").authenticated()
//            .antMatchers("/tradingpartnertypes.xhtml").hasAnyAuthority(Rol.TRADINGPARTNERTYPES.name(), Rol.ADMIN.name())
//            .antMatchers("/companyentries.xhtml").hasAnyAuthority(Rol.COMPANYENTRIES.name(), Rol.ADMIN.name())
//            .antMatchers("/hfmcodes.xhtml").hasAnyAuthority(Rol.HFMCODES.name(), Rol.ADMIN.name())
//            .antMatchers("/hfmcodesOA.xhtml").hasAnyAuthority(Rol.HFMCODESOA.name(), Rol.ADMIN.name())
//            .antMatchers("/layout.xhtml").hasAnyAuthority(Rol.LAYOUT.name(), Rol.ADMIN.name())
//            .antMatchers("/layouthist.xhtml").hasAnyAuthority(Rol.LAYOUTHIST.name(), Rol.ADMIN.name())
//            .antMatchers("/matchAccounts.xhtml").hasAnyAuthority(Rol.MATCHACCOUNTS.name(), Rol.ADMIN.name())
//            .antMatchers("/partners.xhtml").hasAnyAuthority(Rol.PARTNERS.name(), Rol.ADMIN.name())
//            .antMatchers("/payablesAccounts.xhtml").hasAnyAuthority(Rol.PAYABLESACCOUNTS.name(), Rol.ADMIN.name())
//            .antMatchers("/receivablesAccounts.xhtml").hasAnyAuthority(Rol.RECEIVABLESACCOUNTS.name(), Rol.ADMIN.name())
//            .antMatchers("/reclassification.xhtml").hasAnyAuthority(Rol.RECLASSIFICATION.name(), Rol.ADMIN.name())
//            .antMatchers("/rollup.xhtml").hasAnyAuthority(Rol.ROLLUP.name(), Rol.ADMIN.name())
//            .antMatchers("/rolluphist.xhtml").hasAnyAuthority(Rol.ROLLUPHIST.name(), Rol.ADMIN.name()).anyRequest()
//            .authenticated().and().csrf().disable()
//            .formLogin()
//            	.loginPage("/login.xhtml").permitAll()
//            	.failureUrl("/login.xhtml?error=true")
//            	.defaultSuccessUrl("/index.xhtml", true)
//            	.usernameParameter("username")
//            	.passwordParameter("password")
//            	.and()
//            .logout()
//            	.logoutRequestMatcher(new AntPathRequestMatcher("/login.xhtml"))
//            	.logoutSuccessUrl("/login.xhtml").and().exceptionHandling()
//            	.accessDeniedPage("/access-denied.xhtml");

		// require all requests to be authenticated except for the resources
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasAuthority(Rol.ADMIN.name())
				.antMatchers("/index.xhtml").authenticated()
				.antMatchers("/javax.faces.resource/**").permitAll().anyRequest().authenticated();
		// login
		http
			.formLogin()
				.loginPage("/login.xhtml").permitAll()
				.failureUrl("/login.xhtml?error=true")
				.defaultSuccessUrl("/index.xhtml", true);
		// logout
		http
			.logout()
			.logoutSuccessUrl("/login.xhtml");
		// not needed as JSF 2.2 is implicitly protected against CSRF
		http.csrf().disable();
	}

}
