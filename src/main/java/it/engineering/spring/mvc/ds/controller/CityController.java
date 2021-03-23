package it.engineering.spring.mvc.ds.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.exception.ExistEntityException;
import it.engineering.spring.mvc.ds.service.CityService;

@Controller
@RequestMapping(path = "/city")
@SessionAttributes(value = "cityDto")
public class CityController {
	@Autowired
	private LocalValidatorFactoryBean validator;

	@Autowired
	@Qualifier(value = "cityServiceImpl")
	private CityService cityService;

	@RequestMapping(path = { "", "/", "/add" }, method = RequestMethod.GET)
	public String add(SessionStatus session) {
		session.setComplete();
		return "city-add";
	}

	/*
	 * @RequestMapping(path = "/save", method = RequestMethod.POST) public String
	 * save(HttpServletRequest request) {
	 * System.out.println("=========== save =========");
	 * 
	 * String number=request.getParameter("number"); String
	 * name=request.getParameter("name"); System.out.println(number+", "+name);
	 * 
	 * try { Long code = Long.parseLong(number); CityDto cityDto = new CityDto(code,
	 * name); System.out.println(cityDto);
	 * 
	 * cityService.add(cityDto);
	 * 
	 * request.setAttribute("information", "Grad je uspesno sacuvan!"); return
	 * "redirect:/city"; //uradi redirekciju }catch(Exception e) {
	 * request.setAttribute("exception", "Greska kod unosa!"); return "city-add";
	 * //forward } }
	 */

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, @RequestParam(name = "number") String number,
			@RequestParam(name = "name") String name) {

		System.out.println("=========== save =========");

		try {
			Long code = Long.parseLong(number);
			CityDto cityDto = new CityDto(code, name);
			System.out.println(cityDto);

			cityService.save(cityDto);

			request.setAttribute("information", "Grad je uspesno sacuvan!");
			return "redirect:/city"; // uradi redirekciju
		} catch (Exception e) {
			request.setAttribute("exception", "Greska kod unosa!");
			return "city-add"; // forward
		}
	}

	@RequestMapping(path = "/saveDto", method = RequestMethod.POST)
	public String saveCityDto(HttpServletRequest request, CityDto cityDto) throws Exception {

		System.out.println("=========== save: /saveDto =========");

		try {
			System.out.println(cityDto);
			cityService.save(cityDto);
			request.setAttribute("information", "Grad je uspesno sacuvan!");
			return "redirect:/city"; // uradi redirekciju
		} catch (Exception e) {
			request.setAttribute("exception", "Greska kod unosa!");
			return "city-add"; // forward
		}
	}

	@RequestMapping(path = "/saveToConfirm", method = RequestMethod.POST)
	public String saveToConfirm(CityDto cityDto, Model model) {
		System.out.println("=========================================");
		System.out.println("/saveToConfirm");

		model.addAttribute("cityDto", cityDto);
		model.addAttribute("information", "Kliknite na dugem CONFIRM za potvrdu.");
		// redirectAttributes.addAttribute("information","Kliknite na dugem CONFIRM za
		// potvrdu");
		return "city-confirm";
	}

	@RequestMapping(path = "/confirm", method = RequestMethod.POST)
	public String confirm(CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("=========================================");
		System.out.println("/confirm");
		System.out.println(cityDto);

		cityService.save(cityDto);
		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");

		return "redirect:/city";
	}

	@RequestMapping(path = "/add-model", method = RequestMethod.GET)
	public ModelAndView addModel() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("cityDto", new CityDto(0L, "N/A"));
		modelAndView.setViewName("city-add-model");
		return modelAndView;
	}

	@RequestMapping(path = "/save-model", method = RequestMethod.POST)
	public String saveModel(CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
		System.out.println("=========================================");
		System.out.println("/save-model");
		System.out.println(cityDto);

		cityService.save(cityDto);
		redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");

		return "redirect:/city/add-model";
	}

	/*
	 * ========================================================= BINDING
	 * ================================
	 */
	@RequestMapping(path = "/add-binding", method = RequestMethod.GET)
	public String addBinding(@ModelAttribute("cityDto") CityDto cityDto) {
		System.out.println("=============================================================");
		System.out.println("/add-binding");
		
		cityDto.setNumber(-2L);
		cityDto.setName("City - 2");

		// ModelAndView modelAndView=new ModelAndView();
		// modelAndView.addObject("cityDto", new CityDto(-1L, "-"));
		// modelAndView.setViewName("city-add-binding");

		return "city-add-binding";
	}

	@RequestMapping(path = "/save-binding", method = RequestMethod.POST)
	public String saveBinding(@ModelAttribute("cityDto") CityDto cityDto, RedirectAttributes redirectAttributes) {
		System.out.println("=========================================");
		System.out.println("/save-binding");
		System.out.println(cityDto);

		try {
			cityService.save(cityDto);
			redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");
			return "redirect:/city/add-binding";
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("exception", e.getMessage());
			return "redirect:/city/add-binding";
		}

	}

	/*
	 * @RequestMapping(path = "/save-binding-validation", method =
	 * RequestMethod.POST) public ModelAndView saveBindingAndValidation(
	 * 
	 * @javax.validation.Valid @ModelAttribute("cityDto") CityDto cityDto, final
	 * BindingResult bindingResult, Model model) throws Exception{
	 * 
	 * System.out.println("=========================================");
	 * System.out.println("/save-binding-validation"); System.out.println(cityDto);
	 * System.out.println("-----------------------------------------");
	 * System.out.println(model.asMap());
	 * System.out.println("-----------------------------------------");
	 * 
	 * ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	 * Validator validator = factory.getValidator();
	 * Set<ConstraintViolation<CityDto>> constraintViolation =
	 * validator.validate(cityDto); System.out.println(
	 * "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	 * constraintViolation.stream().forEach(el->System.out.println(el));
	 * System.out.println(
	 * "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	 * 
	 * SpringValidatorAdapter springValidator = new
	 * SpringValidatorAdapter(validator); springValidator.validate(cityDto,
	 * bindingResult);
	 * 
	 * if (bindingResult.hasErrors()) { System.out.
	 * println("========================= HAS ERRORS =======================");
	 * ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("city-add-binding"); return modelAndView; }else{
	 * System.out.
	 * println("========================= NO ERRORS ======================="); try {
	 * cityService.save(cityDto); ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("redirect:/city/add-binding"); return modelAndView;
	 * }catch(Exception e) { System.out.
	 * println("================================== GRESKA =================================="
	 * ); System.out.println(e.getMessage()); ModelAndView modelAndView = new
	 * ModelAndView(); modelAndView.setViewName("city-add-binding");
	 * modelAndView.addObject("exception", e.getMessage()); return modelAndView; } }
	 * }
	 */
	@RequestMapping(path = "/save-binding-validation", method = RequestMethod.POST)
	public ModelAndView saveBindingAndValidation(@javax.validation.Valid @ModelAttribute("cityDto") CityDto cityDto,
			final BindingResult bindingResult, Model model, SessionStatus session) throws Exception {

		System.out.println("=========================================");
		System.out.println("/save-binding-validation");
		System.out.println(cityDto);
		System.out.println("-----------------------------------------");
		System.out.println(model.asMap());
		System.out.println("-----------------------------------------");

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<CityDto>> constraintViolation = validator.validate(cityDto);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		constraintViolation.stream().forEach(el -> System.out.println(el));
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		SpringValidatorAdapter springValidator = new SpringValidatorAdapter(validator);
		springValidator.validate(cityDto, bindingResult);

		if (bindingResult.hasErrors()) {
			System.out.println("========================= HAS ERRORS =======================");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("city-add-binding");
			return modelAndView;
		} else {
			System.out.println("========================= NO ERRORS =======================");
			cityService.save(cityDto);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/city/add-binding");
			session.setComplete();
			return modelAndView;
		}
	}

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String viewAll(HttpServletRequest request) throws Exception {
		request.setAttribute("cities", cityService.getAll());
		return "city-list";
	}

	@RequestMapping(path = { "/details" }, method = RequestMethod.GET)
	public String details(HttpServletRequest request) throws Exception {
		System.out.println("City number = " + request.getParameter("number"));
		CityDto cityDto = cityService.findById(Long.parseLong(request.getParameter("number")));
		if (cityDto == null) {

		} else {
			request.setAttribute("city", cityDto);
		}
		return "city-view";
	}
	
	@ExceptionHandler(ExistEntityException.class)
	public ModelAndView exeptionHandlerEntityExist(ExistEntityException eee) {
		System.out.println("=================================================================");
		System.out.println(eee.getMessage());
		System.out.println("============= Objekat nad kojim se desila greska je:==============");
		System.out.println(eee.getEntity());
		System.out.println("=================================================================");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("city-add-binding");
		modelAndView.addObject("cityDto",(CityDto)eee.getEntity() );
		modelAndView.addObject("exception", eee.getMessage());
		//modelAndView.setViewName("redirect:/city/add-binding");
		return modelAndView;
	}
	
	@InitBinder
	public void initBinder(DataBinder binder) {
		if (binder.getTarget() instanceof CityDto) {
			binder.setValidator(validator);
		}
	}
	
	@ModelAttribute(name = "cityDto")
	private CityDto getCityDto() {
		System.out.println("==============================================================");
		System.out.println("City DTO: kreiran - private CityDto getCityDto()");
		System.out.println("==============================================================");
		return new CityDto(-1l, "City - 1");
	}
	
}
