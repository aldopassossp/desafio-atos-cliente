package net.atos.api.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.atos.api.cliente.repository.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

//    public Optional<List<ClienteEntity>> findByNome(String nome);
    
 //   public Optional<ClienteEntity> findByIdCliente(Long idCliente);


}