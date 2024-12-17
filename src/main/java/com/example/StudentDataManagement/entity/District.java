package com.example.StudentDataManagement.entity;

import jakarta.persistence.*;


@Entity

@Table(name = "district", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "state_id"}))
public class District {
    public District(Long id, String name, State state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}

	public District() {
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
