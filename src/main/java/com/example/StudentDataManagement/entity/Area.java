package com.example.StudentDataManagement.entity;

import jakarta.persistence.*;


@Entity

@Table(name = "area", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "block_id"}))
public class Area {
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

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;
}
