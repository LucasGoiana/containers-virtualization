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
public class UserCreateDTO {

    private String username;
    private String password;
    private Profile profile;
}
