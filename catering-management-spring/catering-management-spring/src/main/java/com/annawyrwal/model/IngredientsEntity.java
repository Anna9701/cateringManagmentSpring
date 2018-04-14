package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ingredients", schema = "public", catalog = "catering")
public class IngredientsEntity {
    private int id;
    private float price;
    private String name;
    private int amountOf;
    private DishesEntity dishesByDishid;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price")
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        IngredientsEntity that = (IngredientsEntity) o;
        return id == that.id &&
                Float.compare(that.price, price) == 0 &&
                amountOf == that.amountOf &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, price, name, amountOf);
    }

    @ManyToOne
    @JoinColumn(name = "dishid", referencedColumnName = "id", nullable = false)
    public DishesEntity getDishesByDishid() {
        return dishesByDishid;
    }

    public void setDishesByDishid(DishesEntity dishesByDishid) {
        this.dishesByDishid = dishesByDishid;
    }
}
