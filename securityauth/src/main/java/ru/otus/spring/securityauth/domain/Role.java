package ru.otus.spring.securityauth.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	@ManyToMany
	@JoinTable(name = "roles_privileges",
	joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	private List<Privilege> privileges;

}
