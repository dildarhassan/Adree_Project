package com.example.StudentDataManagement.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = {"roll_no", "class"}))
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    private int rollNo;

    @Column(nullable = false)
    private int className;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "block_id", nullable = false)
    private Block block;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;
	
    public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Long id, String studentName, int rollNo, int className, String address, Country country,
			State state, District district, Block block, Area area) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.rollNo = rollNo;
		this.className = className;
		this.address = address;
		this.country = country;
		this.state = state;
		this.district = district;
		this.block = block;
		this.area = area;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public int getClassName() {
		return className;
	}

	public void setClassName(int className) {
		this.className = className;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Area getArea() {
		return area;
	}
	
	public void setAreaName(Area area) {
		this.area=area;
	}


	
}
