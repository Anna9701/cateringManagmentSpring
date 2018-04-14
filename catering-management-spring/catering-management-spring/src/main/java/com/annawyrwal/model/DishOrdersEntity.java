package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dish_orders", schema = "public", catalog = "catering")
public class DishOrdersEntity {
    private int orderid;
    private int dishid;
    private Boolean takeaway;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "orderid")
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    @Basic
    @Column(name = "takeaway")
    public Boolean getTakeaway() {
        return takeaway;
    }

    public void setTakeaway(Boolean takeaway) {
        this.takeaway = takeaway;
    }

    @Basic
    @Column(name = "dishid")
    public int getDishid() {
        return dishid;
    }

    public void setDishid(int id) {
        this.dishid = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishOrdersEntity that = (DishOrdersEntity) o;
        return orderid == that.orderid && dishid == that.dishid &&
                Objects.equals(takeaway, that.takeaway);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderid, dishid, takeaway);
    }
}
