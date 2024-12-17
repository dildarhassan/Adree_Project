package com.example.StudentDataManagement.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "country", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

	public Country(String countryName) {
		// TODO Auto-generated constructor stub
	}

	public Country() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
