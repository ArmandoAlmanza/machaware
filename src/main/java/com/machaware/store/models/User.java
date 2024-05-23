package com.machaware.store.models;

import com.machaware.store.utils.UserValidation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@NotNull(message = "{NotNull.user.id}")
	@Id
	private Long id;

	@NotBlank(message = "{NotBlank.user.first_name}")
	@Size(min = 3, max = 25)
	@Column(name = "first_name")
	private String firstName;

	@NotBlank(message = "{NotBlank.user.last_name}")
	@Size(min = 5, max = 30)
	@Column(name = "last_name")
	private String lastName;

	@NotBlank(message = "{NotBlank.user.email}")
	@Column(name = "email")
	@UserValidation
	private String email;

	@NotBlank(message = "{NotBlank.user.password}")
	@Column(name = "password")
	private String password;

	@Column(name = "rol")
	@Enumerated(EnumType.STRING)
	private Rol rol;

	@PrePersist
	public void prePersist() {
		rol = Rol.USER;
	}
}
