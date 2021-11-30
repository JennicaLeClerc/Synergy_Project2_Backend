package com.revature.shms.models;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int userID;

	String firstName;
	String lastName;

	@Column(nullable = false, unique = true)
	String userName;
	@Column(nullable = false)
	String password;

}
