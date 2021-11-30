package com.revature.shms.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cleaning {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@OneToOne
	Room room;
	@OneToOne
	Employee employee;

	@Column(nullable = false)
	Long dateAdded;
	@Column(nullable = false)
	int priority;


}
