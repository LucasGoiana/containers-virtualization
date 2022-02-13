package br.com.fiap.goecommerce.entity;

import br.com.fiap.goecommerce.enumerate.OrderStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    private Double totalAmmount;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(name = "status")
    private OrderStatus orderStatus;

    @ManyToOne()
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "order",cascade = CascadeType.PERSIST)
    private List<OrderProduct> orderProducts;

}
