package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "places", schema = "public", catalog = "catering")
public class PlacesEntity {
    private int id;
    private String address;
    private String postalCode;
    private int floor;
    private Integer hallNumber;
    private ClientsEntity clientsByClientid;

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
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "floor")
    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "hall_number")
    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacesEntity that = (PlacesEntity) o;
        return id == that.id &&
                floor == that.floor &&
                Objects.equals(address, that.address) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(hallNumber, that.hallNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, address, postalCode, floor, hallNumber);
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
