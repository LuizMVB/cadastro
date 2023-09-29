package br.com.microsservice.mercadoms.repository;

import br.com.microsservice.mercadoms.domain.entity.Filial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilialRepository extends JpaRepository<Filial, Long> {

    @Query("SELECT f FROM Filial f where f.mercado.id = :idMercado")
    Page<Filial> findByIdMercado(@Param("idMercado") Long idMercado, Pageable paginacao);

}
