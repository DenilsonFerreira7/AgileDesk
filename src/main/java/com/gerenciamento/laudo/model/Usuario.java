package com.gerenciamento.laudo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "idBalance")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBalance;

    @Column(name = "debit")
    private double debit;
}
