package br.com.microsservice.mercadoms.service;

import br.com.microsservice.mercadoms.dto.FilialDTO;
import br.com.microsservice.mercadoms.domain.entity.Filial;
import br.com.microsservice.mercadoms.domain.entity.Mercado;
import br.com.microsservice.mercadoms.repository.FilialRepository;
import br.com.microsservice.mercadoms.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CadastroFilialService {

    @Autowired
    private MercadoRepository mercadoRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public FilialDTO criar(Long idMercado, FilialDTO filialDTO) {
        Mercado mercado = mercadoRepository
                .findById(idMercado)
                .orElseThrow(EntityNotFoundException::new);
        Filial filial = modelMapper.map(filialDTO, Filial.class);
        filial.setMercado(mercado);
        Filial filialPersistida = filialRepository.save(filial);
        return modelMapper.map(filialPersistida, FilialDTO.class);
    }

    public Page<FilialDTO> listar(Long idMercado, Pageable paginacao) {
        return filialRepository
                .findByIdMercado(idMercado, paginacao)
                .map(filial -> modelMapper.map(filial, FilialDTO.class));
    }

    public FilialDTO detalhar(Long id) {
        Filial filial = filialRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(filial, FilialDTO.class);
    }

    @Transactional
    public void substituir(Long id, FilialDTO filialDTO) {
        filialDTO.setId(id);
        Filial filial = modelMapper.map(filialDTO, Filial.class);
        filialRepository.save(filial);
    }

    @Transactional
    public void modificar(Long id, FilialDTO filialDTO) {
        Filial filial = filialRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Filial filialToBe = modelMapper.map(filialDTO, Filial.class);
        filial.mergeNonNullProperties(filialToBe);
        filialRepository.save(filial);
    }

}
