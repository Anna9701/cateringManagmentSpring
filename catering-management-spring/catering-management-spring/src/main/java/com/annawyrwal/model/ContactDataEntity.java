package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact_data", schema = "public", catalog = "catering")
public class ContactDataEntity {
    private int id;
    private String phone;
    private String email;
    private String fax;
    private ClientsEntity clientsByClientid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "fax")
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDataEntity that = (ContactDataEntity) o;
        return id == that.id &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(email, that.email) &&
                Objects.equals(fax, that.fax);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, phone, email, fax);
    }

    @ManyToOne
    @JoinColumn(name = "clientid", referencedColumnName = "id", nullable = false)
    public ClientsEntity getClientsByClientid() {
        return clientsByClientid;
    }

    public void setClientsByClientid(ClientsEntity clientsByClientid) {
        this.clientsByClientid = clientsByClientid;
    }
}
