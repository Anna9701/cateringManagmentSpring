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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class DishOrdersController {
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

        OrdersEntity ordersEntity = orderEntityService.getOrderEntity(orderId);
        List<DishOrdersEntity> dishOrdersEntitiesList = dishOrderEntityService.getDishOrderEntityByOrder(orderEntityService.getOrderEntity(orderId));

        PagedListHolder<DishOrdersEntity> orders = new PagedListHolder<>(dishOrdersEntitiesList);
        orders.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            orders.setPage(pageNumber);
        }

        modelAndView.addObject("cateringId", ordersEntity.getCateringId());
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("dishOrdersList", orders);

        return modelAndView;
    }

    @RequestMapping(value = "/catering/orders/dishes/{dishOrderId}", method = RequestMethod.GET)
    public String deleteDishOrder(ModelAndView modelAndView, @PathVariable int dishOrderId) {
        int orderId = dishOrderEntityService.getDishOrderEntity(dishOrderId).getOrderById().getId();

        dishEntityService.deleteDishEntity(dishOrderId);

        return "redirect:/catering/orders/" + orderId + "/dishes";
    }

    @RequestMapping(value = "/catering/orders/dishes/create/{orderId}", method = RequestMethod.GET)
    public ModelAndView getCreateDishOrderPage(ModelAndView modelAndView, @PathVariable int orderId) {
        modelAndView.setViewName("/catering/orders/dishes/create");

        List<DishesEntity> dishesEntityList = dishEntityService.getAllDishEntities();

        DishOrdersEntity dishOrdersEntity = new DishOrdersEntity();
        dishOrdersEntity.setOrderId(orderId);

        modelAndView.addObject("dishesList", dishesEntityList);
        modelAndView.addObject("orderId", orderId);
        modelAndView.addObject("dishOrder", dishOrdersEntity);

        return modelAndView;
    }

    @RequestMapping(value = "/catering/orders/dishes/create", method = RequestMethod.POST)
    public String createDishOrder(ModelAndView modelAndView, DishOrdersEntity dishOrdersEntity, BindingResult bindingResult, HttpServletRequest request) {

        dishOrdersEntity.setDishById(dishEntityService.getDishEntity(dishOrdersEntity.getDishId()));
        dishOrdersEntity.setOrderById(orderEntityService.getOrderEntity(dishOrdersEntity.getOrderId()));

        dishOrderEntityService.addDishOrderEntity(dishOrdersEntity);

        return "redirect:/catering/orders/" + dishOrdersEntity.getOrderId() + "/dishes";
    }
}
