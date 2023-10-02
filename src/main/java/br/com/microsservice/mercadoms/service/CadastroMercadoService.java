package br.com.microsservice.mercadoms.service;

import br.com.microsservice.mercadoms.dto.MercadoDTO;
import br.com.microsservice.mercadoms.domain.entity.Mercado;
import br.com.microsservice.mercadoms.repository.MercadoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
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

    @Transactional
    public void substituir(Long id, MercadoDTO mercadoDTO) {
        mercadoDTO.setId(id);
        Mercado mercado = modelMapper.map(mercadoDTO, Mercado.class);
        mercado.atualizarFilialList();
        mercado.validarSePodePersistirAtualizacao();
        mercadoRepository.save(mercado);
    }

    @Transactional
    public void modificar(Long id, MercadoDTO mercadoDTO) {
        Mercado mercado = mercadoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        Mercado mercadoToBe = modelMapper.map(mercadoDTO, Mercado.class);
        mercado.modificar(mercadoToBe);
        mercado.atualizarFilialList();
        mercadoRepository.save(mercado);
    }
}
