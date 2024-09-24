package com.subria.fi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class CustomerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactPersonId;

    @Column( nullable = false)
    private String name;

    private String address;

    @Column( unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    private String companyName;

    @OneToMany(mappedBy = "customerInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryOrder> inventoryOrders;

}
