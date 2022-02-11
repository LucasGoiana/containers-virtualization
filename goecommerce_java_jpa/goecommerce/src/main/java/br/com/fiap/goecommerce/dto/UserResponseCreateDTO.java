package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.enumerate.Profile;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserResponseCreateDTO {
    private String username;
    private Profile profile;
}
