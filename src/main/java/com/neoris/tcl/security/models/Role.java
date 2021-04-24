package com.neoris.tcl.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hfm_roles")
public class Role {

	public Role() {
	}

	public Role(int id, Rol role, String description) {
		this.id = id;
		this.role = role;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private int id;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Rol role;

	@Column(name = "description", columnDefinition = "VARCHAR(50)")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rol getRole() {
		return role;
	}

	public void setRole(Rol role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Role [id=%s, role=%s, description=%s]", id, role, description);
	}

}
