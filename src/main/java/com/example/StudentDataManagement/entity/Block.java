package com.example.StudentDataManagement.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "block", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "district_id"}))
public class Block {
    public Block(Long id, String name, District district) {
		super();
		this.id = id;
		this.name = name;
		this.district = district;
	}
    public Block() {};

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;
}
