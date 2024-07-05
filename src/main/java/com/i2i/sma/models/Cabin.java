package com.i2i.sma.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

/**
 * <p>
 * This class is responsible for maintaining methods to get and set the attributes such as teacher details, cabin id and laptop id.
 * These attributes can be accessed throughout the application.
 * </p>
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cabins")
public class Cabin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "laptop_id", nullable = false)
    private int laptopId;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}