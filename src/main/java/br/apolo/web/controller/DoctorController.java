package br.apolo.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.apolo.business.service.ClinicService;
import br.apolo.business.service.DoctorService;
import br.apolo.business.service.SpecialtyService;
import br.apolo.business.service.UserGroupService;
import br.apolo.common.exception.AccessDeniedException;
import br.apolo.common.util.MessageBundle;
import br.apolo.data.model.Doctor;
import br.apolo.data.model.User;
import br.apolo.data.model.UserGroup;
import br.apolo.security.SecuredEnum;
import br.apolo.security.UserPermission;
import br.apolo.web.enums.Navigation;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController extends BaseController<Doctor> {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	UserGroupService userGroupService;
	
	@Autowired
	SpecialtyService specialtyService;
	
	@Autowired
	ClinicService clinicService;

	@Override
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.doctor.list"), 1, request);
		
		ModelAndView mav = new ModelAndView(Navigation.DOCTOR_LIST.getPath());
		
		List<Doctor> doctorList = doctorService.list();
		
		mav.addObject("doctorList", doctorList);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("entity") Doctor entity, BindingResult result,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		if (result.hasErrors()) {
			mav.setViewName(getRedirectionPath(request, Navigation.DOCTOR_NEW, Navigation.DOCTOR_EDIT));
			
			mav.addObject("doctor", entity);
			mav.addObject("readOnly", false);
			mav.addObject("groupList", userGroupService.list());
			mav.addObject("specialtyList", specialtyService.list());
			mav.addObject("clinicList", clinicService.list());
			mav.addObject("error", true);
			
			StringBuilder message = new StringBuilder();
			for (ObjectError error : result.getAllErrors()) {
				DefaultMessageSourceResolvable argument = (DefaultMessageSourceResolvable) error.getArguments()[0];
				
				message.append(MessageBundle.getMessageBundle("common.field") + " " + MessageBundle.getMessageBundle("doctor." + argument.getDefaultMessage()) + ": " + error.getDefaultMessage() + "\n <br />");
			}
						
			mav.addObject("message", message.toString());
			
			return mav;
		}
		
		if (entity != null) {
			try {
				doctorService.save(entity);
				
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
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.doctor"), 2, request);
		
		ModelAndView mav = new ModelAndView(Navigation.DOCTOR_VIEW.getPath());
		
		Doctor doctor = doctorService.find(id);
		
		mav.addObject("doctor", doctor);
		mav.addObject("clinicList", clinicService.list());
		mav.addObject("readOnly", true);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.doctor.new"), 1, request);
		
		ModelAndView mav = new ModelAndView(Navigation.DOCTOR_NEW.getPath());
		
		Doctor doctor = new Doctor();
		User user = new User();
		Set<UserGroup> groups = new HashSet<UserGroup>();
		user.setGroups(groups);
		
		doctor.setUser(user);
		
		doctor.setCreatedBy(doctorService.getAuthenticatedUser());
		doctor.setCreationDate(new Date());
		
		doctor.setLastUpdatedBy(doctorService.getAuthenticatedUser());
		doctor.setLastUpdateDate(new Date());
		
		mav.addObject("doctor", doctor);
		mav.addObject("groupList", userGroupService.list());
		mav.addObject("specialtyList", specialtyService.list());
		mav.addObject("clinicList", clinicService.list());
		mav.addObject("readOnly", false);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long id, HttpServletRequest request) {
		breadCrumbService.addNode(MessageBundle.getMessageBundle("breadcrumb.doctor.edit"), 2, request);
		
		ModelAndView mav = new ModelAndView(Navigation.DOCTOR_EDIT.getPath());
		
		Doctor doctor = doctorService.find(id);
		
		doctor.setLastUpdatedBy(doctorService.getAuthenticatedUser());
		doctor.setLastUpdateDate(new Date());
		
		mav.addObject("doctor", doctor);
		mav.addObject("groupList", userGroupService.list());
		mav.addObject("specialtyList", specialtyService.list());
		mav.addObject("clinicList", clinicService.list());
		mav.addObject("readOnly", false);
		mav.addObject("editing", true);
		
		return mav;
	}

	@Override
	@SecuredEnum(UserPermission.DOCTOR)
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public @ResponseBody String remove(@PathVariable Long id) {
		String result = "";
		
		JSONObject jsonSubject = new JSONObject();
		JSONObject jsonItem = new JSONObject();
		
		Doctor doctor = doctorService.find(id);
		
		if (doctor != null) {
			try {
				doctorService.remove(doctor);
				
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
	
	
	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Set.class, "user.groups", new CustomCollectionEditor(Set.class) {
            @Override
            protected Object convertElement(Object element) {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    } catch (NumberFormatException e) {
                        log.error("Element was " + ((String) element), e);
                    }
                } else if(element instanceof Long) {
                    //From the database 'element' will be a Long
                    id = (Long) element;
                }

                return id != null ? userGroupService.find(id) : null;
            }
          });
        
   
        
        binder.registerCustomEditor(List.class, "specialties", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    } catch (NumberFormatException e) {
                        log.error("Element was " + ((String) element), e);
                    }
                } else if(element instanceof Long) {
                    //From the database 'element' will be a Long
                    id = (Long) element;
                }

                return id != null ? specialtyService.find(id) : null;
            }
          });
        
        binder.registerCustomEditor(List.class, "clinics", new CustomCollectionEditor(List.class) {
            @Override
            protected Object convertElement(Object element) {
                Long id = null;

                if(element instanceof String && !((String)element).equals("")){
                    //From the JSP 'element' will be a String
                    try{
                        id = Long.parseLong((String) element);
                    } catch (NumberFormatException e) {
                        log.error("Element was " + ((String) element), e);
                    }
                } else if(element instanceof Long) {
                    //From the database 'element' will be a Long
                    id = (Long) element;
                }

                return id != null ? clinicService.find(id) : null;
            }
          });
    }
	
}
