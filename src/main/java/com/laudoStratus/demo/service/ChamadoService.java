package com.laudoStratus.demo.service;

import com.laudoStratus.demo.DTO.ChamadoDTO;
import com.laudoStratus.demo.models.*;
import com.laudoStratus.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class ChamadoService {

    private final DescricaoAtualizacaoRepository descricaoAtualizacaoRepository;
    private final ChamadoRepository chamadoRepository;
    private final EmpresaRepository empresaRepository;
    private final TecnicoRepository tecnicoRepository;
    private final UsuarioRepository usuarioRepository;

    public Chamado criarChamado(ChamadoDTO chamadoDTO) {
        if (chamadoDTO.getTitulo() == null || chamadoDTO.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("O título do chamado é obrigatório");
        }

        if (chamadoDTO.getDescricao() == null || chamadoDTO.getDescricao().isEmpty()) {
            throw new IllegalArgumentException("A descrição do chamado é obrigatória");
        }

        // Buscar as entidades associadas pelo ID
        Empresa empresa = empresaRepository.findById(chamadoDTO.getIdEmpresa())
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));
        Tecnico tecnico = tecnicoRepository.findById(chamadoDTO.getIdTecnico())
                .orElseThrow(() -> new IllegalArgumentException("Técnico não encontrado"));
        Usuario usuario = usuarioRepository.findById(chamadoDTO.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        // Criar o chamado
        Chamado novoChamado = new Chamado();
        novoChamado.setTitulo(chamadoDTO.getTitulo());
        novoChamado.setStatus("Aberto");
        novoChamado.setOpeningDateTime(new Date());
        novoChamado.setEmpresa(empresa);
        novoChamado.setTecnico(tecnico);
        novoChamado.setUsuario(usuario);

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