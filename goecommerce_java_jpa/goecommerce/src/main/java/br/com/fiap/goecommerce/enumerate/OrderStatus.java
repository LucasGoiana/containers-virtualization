package br.com.fiap.goecommerce.enumerate;

public enum OrderStatus {
    CANCELED(1),
    PENDENT(2),
    SEPARATING(3),
    BILLED(4);


    private Integer cod;

    OrderStatus(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }
}
