package com.annawyrwal.Controller.Dish;

import com.annawyrwal.Service.Interfaces.DishEntityService;
import com.annawyrwal.Service.Interfaces.DishIngredientEntityService;
import com.annawyrwal.model.DishIngredientsEntity;
import com.annawyrwal.model.DishesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Optional;

@Controller
public class DishesController {
    @Autowired
    private DishEntityService dishEntityService;

    @Autowired
    private DishIngredientEntityService dishIngredientEntityService;

    @RequestMapping(value = {"/dish/dishes/{page}", "/dish/dishes"}, method = RequestMethod.GET)
    public ModelAndView showDishesPage(ModelAndView modelAndView, @PathVariable Optional<Integer> page) {
        modelAndView.setViewName("dish/dishes");

        List<DishesEntity> dishEntitiesList = dishEntityService.getAllDishEntities();
        PagedListHolder<DishesEntity> dishes = new PagedListHolder<>(dishEntitiesList);
        dishes.setPageSize(10);
        if (page.isPresent()) {
            int pageNumber = page.get();
            dishes.setPage(pageNumber);
        }

        modelAndView.addObject("dishesList", dishes);
        return modelAndView;
    }

    @RequestMapping(value = "/dish/create/", method = RequestMethod.GET)
    public ModelAndView showCreateDishPage(ModelAndView modelAndView) {
        modelAndView.setViewName("dish/create");
        DishesEntity dishesEntity = new DishesEntity();
        modelAndView.addObject("dish", dishesEntity);
        return modelAndView;
    }

    @RequestMapping(value="/dish/create", method = RequestMethod.POST)
    public String processRegistrationForm(ModelAndView modelAndView,
                                          @RequestParam("file") File file,
                                          DishesEntity dishEntity,
                                          BindingResult bindingResult,
                                          HttpServletRequest request) {
        modelAndView.setViewName("dish/create");
        String path = file.getAbsolutePath();
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
        catch (IOException e1) {
            System.out.println("Error Reading The File.");
            e1.printStackTrace();
        }
        dishEntity.setImage(b);
        dishEntityService.addDishEntity(dishEntity);
        return "redirect:/dish/dishes";
    }

    @RequestMapping(value = "/dish/photo/{dishId}", method = RequestMethod.GET)
    public ModelAndView showPhotoDishPage(ModelAndView modelAndView, @PathVariable int dishId) {
        modelAndView.setViewName("dish/photo");
        DishesEntity dishesEntity = dishEntityService.getDishEntity(dishId);

        modelAndView.addObject("dish", dishesEntity);

        return modelAndView;
    }

    @RequestMapping(value = "/dish/photo/image/{dishId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("dishId") int imageId) throws IOException {
        DishesEntity dishesEntity = dishEntityService.getDishEntity(imageId);
        byte[] imageContent = dishesEntity.getImage(); //get image from DAO based on id
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/dish/delete/{dishId}", method = RequestMethod.GET)
    public String deleteDish(ModelAndView modelAndView, @PathVariable int dishId) {
        DishesEntity dish = dishEntityService.getDishEntity(dishId);
        List<DishIngredientsEntity> dishIngredientsEntities = dishIngredientEntityService.getDishIngredientEntityByDish(dish);
        for (DishIngredientsEntity dishIngredientsEntity : dishIngredientsEntities) {
            dishIngredientEntityService.deleteDishIngredientEntity(dishIngredientsEntity.getId());
        }
        dishEntityService.deleteDishEntity(dishId);
        return "redirect:/dish/dishes";
    }

}
