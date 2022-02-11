package br.com.fiap.goecommerce.enumerate;

public enum Profile {

    Client(1),
    Employee(2);

    private Integer cod;

    Profile(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }
}
