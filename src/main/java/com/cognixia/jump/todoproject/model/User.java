package com.cognixia.jump.todoproject.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class User {
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
	private String userName;
	
	@NotBlank(message = "Enter a password")
	@Column(columnDefinition = "varchar(50)")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
	@JsonManagedReference
	private List<ToDo> toDos;
	
	
	public User() {}


	public User(Integer id, @NotBlank(message = "Enter a first name") String firstName,
			@NotBlank(message = "Enter a last name") String lastName,
			@NotBlank(message = "Enter a valid user name") String userName,
			@NotBlank(message = "Enter a password") String password, List<ToDo> toDos) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<ToDo> getToDos() {
		return toDos;
	}


	public void setToDos(List<ToDo> toDos) {
		this.toDos = toDos;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", toDos=" + toDos + "]";
	};
	
	
	
	

	
	
	

}
