package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "caterings", schema = "public", catalog = "catering")
public class CateringsEntity {
    private int id;
    private String name;
    private ClientsEntity clientsByClientid;

    @Transient
    private int clientId;

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
        CateringsEntity that = (CateringsEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @ManyToOne
    @JoinColumn(name = "clientid", referencedColumnName = "id", nullable = false)
    public ClientsEntity getClientsByClientid() {
        return clientsByClientid;
    }

    public void setClientsByClientid(ClientsEntity clientsByClientid) {
        this.clientsByClientid = clientsByClientid;
    }

    @Transient
    public int getClientId() {
        return clientId;
    }

    @Transient
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
