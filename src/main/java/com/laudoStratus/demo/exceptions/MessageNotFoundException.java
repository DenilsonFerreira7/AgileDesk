package com.laudoStratus.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageNotFoundException extends RuntimeException {

    public MessageNotFoundException(String message) {
        super(message);

    }

    public static MessageNotFoundException NotFoundUserId(Long id) {
        return new MessageNotFoundException("Usuario com id " + id + " Não encontrado");
    }

    public static MessageNotFoundException NotFoundCardNumber(String cardNumber) {
        return new MessageNotFoundException("Cartão com número " + cardNumber + " Não encontrado");
    }

    public static MessageNotFoundException NotFoundCardPassword(String cardNumber) {
        return new MessageNotFoundException("Senha do cartão " + cardNumber + " invalido");
    }

    public static MessageNotFoundException NotFounIdBalance(Long id) {
        return new MessageNotFoundException("id de referencia " + id + " do saldo (balance)  invalido ou não existe");
    }

    public static MessageNotFoundException BalanceNotReference(Long id) {
        return new MessageNotFoundException("id " + id + " do saldo (balance) não referencia usuario");
    }

    public static MessageNotFoundException CPFAlreadyExists(String cpfUser) {
        return new MessageNotFoundException("CPF " + cpfUser + " já possui conta no WebAppBak");
    }
    public static MessageNotFoundException UserAlreadyHasAccount(Long idUser) {
        return new MessageNotFoundException("Usuario já possui conta no WebAppBak");
    }
}