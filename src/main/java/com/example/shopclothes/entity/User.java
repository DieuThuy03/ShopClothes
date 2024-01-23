package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "User")

public class User extends Propertis {

    private boolean sex;

    private String phoneNumber;

    private String email;

    private Date birthday;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAcc")
    private Account idAcc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    List<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idUser")
    List<Bill> bills;

}
