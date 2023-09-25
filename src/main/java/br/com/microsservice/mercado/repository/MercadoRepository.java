package br.com.microsservice.mercado.repository;

import br.com.microsservice.mercado.domain.entity.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MercadoRepository extends JpaRepository<Mercado, Long> {
}
