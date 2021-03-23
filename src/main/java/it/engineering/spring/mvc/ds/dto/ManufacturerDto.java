package it.engineering.spring.mvc.ds.dto;

import java.io.Serializable;

public class ManufacturerDto implements Serializable, Dto {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private CityDto cityDto;
	private ContactPersonDto contactPersonDto;

	public ManufacturerDto() {
	}

	public ManufacturerDto(Long id, String name, CityDto cityDto) {
		super();
		this.id = id;
		this.name = name;
		this.cityDto = cityDto;
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

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

	@Override
	public String toString() {
		return "ManufacturerDto [id=" + id + ", name=" + name + ", cityDto=" + cityDto + "]";
	}

	public ContactPersonDto getContactPersonDto() {
		return contactPersonDto;
	}

	public void setContactPersonDto(ContactPersonDto contactPersonDto) {
		this.contactPersonDto = contactPersonDto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
