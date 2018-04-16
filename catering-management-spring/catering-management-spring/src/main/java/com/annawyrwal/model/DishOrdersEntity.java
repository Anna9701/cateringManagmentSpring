package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dish_orders", schema = "public", catalog = "catering")
public class DishOrdersEntity {
    private int id;
    private int orderId;
    private int dishId;
    private OrdersEntity orderById;
    private DishesEntity dishById;
    private Boolean takeaway;

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
    @Column(name = "takeaway")
    public Boolean getTakeaway() {
        return takeaway;
    }

    public void setTakeaway(Boolean takeaway) {
        this.takeaway = takeaway;
    }

    @OneToOne
    @JoinColumn(name = "dishid", referencedColumnName = "id", nullable = false)
    public DishesEntity getDishById() {
        return dishById;
    }

    public void setDishById(DishesEntity dish) {
        this.dishById = dish;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishOrdersEntity that = (DishOrdersEntity) o;
        return id == that.id && dishById == that.dishById && orderById == that.orderById &&
                Objects.equals(takeaway, that.takeaway);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderById, dishById, takeaway);
    }

    @OneToOne
    @JoinColumn(name = "orderid", referencedColumnName = "id", nullable = false)
    public OrdersEntity getOrderById() {
        return orderById;
    }

    public void setOrderById(OrdersEntity orderById) {
        this.orderById = orderById;
    }

    @Transient
    public int getOrderId() {
        return orderId;
    }

    @Transient
    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
