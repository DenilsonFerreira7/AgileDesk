package com.laudoStratus.demo.validacao;

import com.laudoStratus.demo.models.Chamado;
import com.laudoStratus.demo.models.DescricaoAtualizacao;
import com.laudoStratus.demo.models.Empresa;
import com.laudoStratus.demo.models.Tecnico;
import com.laudoStratus.demo.models.Usuario;
import com.laudoStratus.demo.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@RequiredArgsConstructor
@Service
public class ChamadoCreationService {

    private final ChamadoRepository chamadoRepository;

    public Chamado criarChamado(String titulo, String primeiraDescricao, String status, Empresa empresa, Tecnico tecnico, Usuario usuario) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("O título do chamado é obrigatório");
        }

        if (primeiraDescricao == null || primeiraDescricao.isEmpty()) {
            throw new IllegalArgumentException("A primeira descrição do chamado é obrigatória");
        }

        Chamado novoChamado = new Chamado();
        novoChamado.setTitulo(titulo);
        novoChamado.setStatus(status != null ? status : "Aberto");
        novoChamado.setOpeningDateTime(new Date());
        novoChamado.setEmpresa(empresa);
        novoChamado.setTecnico(tecnico);
        novoChamado.setUsuario(usuario);

        // Cria a descrição e a associa ao chamado
        DescricaoAtualizacao primeiraDescAtualizacao = new DescricaoAtualizacao();
        primeiraDescAtualizacao.setDescricao(primeiraDescricao);
        primeiraDescAtualizacao.setAtualizadoPor("Sistema");
        primeiraDescAtualizacao.setUpdateDateTime(new Date());
        primeiraDescAtualizacao.setChamado(novoChamado);

        // Adiciona a descrição à lista de atualizações do chamado
        novoChamado.getDescriptionUpdates().add(primeiraDescAtualizacao);

        // Salva o chamado e retorna
        return chamadoRepository.save(novoChamado);
    }
}