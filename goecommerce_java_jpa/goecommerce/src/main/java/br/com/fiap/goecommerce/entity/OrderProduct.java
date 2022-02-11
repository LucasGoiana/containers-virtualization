package br.com.fiap.goecommerce.entity;

import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_order_has_product")
@IdClass(OrderProductPk.class)
public class OrderProduct {

    @Id()
    @ManyToOne()
    @JoinColumn(name = "order_id",referencedColumnName = "id_order")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id_product")
    private Product product;

    @JoinColumn(name = "quantity")
    private Integer quantity;

    @UpdateTimestamp
    @JoinColumn(name = "modifiedDate")
    private LocalDateTime modifiedDate;



}
