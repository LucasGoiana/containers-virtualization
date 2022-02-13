package br.com.fiap.goecommerce.dto;

import br.com.fiap.goecommerce.entity.Address;
import br.com.fiap.goecommerce.entity.Order;
import br.com.fiap.goecommerce.entity.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String nome;
    private String cpf;
    private String phoneNumber;
    private UserDTO user;
    private List<AddressDTO> addresses;

}
