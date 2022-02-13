package br.com.fiap.goecommerce.entity;

import br.com.fiap.goecommerce.enumerate.Profile;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    private String username;
    private String password;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(columnDefinition = "smallint")
    private Profile profile;


}
