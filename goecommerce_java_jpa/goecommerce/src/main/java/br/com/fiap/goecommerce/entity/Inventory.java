package br.com.fiap.goecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tb_inventory")
public class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_inventory")
    private Long id;
    private Integer quantity;

    @ManyToMany(mappedBy = "inventories")
    private List<Product> product;



}
