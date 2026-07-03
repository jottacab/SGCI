package Checklist.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "codigo_patrimonio")
    private String codigoPatrimonio;

    // Novos campos adicionados
    private String placa; 
    
    private LocalDateTime dataUltimaRevisao;
    
    private String status; // Ex: "APTO", "INAPTO", "MANUTENCAO"

    private String tipo;

    // Construtores
    public Equipamento() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigoPatrimonio() { return codigoPatrimonio; }
    public void setCodigoPatrimonio(String codigoPatrimonio) { this.codigoPatrimonio = codigoPatrimonio; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    // Novos Getters e Setters
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public LocalDateTime getDataUltimaRevisao() { return dataUltimaRevisao; }
    public void setDataUltimaRevisao(LocalDateTime dataUltimaRevisao) { this.dataUltimaRevisao = dataUltimaRevisao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}