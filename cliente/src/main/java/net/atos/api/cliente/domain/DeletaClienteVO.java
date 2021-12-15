package net.atos.api.cliente.domain;

import java.util.Objects;
import javax.validation.constraints.NotNull;

public class DeletaClienteVO {

    @NotNull(message = "Campo id não pode ser null")
    private Long idCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DeletaClienteVO other = (DeletaClienteVO) obj;
        return Objects.equals(idCliente, other.idCliente);
    }
}
