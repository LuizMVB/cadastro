package br.com.microsservice.mercadoms.repository;

import br.com.microsservice.mercadoms.domain.entity.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MercadoRepository extends JpaRepository<Mercado, Long> {
}
