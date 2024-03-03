package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import com.example.shopclothes.entity.propertis.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Product")
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

//    @Column(name = "dateUpdate")
//    private Date dateUpdate;
//
//    @Column(name = "dateCreate")
//    private Date dateCreate;

    @Column(name = "dateCreate")
    private LocalDateTime dateCreate;

    @Column(name = "dateUpdate")
    private LocalDateTime dateUpdate;

    @Column(name = "discribe")
    private String discribe;

//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "idCategory")
//    @JoinColumn(name = "id_cate")
//    @JsonProperty("idCategory")
//    private Category idCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cate")
    @JsonProperty("idCategory")
    private Category idCategory;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "idProducer")
//    @JsonProperty("idProducer")
//    private Producer idProducer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idProducer")
    @JsonProperty("idProducer")
    private Producer idProducer;


//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProduct")
//    List<ProductDetail> productDetails;
}
