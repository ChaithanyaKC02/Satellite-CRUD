package com.demo.satellite.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Satellite_Events")
public class SatelliteEvent
{
    @Id
    @GeneratedValue
    @Column(name ="Seq_No")
    private int seqNo;

    @Column(name ="Satellite_Name")
    private String satelliteName;

    @Column(name ="Satellite_Date")
    private LocalDateTime satelliteDate;

    @Column(name ="Description")
    private String description;

    @Column(name ="Priority")
    private String priority;

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seq_no) {
        this.seqNo = seq_no;
    }

    public String getSatelliteName() {
        return satelliteName;
    }

    public void setSatelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
    }

    public LocalDateTime getSatelliteDate() {
        return satelliteDate;
    }

    public void setSatelliteDate(LocalDateTime satellite_Date) {
        this.satelliteDate = satellite_Date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
