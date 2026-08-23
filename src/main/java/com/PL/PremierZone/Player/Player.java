package com.PL.PremierZone.Player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//this indicates class is JPA entity

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Players")
@Data
public class Player {
    @Id
    @Column(name="player",unique = true)
    private String name;
    private String nation;
    private String pos;
    private Integer age;
    private Integer mp;
    private Integer starts;
    private Double min;
    private Double gls;
    private Double ast;
    private Double pk;
    private Double crdy;
    private Double crdr;
    private Double xg;
    private String Team;

    public Player(String name) {
        this.name = name;
    }

}
