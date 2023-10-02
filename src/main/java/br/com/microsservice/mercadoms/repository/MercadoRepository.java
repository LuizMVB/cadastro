package br.com.microsservice.mercadoms.repository;

import br.com.microsservice.mercadoms.domain.entity.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MercadoRepository extends JpaRepository<Mercado, Long> {

    @Query("SELECT m FROM Mercado m " +
            "INNER JOIN m.filialList f " +
            "WHERE f.id = :id")
    Optional<Mercado> findByFilialId(@Param("id") Long id);

}
