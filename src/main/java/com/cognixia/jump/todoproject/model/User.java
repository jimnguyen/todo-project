package com.cognixia.jump.todoproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Role {
		ROLE_USER, ROLE_ADMIN
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Enter a first name")
	@Column(columnDefinition = "varchar(255)")
	private String firstName;
	
	@NotBlank(message = "Enter a last name")
	@Column(columnDefinition = "varchar(255)")
	private String lastName;
	
	@NotBlank(message = "Enter a valid user name")
	@Column(columnDefinition = "varchar(50) unique")
	private String username;
	
	@NotBlank(message = "Enter a password")
	@Column(columnDefinition = "varchar(50)")
	private String password;

	@Column(columnDefinition = "boolean default true")
	private boolean enabled;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
	@JsonManagedReference
	private List<ToDo> toDos;

	public User() {}

	public User(Integer id, String firstName, String lastName, String username, String password, boolean enabled, Role role, List<ToDo> toDos) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.toDos = toDos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<ToDo> getToDos() {
		return toDos;
	}

	public void setToDos(List<ToDo> toDos) {
		this.toDos = toDos;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", role=" + role +
				", toDos=" + toDos +
				'}';
	}
}
