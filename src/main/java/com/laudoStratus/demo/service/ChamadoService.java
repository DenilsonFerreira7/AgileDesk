package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.ChamadoDTO;
import com.laudoStratus.demo.models.*;
import com.laudoStratus.demo.repository.ChamadoRepository;
import com.laudoStratus.demo.repository.DescricaoAtualizacaoRepository;
import com.laudoStratus.demo.validacao.ChamadoCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ChamadoService {

    private final ChamadoCreationService chamadoCreationService;
    private final DescricaoAtualizacaoRepository descricaoAtualizacaoRepository;
    private final ChamadoRepository chamadoRepository;

    public Chamado criarChamado(ChamadoDTO chamadoDTO) {
        if (chamadoDTO.getTitulo() == null || chamadoDTO.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título do chamado é obrigatório");
        }

        if (chamadoDTO.getDescricao() == null || chamadoDTO.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição do chamado é obrigatória");
        }

        // Criar o chamado
        Chamado novoChamado = new Chamado();
        novoChamado.setTitulo(chamadoDTO.getTitulo());
        novoChamado.setStatus("Aberto");
        novoChamado.setOpeningDateTime(new Date());
        novoChamado.setEmpresa(chamadoDTO.getEmpresa());
        novoChamado.setTecnico(chamadoDTO.getTecnico());
        novoChamado.setUsuario(chamadoDTO.getUsuario());

        novoChamado = chamadoRepository.save(novoChamado);

        // Criar a descrição
        DescricaoAtualizacao primeiraDescricao = new DescricaoAtualizacao();
        primeiraDescricao.setDescricao(chamadoDTO.getDescricao());
        primeiraDescricao.setAtualizadoPor("Sistema");
        primeiraDescricao.setUpdateDateTime(new Date());
        primeiraDescricao.setChamado(novoChamado);

        descricaoAtualizacaoRepository.save(primeiraDescricao);

        return novoChamado;
    }

    public Chamado encerrarChamado(Long id) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));

        chamado.setStatus("Fechado");
        chamado.setClosingDateTime(new Date());

        return chamadoRepository.save(chamado);
    }

    public Chamado atualizarChamado(Long id, Chamado chamadoAtualizado) {
        Chamado chamado = chamadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));

        chamado.setTitulo(chamadoAtualizado.getTitulo());
        chamado.setStatus(chamadoAtualizado.getStatus());
        return chamadoRepository.save(chamado);
    }

    public Chamado adicionarDescricao(Long idChamado, DescricaoAtualizacao novaDescricao) {
        // Obter o chamado a ser atualizado
        Chamado chamado = chamadoRepository.findById(idChamado)
                .orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));

        // Configurar a data/hora atual na nova descrição
        novaDescricao.setUpdateDateTime(new Date());

        // Associar a nova descrição ao chamado
        novaDescricao.setChamado(chamado);

        // Salvar a nova descrição
        descricaoAtualizacaoRepository.save(novaDescricao);

        // Adicionar a nova descrição à lista de descrições do chamado
        chamado.getDescriptionUpdates().add(novaDescricao);

        // Atualizar o chamado no banco de dados
        return chamadoRepository.save(chamado);
    }

}