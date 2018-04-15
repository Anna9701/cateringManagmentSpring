package com.annawyrwal.Controller.Catering.Orders.Dishes;

import com.annawyrwal.Service.Interfaces.DishEntityService;
import com.annawyrwal.Service.Interfaces.DishOrderEntityService;
import com.annawyrwal.Service.Interfaces.OrderEntityService;
import com.annawyrwal.model.DishOrdersEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DishesController {
    class DishOrder {
        DishOrdersEntity dishOrderEntity;
        DishesEntity dish;
        DishOrder(DishOrdersEntity dishOrdersEntity, DishesEntity dish) {
            this.dish = dish;
            this.dishOrderEntity = dishOrdersEntity;
        }
    }

    @Autowired
    private DishOrderEntityService dishOrderEntityService;

    @Autowired
    private OrderEntityService orderEntityService;

    @Autowired
    private DishEntityService dishEntityService;

    @RequestMapping(value = {"/catering/orders/{orderId}/dishes/{page}",
            "/catering/orders/{orderId}/dishes"}, method = RequestMethod.GET)
    public ModelAndView showOrdersPage(ModelAndView modelAndView,
                                       @PathVariable int orderId,
                                       @PathVariable Optional<Integer> page){
        modelAndView.setViewName("catering/orders/dishes/dishes");

        List<DishOrder> dishOrdersList = new ArrayList<>();

        List<DishOrdersEntity> dishOrdersEntitiesList = dishOrderEntityService.getDishOrderEntityByOrder(orderId);
        for (DishOrdersEntity dishOrder: dishOrdersEntitiesList) {
            dishOrdersList.add(new DishOrder(dishOrder, dishEntityService.getDishEntity(dishOrder.getDishid())));
        }

        PagedListHolder<DishOrder> orders = new PagedListHolder<>(dishOrdersList);
        orders.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            orders.setPage(pageNumber);
        }

        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("dishOrdersList", orders);

        return modelAndView;
    }
}
