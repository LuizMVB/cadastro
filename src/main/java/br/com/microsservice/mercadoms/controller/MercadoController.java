package br.com.microsservice.mercadoms.controller;

import br.com.microsservice.mercadoms.dto.MercadoDTO;
import br.com.microsservice.mercadoms.service.CadastroMercadoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MercadoController {

    @Autowired
    private CadastroMercadoService cadastroMercadoService;

    @PostMapping(value = "/mercado")
    @ResponseStatus(HttpStatus.CREATED)
    public MercadoDTO criar(@RequestBody @Valid MercadoDTO mercadoDTO) {
        return cadastroMercadoService.criar(mercadoDTO);
    }

    @GetMapping(value = "/mercado")
    public Page<MercadoDTO> listar(@PageableDefault(size = 15) Pageable paginacao) {
        return cadastroMercadoService.listar(paginacao);
    }

    @GetMapping(value = "/mercado/{id}")
    public MercadoDTO detalhar(@PathVariable @NotNull Long id) {
        return cadastroMercadoService.detalhar(id);
    }

    @PutMapping(value = "/mercado/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void substituir(@PathVariable @NotNull Long id,
                           @RequestBody @Valid MercadoDTO mercadoDTO) {
        cadastroMercadoService.substituir(id, mercadoDTO);
    }

    @PatchMapping(value = "/mercado/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modificar(@PathVariable @NotNull Long id,
                          @RequestBody MercadoDTO mercadoDTO) {
        cadastroMercadoService.modificar(id, mercadoDTO);
    }

}
