package com.laudoStratus.demo.controller;

import com.laudoStratus.demo.DTO.ChamadoDTO;
import com.laudoStratus.demo.DTO.DescricaoAtualizacaoDTO;
import com.laudoStratus.demo.models.Chamado;
import com.laudoStratus.demo.models.DescricaoAtualizacao;
import com.laudoStratus.demo.service.ChamadoService;
import com.laudoStratus.demo.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chamados")
@RequiredArgsConstructor
public class ChamadoController {

    private final ChamadoService chamadoService;


    @PostMapping("/criar")
    public ResponseEntity<ResponseMessage> criarChamado(@RequestBody ChamadoDTO chamadoDTO) {
        Chamado novoChamado = chamadoService.criarChamado(chamadoDTO);
        ResponseMessage responseMessage = new ResponseMessage("Chamado Criado com sucesso protocolo: "+novoChamado.getIdChamado());
        return new ResponseEntity<>(responseMessage,HttpStatus.CREATED);
    }
    @PutMapping("/encerrar/{id}")
    public ResponseEntity<Chamado> encerrarChamado(@PathVariable Long id) {
        Chamado chamado = chamadoService.encerrarChamado(id);
        return ResponseEntity.ok(chamado);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Chamado> atualizarChamado(@PathVariable Long id, @RequestBody Chamado chamadoAtualizado) {
        Chamado chamado = chamadoService.atualizarChamado(id, chamadoAtualizado);
        return ResponseEntity.ok(chamado);
    }

    @PostMapping("/{id}/descricao")
    public ResponseEntity<Chamado> adicionarDescricao(@PathVariable("id") Long idChamado,
                                                      @Valid @RequestBody DescricaoAtualizacaoDTO descricaoAtualizacaoDTO) {
        // Converter DTO para entidade DescricaoAtualizacao
        DescricaoAtualizacao novaDescricao = new DescricaoAtualizacao();
        novaDescricao.setDescricao(descricaoAtualizacaoDTO.getDescricao());
        novaDescricao.setAtualizadoPor(descricaoAtualizacaoDTO.getAtualizadoPor());

        // Adicionar a nova descrição ao chamado
        Chamado chamadoAtualizado = chamadoService.adicionarDescricao(idChamado, novaDescricao);

        return ResponseEntity.ok(chamadoAtualizado);
    }

}