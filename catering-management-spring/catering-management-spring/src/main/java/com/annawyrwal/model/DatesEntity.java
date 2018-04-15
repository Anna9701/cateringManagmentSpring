package com.annawyrwal.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "dates", schema = "public", catalog = "catering")
public class DatesEntity {
    private int id;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date startTime = new Date();
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Date endTime = new Date();

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatesEntity that = (DatesEntity) o;
        return id == that.id &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, startTime, endTime);
    }
}
