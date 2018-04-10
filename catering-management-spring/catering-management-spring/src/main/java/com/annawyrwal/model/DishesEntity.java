package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dishes", schema = "public", catalog = "catering")
public class DishesEntity {
    private int id;
    private String name;
    private byte[] image;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishesEntity that = (DishesEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }



    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Lob
    @Column(name="dishImage", columnDefinition = "bytea")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
