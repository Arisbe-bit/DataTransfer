package com.neoris.tcl.security.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.ForeignKey;

import org.apache.myfaces.context.servlet.ServletExternalContextImpl;
import org.hibernate.annotations.ColumnDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "hfm_users")
public class User implements UserDetails {

	private final static Logger LOG = LoggerFactory.getLogger(User.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1538037177774436705L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid")
	private int id;

	@Column(name = "username")
	@Size(min = 5, message = "*Your user name must have at least 5 characters")
	@NotEmpty(message = "*Please provide a user name")
	private String username;

	@Column(name = "password")
	@Size(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(name = "enabled")
	@ColumnDefault(value = "1")
	private boolean enabled;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "hfm_user_role", 
				joinColumns = @JoinColumn(name = "userid", foreignKey = @ForeignKey(name = "FK_USER")), 
				inverseJoinColumns = @JoinColumn(name = "roleid", foreignKey = @ForeignKey(name = "FK_ROLE"))
	)
	private Set<Role> roles;

	public User() {
		this.roles = new HashSet<Role>();
		setEnabled(true);
	}

	public User(int id, String username, String password, String name, boolean enabled, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.roles = roles;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> gaRoles = new HashSet<GrantedAuthority>();
		for (Role role : this.getRoles()) {
			// LOG.info("Add Role = {}", role.getRole().name());
			gaRoles.add(new SimpleGrantedAuthority(role.getRole().name()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(gaRoles);
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		LOG.info("Receive Roles = {}", roles);
		this.roles = roles;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, name=%s, enabled=%s, roles=%s]", id, username,
				password, name, enabled, roles);
	}

}
