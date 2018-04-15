package com.annawyrwal.Controller.Catering;

import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.model.CateringsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
public class CreateController {
    @Autowired
    private ClientEntityService clientEntityService;
    @Autowired
    private CateringEntityService cateringEntityService;

    @RequestMapping(value="/catering/create/{clientId}", method = RequestMethod.GET)
    public ModelAndView showCreatePage (ModelAndView modelAndView, @PathVariable int clientId) {
        modelAndView.setViewName("catering/create");
        CateringsEntity cateringsEntity = new CateringsEntity();
        cateringsEntity.setClientId(clientId);

        modelAndView.addObject("catering", cateringsEntity);

        return modelAndView;
    }

    @RequestMapping(value="/catering/create", method = RequestMethod.POST)
    public String processRegistrationForm(ModelAndView modelAndView, CateringsEntity cateringsEntity, BindingResult bindingResult, HttpServletRequest request) {
        modelAndView.setViewName("catering/create");
        cateringsEntity.setClientsByClientid(clientEntityService.getClient(cateringsEntity.getClientId()));
        cateringEntityService.addCateringEntity(cateringsEntity);
        return "redirect:/catering/catering";
    }
}
