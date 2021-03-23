package it.engineering.spring.mvc.ds.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.engineering.spring.mvc.ds.dto.ContactPersonDto;
import it.engineering.spring.mvc.ds.dto.ManufacturerDto;
import it.engineering.spring.mvc.ds.service.ContactPersonService;
import it.engineering.spring.mvc.ds.service.ManufacturerService;

@Controller
@RequestMapping(path = { "/contactperson", "/cp" })
public class ContactPersonController {

	private final ManufacturerService manufacturerService;
	@Qualifier("ContactPersonService")
	private final ContactPersonService contactPersonService;

	@Autowired
	public ContactPersonController(ManufacturerService manufacturerService, ContactPersonService contactPersonService) {
		this.manufacturerService = manufacturerService;
		this.contactPersonService = contactPersonService;
	}

	@GetMapping(path = "/add")
	public ModelAndView add() throws Exception {
		ModelAndView modelAndView = new ModelAndView("contactperson/contactperson-add");
		return modelAndView;
	}

	@ModelAttribute(name = "manufactures")
	private List<ManufacturerDto> getManufacturers() throws Exception {

		return manufacturerService.getAll();

	}

	@GetMapping(path = "/list")
	public ModelAndView list() throws Exception {
		List<ContactPersonDto> persons = contactPersonService.getAll();
		ModelAndView modelAndView = new ModelAndView("contactperson/contactperson-list");
		modelAndView.addObject("contactPersons", persons);
		return modelAndView;
	}

	@PostMapping(path = "/confirm")
	public ModelAndView confirm(@Valid @ModelAttribute(name = "contactPersonDto") ContactPersonDto contactPersonDto,
			Errors errors) throws Exception {

		System.out.println("contactPersonDto:" + contactPersonDto);

		String view = "contactperson/contactperson-add";
		ModelAndView modelAndView = new ModelAndView();

		if (contactPersonDto.getId() != null) {
			view = "contactperson/contactperson-edit";
		} else
			view = "contactperson/contactperson-confirm";

		modelAndView.setViewName(view);
		return modelAndView;
	}

	@PostMapping(path = "/saveOrUpdate")
	public ModelAndView saveOrUpdate(
			@Valid @ModelAttribute(name = "contactPersonDto") ContactPersonDto contactPersonDto, Errors errors)
			throws Exception {

		ModelAndView modelAndView = new ModelAndView();

		String view = "contactperson/contactperson-add";

		if (contactPersonDto.getId() != null) {
			view = "contactperson/contactperson-edit";
		} else {
			// save or update

			if (contactPersonDto.getId() == null) {
				System.out.println("contactperson " + contactPersonDto);
				contactPersonService.save(contactPersonDto);
				view = "redirect:/contactperson/add";

			} else {
				// update
				contactPersonDto = contactPersonService.update(contactPersonDto);
				view = "redirect:/contactperson/details/id/" + contactPersonDto.getId();
			}
		}

		modelAndView.setViewName(view);
		return modelAndView;
	}

	@GetMapping(path = "/details/id/{id}")
	public ModelAndView details(@PathVariable(name = "id") Long id) throws Exception {

		ContactPersonDto contactPersonDto = contactPersonService.findById(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contactperson/contactperson-details");
		modelAndView.addObject("contactPersonDto", contactPersonDto);

		return modelAndView;
	}

	@GetMapping(path = "/edit/id/{id}")
	public ModelAndView edit(@PathVariable(name = "id") Long id) throws Exception {

		ContactPersonDto contactPersonDto = contactPersonService.findById(id);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("contactperson/contactperson-edit");
		modelAndView.addObject("contactPersonDto", contactPersonDto);
		return modelAndView;
	}

	@GetMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		try {
			contactPersonService.delete(id);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "redirect:/contactperson/list";
	}

	@ModelAttribute(name = "contactPersonDto")
	private ContactPersonDto contactPersonDto() throws Exception {

		ContactPersonDto contactPersonDto = new ContactPersonDto();
		contactPersonDto.setFirstName("first name");
		contactPersonDto.setLastName("last name");
		return contactPersonDto;

	}

}
