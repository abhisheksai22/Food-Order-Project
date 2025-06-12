package com.infy.user.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.infy.user.entity.constant.AddressType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String addressName;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    private String city;

    @ManyToOne
    @JoinColumn(name = "user_detail_id")
    @JsonBackReference
    private UserDetail userDetail;

}