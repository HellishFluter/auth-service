package ru.weber.auth.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "public_key")
    private String publicKey;

    @Column(name = "product_id")
    private Long productId;
}
