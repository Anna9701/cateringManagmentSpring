package com.annawyrwal.Controller.Ingredient;

import com.annawyrwal.Service.Interfaces.DishEntityService;
import com.annawyrwal.Service.Interfaces.DishIngredientEntityService;
import com.annawyrwal.Service.Interfaces.IngredientEntityService;
import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class IngredientsController {
    @Autowired
    private IngredientEntityService ingredientEntityService;

    @Autowired
    private DishIngredientEntityService dishIngredientEntityService;

    @RequestMapping(value = {"/ingredient/ingredients/{page}", "/ingredient/ingredients"}, method = RequestMethod.GET)
    public ModelAndView showIngredientsPage(ModelAndView modelAndView, @PathVariable Optional<Integer> page) {
        modelAndView.setViewName("ingredient/ingredients");

        List<IngredientsEntity> ingredientsEntityList = ingredientEntityService.getAllIngredientsEntity();
        PagedListHolder<IngredientsEntity> ingredients = new PagedListHolder<>(ingredientsEntityList);
        ingredients.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            ingredients.setPage(pageNumber);
        }

        modelAndView.addObject("ingredientsList", ingredients);
        return modelAndView;
    }


    @RequestMapping(value = {"/ingredient/{ingredientId}/dishes/{page}",
            "/ingredient/{ingredientId}/dishes"}, method = RequestMethod.GET)
    public ModelAndView showDishesPage(ModelAndView modelAndView,
                                       @PathVariable int ingredientId,
                                       @PathVariable Optional<Integer> page) {
        modelAndView.setViewName("ingredient/dishes");

        IngredientsEntity ingredientsEntity = ingredientEntityService.getIngredientsEntity(ingredientId);
        List<DishIngredientsEntity> dishIngredientsEntities = dishIngredientEntityService
                .getDishIngredientEntityByIngredient(ingredientsEntity);
        PagedListHolder<DishIngredientsEntity> dishIngredientsEntityPagedListHolder = new PagedListHolder<>(dishIngredientsEntities);
        dishIngredientsEntityPagedListHolder.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            dishIngredientsEntityPagedListHolder.setPage(pageNumber);
        }
        modelAndView.addObject("ingredient", ingredientsEntity);
        modelAndView.addObject("dishesList", dishIngredientsEntityPagedListHolder);
        return modelAndView;
    }

    @RequestMapping(value = "/ingredient/create/", method = RequestMethod.GET)
    public ModelAndView showCreateIngredientPage(ModelAndView modelAndView) {
        modelAndView.setViewName("ingredient/create");
        IngredientsEntity ingredientsEntity = new IngredientsEntity();
        modelAndView.addObject("ingredient", ingredientsEntity);
        return modelAndView;
    }

    @RequestMapping(value = "/ingredient/delete/{ingredientId}", method = RequestMethod.GET)
    public ModelAndView deleteIngredient(ModelAndView modelAndView, @PathVariable int ingredientId) {
        modelAndView.setViewName("ingredient/ingredients");
        try {
            ingredientEntityService.deleteIngredientsEntity(ingredientId);
        } catch (Exception exception) {
            modelAndView.addObject("ErrorMessage", "You cannot delete this ingredient." +
                    "There are dishes containing it.");
        }
        return showIngredientsPage(modelAndView, Optional.ofNullable(1));
    }

    @RequestMapping(value="/ingredient/create", method = RequestMethod.POST)
    public String processCreateForm(ModelAndView modelAndView,
                                          IngredientsEntity ingredientsEntity,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        modelAndView.setViewName("ingredient/create");
        ingredientEntityService.addIngredientsEntity(ingredientsEntity);
        return "redirect:/ingredient/ingredients";
    }

    @RequestMapping(value="/ingredient/edit/{ingredientId}", method = RequestMethod.GET)
    public ModelAndView getEditForm(ModelAndView modelAndView, @PathVariable int ingredientId) {
        modelAndView.setViewName("ingredient/edit");
        IngredientsEntity ingredientsEntity = ingredientEntityService.getIngredientsEntity(ingredientId);

        modelAndView.addObject("ingredient", ingredientsEntity);
        return modelAndView;
    }

    @RequestMapping(value="/ingredient/edit", method = RequestMethod.POST)
    public String processEditForm(ModelAndView modelAndView,
                                          IngredientsEntity ingredientsEntity,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        modelAndView.setViewName("ingredient/edit");
        ingredientEntityService.updateIngredientsEntity(ingredientsEntity);
        return "redirect:/ingredient/ingredients";
    }
}