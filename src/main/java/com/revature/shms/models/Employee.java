package com.revature.shms.models;

import com.revature.shms.enums.EmployeeType;
import lombok.*;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int employeeID;

	String firstName;
	String lastName;

	@Column(nullable = false, unique = true)
	String userName;
	@Column(nullable = false)
	String password;

	@Enumerated
	@Column(nullable = false)
	EmployeeType employeeType;
}
