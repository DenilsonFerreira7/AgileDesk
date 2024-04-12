package com.laudoStratus.demo.service;

import com.laudoStratus.demo.models.Chamado;
import com.laudoStratus.demo.models.DescricaoAtualizacao;
import com.laudoStratus.demo.repository.ChamadoRepository;
import com.laudoStratus.demo.repository.DescricaoAtualizacaoRepository;
import com.laudoStratus.demo.validacao.DescricaoAtualizacaoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DescricaoAtualizacaoService {

    private final DescricaoAtualizacaoRepository descricaoAtualizacaoRepository;
    private final DescricaoAtualizacaoValidator descricaoAtualizacaoValidator;

    private final ChamadoRepository chamadoRepository;


    public DescricaoAtualizacao criarDescricaoAtualizacao(DescricaoAtualizacao descricaoAtualizacao, Chamado chamado) {
        descricaoAtualizacaoValidator.validarAssociacaoChamado(descricaoAtualizacao, chamado);
        descricaoAtualizacao.setChamado(chamado);
        return descricaoAtualizacaoRepository.save(descricaoAtualizacao);
    }

    public List<DescricaoAtualizacao> listarTodasDescricoes() {
        return descricaoAtualizacaoRepository.findAll();
    }
    public  Chamado adicionarDescricao(Long idChamado, String novaDescricao, String atualizadoPor) {
        // Recupere o chamado a ser atualizado do banco de dados
        Chamado chamado = chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));

        // Crie uma nova descrição
        DescricaoAtualizacao novaDescricaoAtualizacao = new DescricaoAtualizacao();
        novaDescricaoAtualizacao.setDescricao(novaDescricao);
        novaDescricaoAtualizacao.setAtualizadoPor(atualizadoPor);
        novaDescricaoAtualizacao.setUpdateDateTime(new Date()); // Defina a data/hora atual

        // Associe a nova descrição ao chamado
        novaDescricaoAtualizacao.setChamado(chamado);

        // Salve a nova descrição no banco de dados
        descricaoAtualizacaoRepository.save(novaDescricaoAtualizacao);

        // Atualize a lista de descrições do chamado
        chamado.getDescriptionUpdates().add(novaDescricaoAtualizacao);

        // Atualize o chamado no banco de dados
        return chamadoRepository.save(chamado);
    }

}