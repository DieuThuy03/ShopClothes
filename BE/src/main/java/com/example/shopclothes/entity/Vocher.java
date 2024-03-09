package com.example.shopclothes.entity;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.propertis.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Vocher")
public class Vocher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @UpdateTimestamp
    @Column(name = "dateCreate")
    private LocalDateTime dateCreate;

    @UpdateTimestamp
    @Column(name = "dateUpdate")
    private LocalDateTime dateUpdate;

    @Column(name = "peplerCreate")
    private String peplerCreate;

    @Column(name = "peopleUpdate")
    private String peopleUpdate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ComonEnum.TrangThaiVoucher status;

    @Column(name = "describe")
    private String describe;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "endTime")
    private LocalDateTime endTime;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "reducedValue") //Giá trị giảm.
    private BigDecimal reducedValue;

    @Column(name = "Minimize") //Giảm tối đa.
    private BigDecimal minimize;

    @Column(name = "minimumOrder") //Đơn tối thiểu
    private BigDecimal minimumOrder;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vocher", cascade = CascadeType.ALL)
    List<VocherDetail> vocherDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBill")
    private Bill idBill;

//    @ManyToOne
//    @JoinColumn(name = "formOfDiscount", referencedColumnName = "id")
//    private FormOfDiscount formOfDiscount;

}
