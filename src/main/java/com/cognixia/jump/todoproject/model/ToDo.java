package com.cognixia.jump.todoproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;

@Entity
public class ToDo {

    @Id
    @Column(columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    @NotBlank(message = "Give a description")
    private String description;

    @Column(columnDefinition = "DATE", nullable = false)
    @NotBlank(message = "Provide a due date")
    private Date dueDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    public ToDo() {
    }

	public ToDo(Integer id, @NotBlank(message = "Give a description") String description,
			@NotBlank(message = "Provide a due date") Date dueDate, User user) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", description=" + description + ", dueDate=" + dueDate + ", user=" + user + "]";
	}

    
}
