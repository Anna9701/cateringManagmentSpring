package com.zai.caterings.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "public", catalog = "catering")
public class RolesEntity {
    private int id;
    private Boolean isadmin;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "isadmin")
    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesEntity that = (RolesEntity) o;
        return id == that.id &&
                Objects.equals(isadmin, that.isadmin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, isadmin);
    }
}
