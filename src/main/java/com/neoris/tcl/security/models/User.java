package com.neoris.tcl.security.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "HFM_USERS")
public class User implements UserDetails {

	private final static Logger LOG = LoggerFactory.getLogger(User.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1538037177774436705L;

	@Id
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

	@ColumnDefault(value = "0")
	private boolean admin;

	@ColumnDefault(value = "0")
	private boolean hfmcodes;

	@ColumnDefault(value = "0")
	private boolean hfmcodesoa;

	@ColumnDefault(value = "0")
	private boolean hfmcodestypes;

	@ColumnDefault(value = "0")
	private boolean partners;

	@ColumnDefault(value = "0")
	private boolean payablesaccounts;

	@ColumnDefault(value = "0")
	private boolean receivablesaccounts;

	@ColumnDefault(value = "0")
	private boolean matchaccounts;

	@ColumnDefault(value = "0")
	private boolean dsvscompany;

	@ColumnDefault(value = "0")
	private boolean rollup;

	@ColumnDefault(value = "0")
	private boolean rolluphist;

	@ColumnDefault(value = "0")
	private boolean policies;

	@ColumnDefault(value = "0")
	private boolean definedaccounts;
	
	@ColumnDefault(value = "0")
	private boolean manualentrieshist;

	@Transient
	private List<Rol> selectdRoles;

	@Transient
	private String passwordBackUp;


	public User() {
		passwordBackUp = "";
		selectdRoles = new ArrayList<>();
		setEnabled(true);
	}

	public User(
			String username, String password, String name, boolean enabled, boolean admin,
			boolean hfmcodes, boolean hfmcodesoa, boolean hfmcodestypes, boolean partners, boolean payablesaccounts,
			boolean receivablesaccounts, boolean matchaccounts, boolean dsvscompany, boolean rollup, boolean rolluphist,
			boolean policies, boolean definedaccounts, boolean manualentrieshist) {
		this.selectdRoles = new ArrayList<>();
		this.username = username;
		this.password = password;
		this.name = name;
		this.enabled = enabled;
		this.admin = admin;
		this.hfmcodes = hfmcodes;
		this.hfmcodesoa = hfmcodesoa;
		this.hfmcodestypes = hfmcodestypes;
		this.partners = partners;
		this.payablesaccounts = payablesaccounts;
		this.receivablesaccounts = receivablesaccounts;
		this.matchaccounts = matchaccounts;
		this.dsvscompany = dsvscompany;
		this.rollup = rollup;
		this.rolluphist = rolluphist;
		this.policies = policies;
		this.definedaccounts = definedaccounts; 
		this.manualentrieshist = manualentrieshist;
		this.passwordBackUp ="";
		this.populateList();
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
		if (admin) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.ADMIN.name()));
			selectdRoles.add(Rol.ADMIN);
		}
		if (hfmcodes) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.HFMCODES.name()));
			selectdRoles.add(Rol.HFMCODES);
		}
		if (hfmcodesoa) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.HFMCODESOA.name()));
			selectdRoles.add(Rol.HFMCODESOA);
		}
		if (hfmcodestypes) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.HFMCODESTYPES.name()));
			selectdRoles.add(Rol.HFMCODESTYPES);
		}
		if (partners) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.PARTNERS.name()));
			selectdRoles.add(Rol.PARTNERS);
		}
		if (payablesaccounts) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.PAYABLESACCOUNTS.name()));
			selectdRoles.add(Rol.PAYABLESACCOUNTS);
		}
		if (receivablesaccounts) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.RECEIVABLESACCOUNTS.name()));
			selectdRoles.add(Rol.RECEIVABLESACCOUNTS);
		}
		if (matchaccounts) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.MATCHACCOUNTS.name()));
			selectdRoles.add(Rol.MATCHACCOUNTS);
		}
		if (dsvscompany) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.DSVSCOMPANY.name()));
			selectdRoles.add(Rol.DSVSCOMPANY);
		}
		if (rollup) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.ROLLUP.name()));
			selectdRoles.add(Rol.ROLLUP);
		}
		if (rolluphist) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.ROLLUPHIST.name()));
			selectdRoles.add(Rol.ROLLUPHIST);
		}
		if (policies) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.POLICIES.name()));
			selectdRoles.add(Rol.POLICIES);
		}
		if(definedaccounts) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.POLICIES.name()));
			selectdRoles.add(Rol.DEFINEDACCOUNT);
		}
		
		if(manualentrieshist) {
			gaRoles.add(new SimpleGrantedAuthority(Rol.MANUALENTRIESHIST.name()));
			selectdRoles.add(Rol.MANUALENTRIESHIST);
		}
		return new ArrayList<GrantedAuthority>(gaRoles);
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isHfmcodes() {
		return hfmcodes;
	}

	public void setHfmcodes(boolean hfmcodes) {
		this.hfmcodes = hfmcodes;
	}

	public boolean isHfmcodesoa() {
		return hfmcodesoa;
	}

	public void setHfmcodesoa(boolean hfmcodesoa) {
		this.hfmcodesoa = hfmcodesoa;
	}

	public boolean isHfmcodestypes() {
		return hfmcodestypes;
	}

	public void setHfmcodestypes(boolean hfmcodestypes) {
		this.hfmcodestypes = hfmcodestypes;
	}

	public boolean isPartners() {
		return partners;
	}

	public void setPartners(boolean partners) {
		this.partners = partners;
	}

	public boolean isPayablesaccounts() {
		return payablesaccounts;
	}

	public void setPayablesaccounts(boolean payablesaccounts) {
		this.payablesaccounts = payablesaccounts;
	}

	public boolean isReceivablesaccounts() {
		return receivablesaccounts;
	}

	public void setReceivablesaccounts(boolean receivablesaccounts) {
		this.receivablesaccounts = receivablesaccounts;
	}

	public boolean isMatchaccounts() {
		return matchaccounts;
	}

	public void setMatchaccounts(boolean matchaccounts) {
		this.matchaccounts = matchaccounts;
	}

	public boolean isDsvscompany() {
		return dsvscompany;
	}

	public void setDsvscompany(boolean dsvscompany) {
		this.dsvscompany = dsvscompany;
	}

	public boolean isRollup() {
		return rollup;
	}

	public void setRollup(boolean rollup) {
		this.rollup = rollup;
	}

	public boolean isRolluphist() {
		return rolluphist;
	}

	public void setRolluphist(boolean rolluphist) {
		this.rolluphist = rolluphist;
	}

	public boolean isPolicies() {
		return policies;
	}

	public void setPolicies(boolean policies) {
		this.policies = policies;
	}

	public boolean isDefinedaccounts() {
		return definedaccounts;
	}

	public void setDefinedaccounts(boolean definedaccounts) {
		this.definedaccounts = definedaccounts;
	}

	public String getPasswordBackUp() {
		return passwordBackUp;
	}

	public void setPasswordBackUp(String passwordBackUp) {
		this.passwordBackUp = passwordBackUp;
	}

	public List<Rol> getSelectdRoles() {
		populateList();
		LOG.info("[USers] Envio selectdRoles = {}", selectdRoles);
		return selectdRoles;
	}

	public boolean isManualentrieshist() {
		return manualentrieshist;
	}

	
	public void setManualentrieshist(boolean manualentrieshist) {
		this.manualentrieshist = manualentrieshist;
	}

	public void setSelectdRoles(List<Rol> selectdRoles) {
		LOG.info("[USers] Recibo selectdRoles = {}", selectdRoles);
		this.selectdRoles = selectdRoles;
		resetRoles();
		for (Rol rol : selectdRoles) {
			if (Rol.ADMIN.equals(rol)) {
				this.setAdmin(true);
			}
			if (Rol.HFMCODES.equals(rol)) {
				this.setHfmcodes(true);
			}
			if (Rol.HFMCODESOA.equals(rol)) {
				this.setHfmcodesoa(true);
			}
			if (Rol.HFMCODESTYPES.equals(rol)) {
				this.setHfmcodestypes(true);
			}
			if (Rol.PARTNERS.equals(rol)) {
				this.setPartners(true);
			}
			if (Rol.PAYABLESACCOUNTS.equals(rol)) {
				this.setPayablesaccounts(true);
			}
			if (Rol.RECEIVABLESACCOUNTS.equals(rol)) {
				this.setReceivablesaccounts(true);
			}
			if (Rol.MATCHACCOUNTS.equals(rol)) {
				this.setMatchaccounts(true);
			}
			if (Rol.DSVSCOMPANY.equals(rol)) {
				this.setDsvscompany(true);
			}
			if (Rol.ROLLUP.equals(rol)) {
				this.setRollup(true);
			}
			if (Rol.ROLLUPHIST.equals(rol)) {
				this.setRolluphist(true);
			}
			if (Rol.POLICIES.equals(rol)) {
				this.setPolicies(true);
			}
			if (Rol.DEFINEDACCOUNT.equals(rol)) {
				this.setDefinedaccounts(true);
			}
			
			if (Rol.MANUALENTRIESHIST.equals(rol)) {
				this.setManualentrieshist(true);
			}
		}
	}

	private void resetRoles() {
		this.admin = false;
		this.hfmcodes = false;
		this.hfmcodesoa = false;
		this.hfmcodestypes = false;
		this.partners = false;
		this.payablesaccounts = false;
		this.receivablesaccounts = false;
		this.matchaccounts = false;
		this.dsvscompany = false;
		this.rollup = false;
		this.rolluphist = false;
		this.policies = false;
		this.definedaccounts = false;
		this.manualentrieshist = false;
	}
	
	private void populateList() {
		selectdRoles = new ArrayList<>();
		if (admin) {
			selectdRoles.add(Rol.ADMIN);
		}
		if (hfmcodes) {
			selectdRoles.add(Rol.HFMCODES);
		}
		if (hfmcodesoa) {
			selectdRoles.add(Rol.HFMCODESOA);
		}
		if (hfmcodestypes) {
			selectdRoles.add(Rol.HFMCODESTYPES);
		}
		if (partners) {
			selectdRoles.add(Rol.PARTNERS);
		}
		if (payablesaccounts) {
			selectdRoles.add(Rol.PAYABLESACCOUNTS);
		}
		if (receivablesaccounts) {
			selectdRoles.add(Rol.RECEIVABLESACCOUNTS);
		}
		if (matchaccounts) {
			selectdRoles.add(Rol.MATCHACCOUNTS);
		}
		if (dsvscompany) {
			selectdRoles.add(Rol.DSVSCOMPANY);
		}
		if (rollup) {
			selectdRoles.add(Rol.ROLLUP);
		}
		if (rolluphist) {
			selectdRoles.add(Rol.ROLLUPHIST);
		}
		if (policies) {
			selectdRoles.add(Rol.POLICIES);
		}
		if (definedaccounts) {
			selectdRoles.add(Rol.DEFINEDACCOUNT);
		}
		
		if (manualentrieshist) {
			selectdRoles.add(Rol.MANUALENTRIESHIST);
		}
	}




}
