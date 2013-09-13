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

import br.apolo.business.service.SicknessService;
import br.apolo.common.exception.AccessDeniedException;
import br.apolo.common.util.MessageBundle;
import br.apolo.data.model.Sickness;
import br.apolo.security.SecuredEnum;
import br.apolo.security.UserPermission;
import br.apolo.web.enums.Navigation;

@Controller
@RequestMapping(value = "/sickness")
public class SicknessController extends BaseController<Sickness> {

	@Autowired
	private SicknessService sicknessService;
	
	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.sickness.list"), 1, request);
		
		ModelAndView mav = new ModelAndView(Navigation.SICKNESS_LIST.getPath());
		
		List<Sickness> sicknessList = sicknessService.list();
		
		mav.addObject("sicknessList", sicknessList);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute ("entity") Sickness entity, BindingResult result,
			HttpServletRequest request) {
		
	
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.setViewName(getRedirectionPath(request, Navigation.SICKNESS_NEW, Navigation.SICKNESS_EDIT));
			
			mav.addObject("sickness", entity);
			mav.addObject("readOnly", false);
			mav.addObject("error", true);
			
			StringBuilder message = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				DefaultMessageSourceResolvable argument = (DefaultMessageSourceResolvable) error.getArguments()[0];
				
				message.append(MessageBundle.getMessageBundle("common.field") + " " + MessageBundle.getMessageBundle("sickness." + argument.getDefaultMessage()) + ": " + error.getDefaultMessage() + "\n <br />");
			}
						
			mav.addObject("message", message.toString());
			
		
		return mav;
		
		}
		if (entity != null) {
			try {
				sicknessService.save(entity);
				
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

	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable Long id, HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.sickness"), 2, request);
		
		ModelAndView mav = new ModelAndView(Navigation.SICKNESS_VIEW.getPath());
		
		Sickness sickness = sicknessService.find(id);
		
		mav.addObject("sickness", sickness);
		mav.addObject("readOnly", true);
		
		return mav;
	}
	
	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.sickness.new"), 1, request);
		
		ModelAndView mav = new ModelAndView(Navigation.SICKNESS_NEW.getPath());
		
		Sickness sickness = new Sickness();
		
		sickness.setCreatedBy(sicknessService.getAuthenticatedUser());
		sickness.setCreationDate(new Date());
		
		sickness.setLastUpdatedBy(sicknessService.getAuthenticatedUser());
		sickness.setLastUpdateDate(new Date());
		
		mav.addObject("sickness", sickness);
		mav.addObject("readOnly", false);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.sickness.edit"), 2, request);
		
		ModelAndView mav = new ModelAndView(Navigation.SICKNESS_EDIT.getPath());
		
		Sickness sickness = sicknessService.find(id);
		
		sickness.setLastUpdatedBy(sicknessService.getAuthenticatedUser());
		sickness.setLastUpdateDate(new Date());
		
		mav.addObject("sickness", sickness);
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
		
		Sickness sickness = sicknessService.find(id);
		
		if (sickness != null) {
			try {
				sicknessService.remove(sickness);
				
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