package br.com.microsservice.mercadoms.service;

import br.com.microsservice.mercadoms.domain.entity.Mercado;
import br.com.microsservice.mercadoms.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class ControleAtivacaoMercadoService {

    @Autowired
    private MercadoRepository mercadoRepository;

    public void desativar(Long idFilial) {
        var mercado = mercadoRepository
                .findByFilialId(idFilial)
                .orElseThrow(EntityNotFoundException::new);

        if(!CollectionUtils.isEmpty(mercado.getFiliaisAtivas())) {
            return;
        }

        mercado.setIsAtivo(false);

        mercadoRepository.save(mercado);
    }

    public void ativar(Long idFilial) {
        var mercado = mercadoRepository
                .findByFilialId(idFilial)
                .orElseThrow(EntityNotFoundException::new);

        if(CollectionUtils.isEmpty(mercado.getFiliaisAtivas())) {
            return;
        }

        mercado.setIsAtivo(true);

        mercadoRepository.save(mercado);
    }

    public Boolean isMercadoAtivo(Long idFilial) {
        Mercado mercado = mercadoRepository
                .findByFilialId(idFilial)
                .orElseThrow(EntityNotFoundException::new);

        return mercado.getIsAtivo();
    }
}
