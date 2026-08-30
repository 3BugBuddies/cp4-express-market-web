package com.br.fiap.cp4_express_market_web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TDS_TB_mercado")
@SequenceGenerator(name = "produto", sequenceName = "SQ_TDS_TB_mercado", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto")
    @Column(name = "id_produto")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    @Column(name = "nm_nome_produto", length = 50, nullable = false)
    private String nome;

    @NotBlank(message = "O tipo é obrigatório")
    @Size(min = 3, max = 50, message = "O tipo deve ter entre 3 e 50 caracteres")
    @Column(name = "tp_tipo", length = 50, nullable = false)
    private String tipo;

    @NotBlank(message = "O setor é obrigatório")
    @Size(min = 3, max = 50, message = "O setor deve ter entre 3 e 50 caracteres")
    @Column(name = "st_setor", length = 50, nullable = false)
    private String setor;

    @NotBlank(message = "O tamanho é obrigatório")
    @Size(min = 2, max = 50, message = "O tamanho deve ter entre 2 e 50 caracteres")
    @Column(name = "tm_tamanho", length = 50, nullable = false)
    private String tamanho;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser um valor positivo")
    @Column(name = "pr_preco", nullable = false)
    private Double preco;
}
