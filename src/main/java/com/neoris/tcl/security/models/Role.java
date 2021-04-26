package com.neoris.tcl.security.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hfm_roles")
public class Role {

	public Role() {
	}

	public Role(int id, String role, String description) {
		this.id = id;
		this.role = role;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private int id;

	@Column(name = "role")
	private String role;

	@Column(name = "description", columnDefinition = "VARCHAR(50)")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
