package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dish_ingredients", schema = "public", catalog = "catering")
public class DishIngredientsEntity {
    private int id;
    private int ingredientId;
    private int dishId;
    private IngredientsEntity ingredientById;
    private DishesEntity dishById;
    private int amountOf;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "dishid", referencedColumnName = "id", nullable = false)
    public DishesEntity getDishById() {
        return dishById;
    }

    public void setDishById(DishesEntity dish) {
        this.dishById = dish;
    }

    @Basic
    @Column(name = "amount_of")
    public int getAmountOf() {
        return amountOf;
    }

    public void setAmountOf(int amountOf) {
        this.amountOf = amountOf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredientsEntity that = (DishIngredientsEntity) o;
        return id == that.id && dishById == that.dishById && ingredientById == that.ingredientById;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, ingredientById, dishById);
    }

    @OneToOne
    @JoinColumn(name = "ingredientid", referencedColumnName = "id", nullable = false)
    public IngredientsEntity getIngredientById() {
        return ingredientById;
    }

    public void setIngredientById(IngredientsEntity ingredientById) {
        this.ingredientById = ingredientById;
    }

    @Transient
    public int getIngredientId() {
        return ingredientId;
    }

    @Transient
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Transient
    public int getDishId() {
        return dishId;
    }

    @Transient
    public void setDishId(int dishId) {
        this.dishId = dishId;
    }
}
