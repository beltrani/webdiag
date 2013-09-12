package br.apolo.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.apolo.business.service.ClinicService;
import br.apolo.common.exception.AccessDeniedException;
import br.apolo.common.util.MessageBundle;
import br.apolo.data.model.Clinic;
import br.apolo.security.SecuredEnum;
import br.apolo.security.UserPermission;
import br.apolo.web.enums.Navigation;

@Controller
@RequestMapping(value = "/clinic")
public class ClinicController extends BaseController<Clinic> {

	@Autowired
	private ClinicService clinicService;

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.clinic.list"), 1, request);
		
		ModelAndView mav = new ModelAndView(Navigation.CLINIC_LIST.getPath());
		
		List<Clinic> clinicList = clinicService.list();
		
		mav.addObject("clinicList", clinicList);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("entity") Clinic entity, BindingResult result,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.setViewName(getRedirectionPath(request, Navigation.CLINIC_NEW, Navigation.CLINIC_EDIT));
			
			mav.addObject("clinic", entity);
			mav.addObject("readOnly", false);
			mav.addObject("error", true);
			
			StringBuilder message = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				DefaultMessageSourceResolvable argument = (DefaultMessageSourceResolvable) error.getArguments()[0];
				
				message.append(MessageBundle.getMessageBundle("common.field") + " " + MessageBundle.getMessageBundle("clinic." + argument.getDefaultMessage()) + ": " + error.getDefaultMessage() + "\n <br />");
			}
						
			mav.addObject("message", message.toString());
			
			return mav;
		}
		
		if (entity != null) {
			try {
				clinicService.save(entity);
				
     			mav = view(entity.getId(), request); 
				mav.addObject("msg", true);
				mav.addObject("message", MessageBundle.getMessageBundle("common.msg.save.success"));
			} catch (AccessDeniedException e) {
				mav = list(request);
				mav.addObject("error", true);
				mav.addObject("message", e.getCustomMsg());
			}
		}
		
		return mav;
	}
	
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable Long id, HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.clinic"), 2, request);
		
		ModelAndView mav = new ModelAndView(Navigation.DOCTOR_VIEW.getPath());
		
		Clinic clinic = clinicService.find(id);
		
		mav.addObject("clinic", clinic);
		mav.addObject("readOnly", true);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.clinic.new"), 1, request);
		
		ModelAndView mav = new ModelAndView(Navigation.CLINIC_NEW.getPath());
		
		Clinic clinic = new Clinic();
		
		clinic.setCreatedBy(clinicService.getAuthenticatedUser());
		clinic.setCreationDate(new Date());
		
		clinic.setLastUpdatedBy(clinicService.getAuthenticatedUser());
		clinic.setLastUpdateDate(new Date());
		
		mav.addObject("clinic", clinic);
		mav.addObject("readOnly", false);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.clinic.edit"), 2, request);
		
		ModelAndView mav = new ModelAndView(Navigation.CLINIC_EDIT.getPath());
		
		Clinic clinic = clinicService.find(id);
		
		clinic.setLastUpdatedBy(clinicService.getAuthenticatedUser());
		clinic.setLastUpdateDate(new Date());
		
		mav.addObject("clinic", clinic);
		mav.addObject("readOnly", false);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public @ResponseBody String remove(@PathVariable Long id) {
		String result = "";
		
		JSONObject jsonSubject = new JSONObject();
		JSONObject jsonItem = new JSONObject();
		
		Clinic clinic = clinicService.find(id);
		
		if (clinic != null) {
			try {
				clinicService.remove(clinic);
				
				result = MessageBundle.getMessageBundle("common.msg.remove.success");
				jsonItem.put("success", true);
			} catch (Throwable e) {
				result = MessageBundle.getMessageBundle("common.remove.msg.error");
				jsonItem.put("success", false);
			}
		}
		
		jsonItem.put("message", result);
		jsonSubject.accumulate("result", jsonItem);
		
		return jsonSubject.toString();
	}
	
	}
