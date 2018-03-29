package com.annawyrwal.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "catering")
public class OrdersEntity {
    private int id;
    private int priority;
    private int amountOfPeople;
    private float cost;
    private boolean invoice;
    private boolean payedUp;
    private CateringsEntity cateringsByCateringid;
    private ClientsEntity clientsByClientid;
    private PlacesEntity placesByPlaceid;
    private DatesEntity datesByDateid;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "amount_of_people")
    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    @Basic
    @Column(name = "cost")
    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "invoice")
    public boolean isInvoice() {
        return invoice;
    }

    public void setInvoice(boolean invoice) {
        this.invoice = invoice;
    }

    @Basic
    @Column(name = "payed_up")
    public boolean isPayedUp() {
        return payedUp;
    }

    public void setPayedUp(boolean payedUp) {
        this.payedUp = payedUp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return id == that.id &&
                priority == that.priority &&
                amountOfPeople == that.amountOfPeople &&
                Float.compare(that.cost, cost) == 0 &&
                invoice == that.invoice &&
                payedUp == that.payedUp;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, priority, amountOfPeople, cost, invoice, payedUp);
    }

    @ManyToOne
    @JoinColumn(name = "cateringid", referencedColumnName = "id", nullable = false)
    public CateringsEntity getCateringsByCateringid() {
        return cateringsByCateringid;
    }

    public void setCateringsByCateringid(CateringsEntity cateringsByCateringid) {
        this.cateringsByCateringid = cateringsByCateringid;
    }

    @ManyToOne
    @JoinColumn(name = "clientid", referencedColumnName = "id", nullable = false)
    public ClientsEntity getClientsByClientid() {
        return clientsByClientid;
    }

    public void setClientsByClientid(ClientsEntity clientsByClientid) {
        this.clientsByClientid = clientsByClientid;
    }

    @ManyToOne
    @JoinColumn(name = "placeid", referencedColumnName = "id", nullable = false)
    public PlacesEntity getPlacesByPlaceid() {
        return placesByPlaceid;
    }

    public void setPlacesByPlaceid(PlacesEntity placesByPlaceid) {
        this.placesByPlaceid = placesByPlaceid;
    }

    @ManyToOne
    @JoinColumn(name = "dateid", referencedColumnName = "id", nullable = false)
    public DatesEntity getDatesByDateid() {
        return datesByDateid;
    }

    public void setDatesByDateid(DatesEntity datesByDateid) {
        this.datesByDateid = datesByDateid;
    }
}
