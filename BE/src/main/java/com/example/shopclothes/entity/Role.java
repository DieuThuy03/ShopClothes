package com.example.shopclothes.entity;

import com.example.shopclothes.common.ComonEnum;
import com.example.shopclothes.entity.propertis.Propertis;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Role")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    private String name;

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
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Account> accountList;

    public Role(String name) {
        this.name = name;
    }
}

