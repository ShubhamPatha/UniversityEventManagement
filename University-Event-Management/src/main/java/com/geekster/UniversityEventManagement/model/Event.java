package com.geekster.UniversityEventManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Event")
public class Event {

    @Id
    private Integer EventId;
    private String EventName;
    private String EventLocation;
    private LocalDate EventDate;//EX : 2007-12-03.
    private String EventStartTime;
    private String EventEndTime;


}

