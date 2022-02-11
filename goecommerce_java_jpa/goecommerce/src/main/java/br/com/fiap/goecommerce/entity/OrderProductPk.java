package br.com.fiap.goecommerce.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@EqualsAndHashCode
@Getter
@Setter
public class OrderProductPk implements Serializable {
    private Long order;
    private Long product;

}
