package com.annawyrwal.Controller.Catering.Orders.Dates;

import com.annawyrwal.Service.Interfaces.DateEntityService;
import com.annawyrwal.Service.Interfaces.OrderEntityService;
import com.annawyrwal.model.DatesEntity;
import com.annawyrwal.model.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DatesControllers {
    @Autowired
    private DateEntityService dateEntityService;

    @Autowired
    private OrderEntityService orderEntityService;

    @RequestMapping(value = "/catering/orders/date/{dateId}", method = RequestMethod.GET)
    public ModelAndView showDatePage(ModelAndView modelAndView, @PathVariable int dateId) {
        modelAndView.setViewName("catering/orders/date");

        DatesEntity datesEntities = dateEntityService.getDateEntity(dateId);
        OrdersEntity ordersEntity = orderEntityService.getOrdersByDatesEntity(datesEntities).get(0);

        modelAndView.addObject("date", datesEntities);
        modelAndView.addObject("cateringId", ordersEntity.getCateringsByCateringid().getId());

        return modelAndView;
    }

    @RequestMapping(value = "/catering/orders/date/", method = RequestMethod.POST)
    public ModelAndView updateDate(ModelAndView modelAndView,
                                   DatesEntity datesEntity,
                                   BindingResult bindingResult,
                                   HttpServletRequest request) {
        modelAndView.setViewName("catering/orders/date/" + datesEntity.getId());

        dateEntityService.updateDateEntity(datesEntity);

        return modelAndView;
    }
}
