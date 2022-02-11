package br.com.fiap.goecommerce.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class AddressCreateDTO {

    private String cep;
    private String street;
    private Integer number;
    private String city;
    private String uf;
    private String complement;

}
