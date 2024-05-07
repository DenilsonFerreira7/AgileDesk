package com.laudoStratus.demo.validacao.chamadosVal;

import com.laudoStratus.demo.models.DescricaoAtualizacao;
import com.laudoStratus.demo.models.Chamado;
import org.springframework.stereotype.Component;

@Component
public class descricaoAtualizacaoValidator {

    public void validarAssociacaoChamado(DescricaoAtualizacao descricaoAtualizacao, Chamado chamado) {
        if (chamado == null) {
            throw new IllegalArgumentException("A descrição deve estar associada a um chamado.");
        }
    }
}