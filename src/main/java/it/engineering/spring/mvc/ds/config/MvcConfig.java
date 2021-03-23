package it.engineering.spring.mvc.ds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import it.engineering.spring.mvc.ds.formatter.CityDtoFormatter;

import it.engineering.spring.mvc.ds.formatter.ManufacturerFormatter;
import it.engineering.spring.mvc.ds.service.CityService;
import it.engineering.spring.mvc.ds.service.ContactPersonService;
import it.engineering.spring.mvc.ds.service.ManufacturerService;

@Configuration
@ComponentScan(basePackages = { "it.engineering.spring.mvc.ds.controller" })
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	private CityService cityService;
	private ManufacturerService manufacturerService;
	private ContactPersonService contactPersonService;

	@Autowired
	public MvcConfig(CityService cityService, ManufacturerService manufacturerService, ContactPersonService contactPersonService) {
		this.cityService = cityService;
		this.manufacturerService = manufacturerService;
		this.contactPersonService = contactPersonService;
	}

	@Bean
	public ViewResolver tilesViewResolver() {
		System.out.println("=========================== tilesViewResolver =====================");
		TilesViewResolver tilesViewResolver = new TilesViewResolver();
		tilesViewResolver.setOrder(0);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		//gde se nalazi definicija pogleda koji se vracaju korisniku
		tilesConfigurer.setDefinitions(new String[] {
			"/WEB-INF/views/tiles/tiles.xml"
		});
		return tilesConfigurer;
	}

	@Bean
	public ViewResolver jspViewResolver() {
		System.out.println("=========================== jspViewResolver =====================");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(1);
		return resolver;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		System.out.println("================ public void addFormatters(FormatterRegistry registry) =================");
		registry.addFormatter(new CityDtoFormatter(cityService));
		registry.addFormatter(new ManufacturerFormatter(manufacturerService));
		
	}
}
