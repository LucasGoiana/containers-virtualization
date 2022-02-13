package br.com.fiap.goecommerce.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ClientUpdateDTO {
    private String nome;
    private String phoneNumber;
    private UserUpdateDTO user;
    private List<AddressCreateDTO> addresses;
}
