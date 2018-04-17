package com.annawyrwal.Controller.Dish.DishIngredients;

import com.annawyrwal.Service.Interfaces.DishEntityService;
import com.annawyrwal.Service.Interfaces.DishIngredientEntityService;
import com.annawyrwal.Service.Interfaces.IngredientEntityService;
import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import com.annawyrwal.model.IngredientsEntity;
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
public class DishIngredientsController {
    @Autowired
    private DishIngredientEntityService dishIngredientEntityService;

    @Autowired
    private DishEntityService dishEntityService;

    @Autowired
    private IngredientEntityService ingredientEntityService;

    @RequestMapping(value = {"/dish/{dishId}/ingredients/{page}",
            "/dish/{dishId}/ingredients"}, method = RequestMethod.GET)
    public ModelAndView showIngredientsPage(ModelAndView modelAndView,
                                       @PathVariable int dishId,
                                       @PathVariable Optional<Integer> page){
        modelAndView.setViewName("dish/ingredients/ingredients");

        DishesEntity dishesEntity = dishEntityService.getDishEntity(dishId);
        List<DishIngredientsEntity> dishIngredientsEntities = dishIngredientEntityService
                .getDishIngredientEntityByDish(dishesEntity);

        PagedListHolder<DishIngredientsEntity> ingredients = new PagedListHolder<>(dishIngredientsEntities);
        ingredients.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            ingredients.setPage(pageNumber);
        }

        modelAndView.addObject("dishId", dishId);
        modelAndView.addObject("dishIngredientsList", ingredients);

        return modelAndView;
    }

    @RequestMapping(value = "/dish/ingredients/{dishIngredientId}", method = RequestMethod.GET)
    public String deleteDishOrder(ModelAndView modelAndView, @PathVariable int dishIngredientId) {
        int dishId = dishIngredientEntityService
                .getDishIngredientEntity(dishIngredientId)
                .getDishById()
                .getId();

        dishIngredientEntityService.deleteDishIngredientEntity(dishIngredientId);

        return "redirect:/dish/" + dishId + "/ingredients";
    }

    @RequestMapping(value = "/dish/ingredients/create/{dishId}", method = RequestMethod.GET)
    public ModelAndView getCreateDishIngredientPage(ModelAndView modelAndView, @PathVariable int dishId) {
        modelAndView.setViewName("/dish/ingredients/create");

        List<IngredientsEntity> ingredientsEntities = ingredientEntityService.getAllIngredientsEntity();

        DishIngredientsEntity dishIngredientEntity = new DishIngredientsEntity();
        dishIngredientEntity.setDishId(dishId);

        modelAndView.addObject("ingredientsList", ingredientsEntities);
        modelAndView.addObject("dishId", dishId);
        modelAndView.addObject("dishIngredient", dishIngredientEntity);

        return modelAndView;
    }

    @RequestMapping(value = "/dish/ingredients/create", method = RequestMethod.POST)
    public String createDishOrder(ModelAndView modelAndView,
                                  DishIngredientsEntity dishIngredientsEntity,
                                  BindingResult bindingResult,
                                  HttpServletRequest request) {

        dishIngredientsEntity.setDishById(dishEntityService.getDishEntity(dishIngredientsEntity.getDishId()));
        dishIngredientsEntity.setIngredientById(ingredientEntityService.getIngredientsEntity(dishIngredientsEntity.getIngredientId()));

        dishIngredientEntityService.addDishIngredientEntity(dishIngredientsEntity);

        return "redirect:/dish/" + dishIngredientsEntity.getDishId() + "/ingredients";
    }
}
