package com.example.shopclothes.entity;

import com.example.shopclothes.common.ComonEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "citizen_identification_card")
    private String citizenIdentificationCard;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private ComonEnum.GioiTinh sex;

    @Column(name = "phoneNumber",unique = true)
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district; // tinh/huyen

    @Column(name = "wards")
    private String wards; // phuong/ xa

    @Column(name = "specific_address")
    private String specificAddress;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "password")
    private String password;

    //@CreationTimestamp
    @Column(name = "date_create")
    private Date dateCreate;

    //@UpdateTimestamp
    @Column(name = "date_update")
    private Date dateUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ComonEnum.TrangThaiThuocTinh status;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> listAddress;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bill> listBill;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    List<Cart> listCart;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payments> listPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRole")
    private Role idRole;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    List<Return> returns;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VocherDetail> vocherDetail;

    @ManyToOne
    @JoinColumn(name = "idRole", referencedColumnName = "id", insertable=false, updatable=false)
    private Role role;

    public Account(String fullName, String email, String password, Role role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
