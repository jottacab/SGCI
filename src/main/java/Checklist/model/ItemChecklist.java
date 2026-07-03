package Checklist.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_checklist")
public class ItemChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private boolean concluido;

    public ItemChecklist() {}

    public ItemChecklist(String nome, boolean concluido) {
        this.nome = nome;
        this.concluido = concluido;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public boolean isConcluido() { return concluido; }
    public void setConcluido(boolean concluido) { this.concluido = concluido; }
}