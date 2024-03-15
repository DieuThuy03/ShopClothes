package com.example.shopclothes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Payment")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "status")
    private String status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payments")
    List<PaymentsDetail> paymentsDetails;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orders;
}
