package com.annawyrwal.Controller;

import com.annawyrwal.Service.Authentication.UserService;
import com.annawyrwal.Service.Interfaces.ClientEntityService;
import com.annawyrwal.model.ClientsEntity;
import com.annawyrwal.model.User;
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
public class ClientListController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientEntityService clientEntityService;

    @RequestMapping(value = {"/clientList", "/clientList/{page}"}, method = RequestMethod.GET)
    public ModelAndView showIndexPage(ModelAndView modelAndView, @PathVariable Optional<Integer> page){
        modelAndView.setViewName("/adminUtil/clientsList");

        List<ClientsEntity> clientsEntityList = clientEntityService.getAllClients();

        PagedListHolder<ClientsEntity> clients = new PagedListHolder<>(clientsEntityList);
        clients.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            clients.setPage(pageNumber);
        }

        modelAndView.addObject("clientsList", clients);
        return modelAndView;
    }

    @RequestMapping(value = "/adminUtil/disable/{clientId}", method = RequestMethod.GET)
    public ModelAndView disableClient(ModelAndView modelAndView, @PathVariable int clientId) {
        modelAndView.setViewName("/adminUtil/clientsList");
        ClientsEntity clientsEntity = clientEntityService.getClient(clientId);
        User user = clientsEntity.getUserByUsername();
        user.setEnabled(false);
        userService.saveUser(user);
        return showIndexPage(modelAndView, Optional.of(1));
    }

    @RequestMapping(value = "/adminUtil/enable/{clientId}", method = RequestMethod.GET)
    public ModelAndView enableClient(ModelAndView modelAndView, @PathVariable int clientId) {
        modelAndView.setViewName("/adminUtil/clientsList");
        ClientsEntity clientsEntity = clientEntityService.getClient(clientId);
        User user = clientsEntity.getUserByUsername();
        user.setEnabled(true);
        userService.saveUser(user);
        return showIndexPage(modelAndView, Optional.of(1));
    }
}
