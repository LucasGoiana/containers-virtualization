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
public class ClientResponseCreateOrderDTO {
    private Long id;
    private String nome;
    private String cpf;
}
