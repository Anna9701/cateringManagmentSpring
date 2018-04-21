package com.annawyrwal.Controller.Catering.Orders;

import com.annawyrwal.Service.Interfaces.CateringEntityService;
import com.annawyrwal.Service.Interfaces.DateEntityService;
import com.annawyrwal.Service.Interfaces.OrderEntityService;
import com.annawyrwal.Service.Interfaces.PlaceEntityService;
import com.annawyrwal.model.CateringsEntity;
import com.annawyrwal.model.DatesEntity;
import com.annawyrwal.model.OrdersEntity;
import com.annawyrwal.model.PlacesEntity;
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
public class OrdersController {
    @Autowired
    private OrderEntityService orderEntityService;

    @Autowired
    private CateringEntityService cateringEntityService;

    @Autowired
    private DateEntityService dateEntityService;

    @Autowired
    private PlaceEntityService placeEntityService;

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
        OrdersEntity orderEntity = orderEntityService.getOrderEntity(orderId);
        int cateringId = orderEntity.getCateringsByCateringid().getId();
        orderEntityService.deleteOrderEntity(orderId);
        return "redirect:/catering/" + cateringId + "/orders/orders";
    }

    @RequestMapping(value="/catering/orders/create/{cateringId}", method = RequestMethod.GET)
    public ModelAndView showCreatePage (ModelAndView modelAndView, @PathVariable int cateringId) {
        modelAndView.setViewName("catering/orders/create");
        OrdersEntity orderEntity = new OrdersEntity();
        orderEntity.setCateringId(cateringId);
        DatesEntity dateEntity = new DatesEntity();
        PlacesEntity placeEntity = new PlacesEntity();

        modelAndView.addObject("order", orderEntity);
        modelAndView.addObject("date", dateEntity);
        modelAndView.addObject("place", placeEntity);

        return modelAndView;
    }

    @RequestMapping(value="/catering/orders/edit/{orderId}", method = RequestMethod.GET)
    public ModelAndView updateOrder(ModelAndView modelAndView, @PathVariable int orderId) {
        modelAndView.setViewName("catering/orders/edit");
        OrdersEntity ordersEntity = orderEntityService.getOrderEntity(orderId);
        modelAndView.addObject("order", ordersEntity);
        return modelAndView;
    }

    @RequestMapping(value="/catering/orders/edit", method = RequestMethod.POST)
    public String updateOrder(ModelAndView modelAndView,
                                          OrdersEntity orderEntity,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        modelAndView.setViewName("catering/orders/edit");

        orderEntityService.updateOrderEntity(orderEntity);

        return "redirect:/catering/" + orderEntity.getCateringId() + "/orders/orders";
    }

    @RequestMapping(value="/catering/orders/create", method = RequestMethod.POST)
    public String processRegistrationForm(ModelAndView modelAndView,
                                          OrdersEntity orderEntity,
                                          DatesEntity dateEntity,
                                          PlacesEntity placeEntity,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        modelAndView.setViewName("catering/orders/create");

        dateEntityService.addDateEntity(dateEntity);

        CateringsEntity parentCatering = cateringEntityService.findCateringById(orderEntity.getCateringId());

        orderEntity.setCateringsByCateringid(parentCatering);
        orderEntity.setClientsByClientid(parentCatering.getClientsByClientid());
        orderEntity.setDatesByDateid(dateEntity);

        placeEntity.setClientsByClientid(parentCatering.getClientsByClientid());
        placeEntityService.addPlaceEntity(placeEntity);
        orderEntity.setPlacesByPlaceid(placeEntity);

        orderEntityService.addOrderEntity(orderEntity);
        return "redirect:/catering/" + orderEntity.getCateringId() + "/orders/orders";
    }
}

//TODO : liczenie ceny
