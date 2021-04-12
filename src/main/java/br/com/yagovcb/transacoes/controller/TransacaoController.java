package br.com.yagovcb.transacoes.controller;

import br.com.yagovcb.transacoes.services.TransacaoService;
import br.com.yagovcb.transacoes.services.dto.TransacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yago Castelo Branco
 * @since 12/04/2021
 * */
@RestController
@RequestMapping("/api")
public class TransacaoController {

    @Autowired
    private TransacaoService service;


    /**
     * {@code GET  /{id}/transacoes/{ano}/{mes}} : Returna uma List<TransacaoDTO>
     *
     * @param id Id do Usuario
     * @param ano da transação
     * @param mes em formato unitario, de 1 to 12
     * @return o {@link ResponseEntity} com status {@code 201 (Criado)} e com corpo o novo TransacaoDTO, ou com status {@code 400 (Bad Request)} se o TransacaoDTO já tiver um ID
     */
    @Cacheable(cacheNames = "getTransacoes")
    @GetMapping("/{id}/transacoes/{ano}/{mes}")
    public ResponseEntity<List<TransacaoDTO>> getTransacoes(@PathVariable int id, @PathVariable int ano, @PathVariable int mes ) {
        return new ResponseEntity<>(service.retornaListaTransacoes(id, ano, mes), HttpStatus.OK);
    }
}
