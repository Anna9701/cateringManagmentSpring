package com.annawyrwal.model;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders", schema = "public", catalog = "catering")
public class OrdersEntity {
    private int id;
    private int amountOfPeople;
    private double cost = 0;
    private boolean invoice = true;
    private boolean payedUp = false;
    private CateringsEntity cateringsByCateringid;
    private ClientsEntity clientsByClientid;
    private PlacesEntity placesByPlaceid;
    private DatesEntity datesByDateid;

    @Transient
    private int cateringId;

    @Transient
    private int clientId;

    @Transient
    private int placeId;

    @Transient
    private int dateId;

    public OrdersEntity() {
    }

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
    @Column(name = "amount_of_people")
    public int getAmountOfPeople() {
        return amountOfPeople;
    }

    public void setAmountOfPeople(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople;
    }

    @Basic
    @Column(name = "cost", nullable = false)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "invoice", nullable = false)
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
                amountOfPeople == that.amountOfPeople &&
                Double.compare(that.cost, cost) == 0 &&
                invoice == that.invoice &&
                payedUp == that.payedUp;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, amountOfPeople, cost, invoice, payedUp);
    }

    @OneToOne
    @JoinColumn(name = "cateringid", referencedColumnName = "id", nullable = false)
    public CateringsEntity getCateringsByCateringid() {
        return cateringsByCateringid;
    }

    public void setCateringsByCateringid(CateringsEntity cateringsByCateringid) {
        this.cateringsByCateringid = cateringsByCateringid;
    }

    @OneToOne
    @JoinColumn(name = "clientid", referencedColumnName = "id", nullable = false)
    public ClientsEntity getClientsByClientid() {
        return clientsByClientid;
    }

    public void setClientsByClientid(ClientsEntity clientsByClientid) {
        this.clientsByClientid = clientsByClientid;
    }

    @OneToOne
    @JoinColumn(name = "placeid", referencedColumnName = "id", nullable = true)
    public PlacesEntity getPlacesByPlaceid() {
        return placesByPlaceid;
    }

    public void setPlacesByPlaceid(PlacesEntity placesByPlaceid) {
        this.placesByPlaceid = placesByPlaceid;
    }

    @OneToOne
    @JoinColumn(name = "dateid", referencedColumnName = "id", nullable = true)
    public DatesEntity getDatesByDateid() {
        return datesByDateid;
    }

    public void setDatesByDateid(DatesEntity datesByDateid) {
        this.datesByDateid = datesByDateid;
    }

    @Transient
    public int getCateringId() {
        return cateringId;
    }

    @Transient
    public void setCateringId(int cateringId) {
        this.cateringId = cateringId;
    }

    @Transient
    public int getClientId() {
        return clientId;
    }

    @Transient
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Transient
    public int getPlaceId() {
        return placeId;
    }

    @Transient
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    @Transient
    public int getDateId() {
        return dateId;
    }

    @Transient
    public void setDateId(int dateId) {
        this.dateId = dateId;
    }
}
