package com.laudoStratus.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(String message) {
        super(message);

    }

    //EMPRESAS
    public static MessageNotFoundException EmpresaIdNull(Long empresa) {
        return new MessageNotFoundException("Empresa não pode ser nula");
    }
    public static MessageNotFoundException EmpresaNaoEncontrada (Long empresa) {
        return new MessageNotFoundException("Empresa com id " +empresa+ " já cadastrada");
    }
    public static MessageNotFoundException EmpresaIdNaoExiste (Long empresa) {
        return new MessageNotFoundException("Empresa com id " + empresa + " não existe");
    }
    public static MessageNotFoundException NomeEmpresaObrigatorio(String nomeEmpresa) {
        return new MessageNotFoundException("O nome da empresa é obrigatório.");
    }
    public static MessageNotFoundException NomeEmpresaNaoEncontrado (String nomeEmpresa) {
        return new MessageNotFoundException("O nome da empresa é obrigatório.");
    }

    public static MessageNotFoundException EmpresaNomeJaCadastrada(String nomeEmpresa) {
        return new MessageNotFoundException("Já existe uma empresa cadastrada com esse nome" +nomeEmpresa);
    }

   //TECNICO
    public static MessageNotFoundException TecnicoNaoEncontrado (Long tecnico) {
        return new MessageNotFoundException("O tecnico com ID " + tecnico + " não encontrado");
    }

    //EQUIPAMENTO
    public static MessageNotFoundException EquipamentoNaoEncontrado (Long equipamento) {
        return new MessageNotFoundException("O equipamento com ID " + equipamento + " não encontrado");
    }
    public static MessageNotFoundException LaudoIdNull(Long laudo) {
        return new MessageNotFoundException("O objeto de solicitação do laudo não pode ser nulo");
    }
}