package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ProductDetail")

public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "dateCreate")
    private Date dateCreate;

    @Column(name = "dateUpdate")
    private Date dateUpdate;

    @Column(name = "status")
    private Status status;

    @Column(name = "peopleUpdate")
    private String peopleUpdate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idCtsp")
    List<Imege> imeges;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProduct")
    private Product idProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCate")
    private Category idCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCol")
    private Color idColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMate")
    private Material idMaterial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducer")
    private Producer idProducer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSize")
    private Size idSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcartDetail")
    private CartDetail idCartDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idCtsp")
    List<BillDetail> billDetails;
}
