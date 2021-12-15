package net.atos.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeletaClienteRepository extends JpaRepository<ClienteRepository, Long> {

    Optional<Object> findByIdCliente(Long idCliente);
}
