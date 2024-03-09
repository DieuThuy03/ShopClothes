package com.example.shopclothes.entity;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.propertis.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "VocherDetail")

public class VocherDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_uses")
    private Integer numberOfUses;

    @CreationTimestamp
//    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "dateCreate",  updatable = false)
    private LocalDateTime dateCreate;

    @CreationTimestamp
//    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "dateUpdate", updatable = false)
    private LocalDateTime dateUpdate;


    @Column(name = "status")
    private ComonEnum.TrangThaiVoucherChiTiet status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVor")
    private Vocher vocher;


    @ManyToOne
    @JoinColumn(name = "idAcc", referencedColumnName = "id")
    private Account account;

}
