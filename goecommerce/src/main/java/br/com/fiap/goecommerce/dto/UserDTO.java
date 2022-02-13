package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.enumerate.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private Profile profile;
}
