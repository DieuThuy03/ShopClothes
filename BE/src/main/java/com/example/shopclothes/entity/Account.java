package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(name = "userName")
    private String userName;

    @Column(name = "code")
    private String code;

    @Column(name = "ghi_chu")
    private String ghi_chu;

    @Column(name = "dateCreate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.util.Date dateCreate;

    @Column(name = "dateUpdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateUpdate;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    private Role idRole;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "idAcc")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAcc")
    List<Return> returns;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idAcc")
    List<CartDetail> cartDetails;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "idAcc")
    private Cart idCart;


}
