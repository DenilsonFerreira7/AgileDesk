package com.laudoStratus.demo.validacao;

import com.laudoStratus.demo.models.DescricaoAtualizacao;
import com.laudoStratus.demo.models.Chamado;
import org.springframework.stereotype.Component;

@Component
public class DescricaoAtualizacaoValidator {

    public void validarAssociacaoChamado(DescricaoAtualizacao descricaoAtualizacao, Chamado chamado) {
        if (chamado == null) {
            throw new IllegalArgumentException("A descrição deve estar associada a um chamado.");
        }
    }
}