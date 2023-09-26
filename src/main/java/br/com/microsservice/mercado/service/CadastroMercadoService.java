package br.com.microsservice.mercado.service;

import br.com.microsservice.mercado.controller.dto.MercadoDTO;
import br.com.microsservice.mercado.domain.entity.Mercado;
import br.com.microsservice.mercado.repository.MercadoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

@Service
public class CadastroMercadoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MercadoRepository mercadoRepository;

    public MercadoDTO criar(MercadoDTO mercadoDTO) {
        Mercado mercado = modelMapper.map(mercadoDTO, Mercado.class);
        mercado = mercadoRepository.save(mercado);
        return modelMapper.map(mercado, MercadoDTO.class);
    }

    public Page<MercadoDTO> listar(@PageableDefault(size = 15) Pageable paginacao) {
        return mercadoRepository
                .findAll(paginacao)
                .map(mercado -> modelMapper.map(mercado, MercadoDTO.class));
    }

    public MercadoDTO detalhar(Long id) {
        Mercado mercado = mercadoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(mercado, MercadoDTO.class);
    }

    public void substituir(Long id, MercadoDTO mercadoDTO) {
        mercadoDTO.setId(id);
        Mercado mercado = modelMapper.map(mercadoDTO, Mercado.class);
        mercadoRepository.save(mercado);
    }

    public void modificar(Long id, MercadoDTO mercadoDTO) {
        Mercado mercado = mercadoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Mercado mercadoToBe = modelMapper.map(mercadoDTO, Mercado.class);
        mercado.mergeNonNullProperties(mercadoToBe);
        mercadoRepository.save(mercado);
    }
}
