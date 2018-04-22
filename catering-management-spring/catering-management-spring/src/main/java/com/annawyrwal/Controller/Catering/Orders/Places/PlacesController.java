package com.annawyrwal.Controller.Catering.Orders.Places;

import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.Service.Interfaces.OrderEntityService;
import com.annawyrwal.Service.Interfaces.PlaceEntityService;
import com.annawyrwal.model.OrdersEntity;
import com.annawyrwal.model.PlacesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PlacesController {
    @Autowired
    private PlaceEntityService placeEntityService;

    @Autowired
    private OrderEntityService orderEntityService;

    @RequestMapping(value = "/catering/orders/place/{placeId}", method = RequestMethod.GET)
    public ModelAndView showDatePage(ModelAndView modelAndView, @PathVariable int placeId) {
        modelAndView.setViewName("catering/orders/place");

        PlacesEntity placesEntity = placeEntityService.getPlaceEntity(placeId);
        OrdersEntity ordersEntity = orderEntityService.getOrdersByPlacesEntity(placesEntity).get(0);

        modelAndView.addObject("place", placesEntity);
        modelAndView.addObject("cateringId", ordersEntity.getCateringsByCateringid().getId());

        return modelAndView;
    }

    @RequestMapping(value = "/catering/orders/place/", method = RequestMethod.POST)
    public ModelAndView updatePlace(ModelAndView modelAndView,
                                   PlacesEntity placesEntity,
                                   BindingResult bindingResult,
                                   HttpServletRequest request) {
        modelAndView.setViewName("catering/orders/place/" + placesEntity.getId());

        placeEntityService.updatePlaceEntity(placesEntity);

        return modelAndView;
    }
}
