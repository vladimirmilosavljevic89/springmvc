package it.engineering.spring.mvc.ds.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import it.engineering.spring.mvc.ds.dto.ManufacturerDto;

import it.engineering.spring.mvc.ds.service.ManufacturerService;

public class ManufacturerFormatter implements Formatter<ManufacturerDto> {
	private final ManufacturerService manufacturerService;

	@Autowired
	public ManufacturerFormatter(ManufacturerService manufacturerService) {

		this.manufacturerService = manufacturerService;
	}

	@Override
	public String print(ManufacturerDto manufacturerDto, Locale locale) {
		return manufacturerDto.getId() + " - " + manufacturerDto.getName();
	}

	@Override
	public ManufacturerDto parse(String manufacturer, Locale locale) throws ParseException {
		;
		Long number = Long.parseLong(manufacturer);
		try {
			ManufacturerDto manufacturerDto = manufacturerService.findById(number);
			return manufacturerDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ParseException("Greska u formatteru...", 0);
		}

	}

}
