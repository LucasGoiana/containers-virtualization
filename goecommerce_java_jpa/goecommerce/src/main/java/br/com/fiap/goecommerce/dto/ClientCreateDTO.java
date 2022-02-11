package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ClientCreateDTO {

    private String nome;
    private String cpf;
    private String phoneNumber;
    private UserCreateDTO user;
    private List<AddressCreateDTO> addresses;


}
