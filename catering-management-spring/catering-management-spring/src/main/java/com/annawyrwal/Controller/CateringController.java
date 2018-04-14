package com.annawyrwal.Controller;

import com.annawyrwal.Service.Authentication.MyUserPrincipal;
import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.model.ClientsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CateringController {
    @Autowired
    private ClientEntityService clientEntityService;
    @Autowired
    private CateringEntityService cateringEntityService;

    @RequestMapping(value="/catering/catering", method = RequestMethod.GET)
    public ModelAndView showCateringPage(ModelAndView modelAndView){
        modelAndView.setViewName("catering/catering");

        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientsEntity clientsEntity = clientEntityService.findByUser(user.getUser());
        List<CateringsEntity> clientCaterings = cateringEntityService.getCateringsEntitiesByClient(clientsEntity);

        modelAndView.addObject("client", clientsEntity);
        modelAndView.addObject("cateringsList", clientCaterings);

        return modelAndView;
    }

    @RequestMapping(value="/catering/catering/delete/{id}", method = RequestMethod.GET)
    public String deleteCatering(@PathVariable int id) {
        cateringEntityService.deleteCateringEntity(id);
        return "redirect:/catering/catering";
    }

    @RequestMapping(value="/catering/catering/create/{clientId}", method = RequestMethod.GET)
    public String createCatering (@PathVariable int clientId) {
        CateringsEntity cateringsEntity = new CateringsEntity();
        cateringsEntity.setClientsByClientid(clientEntityService.getClient(clientId));
        cateringsEntity.setName("Test");
        cateringEntityService.addCateringEntity(cateringsEntity);
        return "redirect:/catering/catering";
    }
}
