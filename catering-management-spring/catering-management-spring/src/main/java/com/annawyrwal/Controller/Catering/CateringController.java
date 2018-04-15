package com.annawyrwal.Controller.Catering;

import com.annawyrwal.Service.Authentication.MyUserPrincipal;
import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.model.ClientsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CateringController {
    @Autowired
    private ClientEntityService clientEntityService;
    @Autowired
    private CateringEntityService cateringEntityService;

    @RequestMapping(value = {"/catering/catering/{page}", "/catering/catering"}, method = RequestMethod.GET)
    public ModelAndView showCateringPage(ModelAndView modelAndView, @PathVariable Optional<Integer> page){
        modelAndView.setViewName("catering/catering");

        MyUserPrincipal user = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ClientsEntity clientsEntity = clientEntityService.findByUser(user.getUser());
        List<CateringsEntity> clientCaterings = cateringEntityService.getCateringsEntitiesByClient(clientsEntity);

        PagedListHolder<CateringsEntity> caterings = new PagedListHolder<>(clientCaterings);
        caterings.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            caterings.setPage(pageNumber);
        }

        modelAndView.addObject("client", clientsEntity);
        modelAndView.addObject("cateringsList", caterings);

        return modelAndView;
    }

    @RequestMapping(value="/catering/catering/delete/{id}", method = RequestMethod.GET)
    public String deleteCatering(@PathVariable int id) {
        cateringEntityService.deleteCateringEntity(id);
        return "redirect:/catering/catering";
    }
}