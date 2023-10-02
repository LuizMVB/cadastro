package br.com.microsservice.mercadoms.service;

import br.com.microsservice.mercadoms.domain.entity.Filial;
import br.com.microsservice.mercadoms.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ControleAtivacaoFilialService {

    @Autowired
    private FilialRepository filialRepository;

    public void desativar(Long idMercado) {
        List<Filial> filiaisAtivas = filialRepository.findByIdMercadoAndIsAtivoTrue(idMercado);

        if(CollectionUtils.isEmpty(filiaisAtivas)) {
            return;
        }

        filiaisAtivas.forEach(Filial::desativar);

        filialRepository.saveAll(filiaisAtivas);
    }
}
