package it.engineering.spring.mvc.ds.dto;

import java.io.Serializable;

public class ContactPersonDto implements Serializable, Dto {

	private static final long serialVersionUID = -7708289936347758013L;

	private Long id;
	private String firstName;
	private String lastName;
	private ManufacturerDto manufacturerDto;

	public ContactPersonDto() {
		// TODO Auto-generated constructor stub
	}

	public ContactPersonDto(Long id, String firstName, String lastName, ManufacturerDto manufacturerDto) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.manufacturerDto = manufacturerDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ManufacturerDto getManufacturerDto() {
		return manufacturerDto;
	}

	public void setManufacturerDto(ManufacturerDto manufacturerDto) {
		this.manufacturerDto = manufacturerDto;
	}

	
	@Override
	public String toString() {
		return "ContactPersonDto [id=" + id + ", firstName=" + firstName + ", LastName=" + lastName
				+ ", manufacturerDto=" + manufacturerDto + "]";
	}

	
}
