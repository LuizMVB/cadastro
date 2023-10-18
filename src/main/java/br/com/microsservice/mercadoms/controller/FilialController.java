package br.com.microsservice.mercadoms.controller;

import br.com.microsservice.mercadoms.dto.FilialDTO;
import br.com.microsservice.mercadoms.service.CadastroFilialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class FilialController {

    @Autowired
    private CadastroFilialService cadastroFilialService;

    @PostMapping(value = "/mercado/{id}/filial")
    @ResponseStatus(HttpStatus.CREATED)
    public FilialDTO criar(@PathVariable @NotNull Long id,
                      @RequestBody @Valid FilialDTO filialDTO) {
        return cadastroFilialService.criar(id, filialDTO);
    }

    @GetMapping(value = "/mercado/{id}/filial")
    public Page<FilialDTO> listar(@PathVariable @NotNull Long id,
                                  @PageableDefault(size = 5) Pageable paginacao) {
        return cadastroFilialService.listar(id, paginacao);
    }

    @GetMapping(value = "/mercado/filial/{id}")
    public FilialDTO detalhar(@PathVariable @NotNull Long id) {
        return cadastroFilialService.detalhar(id);
    }

    @PutMapping(value = "/mercado/filial/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void substituir(@PathVariable @NotNull Long id,
                           @RequestBody @Valid FilialDTO filialDTO) {
        cadastroFilialService.substituir(id, filialDTO);
    }

    @PatchMapping(value = "/mercado/filial/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void modificar(@PathVariable @NotNull Long id,
                          @RequestBody FilialDTO filialDTO) {
        cadastroFilialService.modificar(id, filialDTO);
    }

}
