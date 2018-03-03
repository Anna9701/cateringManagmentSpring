package com.zai.caterings.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "catering")
public class UsersEntity {
    private int id;
    private int username;
    private RolesEntity rolesByRoleid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                username == that.username;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username);
    }

    @ManyToOne
    @JoinColumn(name = "roleid", referencedColumnName = "id", nullable = false)
    public RolesEntity getRolesByRoleid() {
        return rolesByRoleid;
    }

    public void setRolesByRoleid(RolesEntity rolesByRoleid) {
        this.rolesByRoleid = rolesByRoleid;
    }
}
