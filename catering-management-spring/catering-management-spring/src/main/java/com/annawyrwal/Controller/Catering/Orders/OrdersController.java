package com.annawyrwal.Controller.Catering.Orders;

import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.Service.Interfaces.OrderEntityService;
import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.model.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class OrdersController {
    @Autowired
    private OrderEntityService orderEntityService;

    @Autowired
    private CateringEntityService cateringEntityService;

    @RequestMapping(value = {"/catering/{cateringId}/orders/orders/{page}",
            "/catering/{cateringId}/orders/orders"}, method = RequestMethod.GET)
    public ModelAndView showOrdersPage(ModelAndView modelAndView,
                                         @PathVariable int cateringId,
                                         @PathVariable Optional<Integer> page){
        modelAndView.setViewName("catering/orders/orders");

        CateringsEntity catering = cateringEntityService.findCateringById(cateringId);
        List<OrdersEntity> cateringOrders = orderEntityService.getOrdersByCateringsEntity(catering);

        PagedListHolder<OrdersEntity> orders = new PagedListHolder<>(cateringOrders);
        orders.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            orders.setPage(pageNumber);
        }

        modelAndView.addObject("catering", catering);
        modelAndView.addObject("ordersList", orders);

        return modelAndView;
    }

    @RequestMapping(value = "/catering/orders/delete/{orderId}", method = RequestMethod.GET)
    public String deleteCatering(@PathVariable int orderId) {
        orderEntityService.deleteOrderEntity(orderId);
        return "redirect:/catering/catering";
    }
}
