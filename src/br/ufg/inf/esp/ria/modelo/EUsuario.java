package br.ufg.inf.esp.ria.modelo;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

@Table(name = "usuario")
public class EUsuario {

  @PrimaryKey
  @Column(name = "id")
  private Long id;

  @Column(name = "identificacao")
  private String identificacao;

  @Column(name = "nome")
  private String nome;

  @Column(name = "senha")
  private String senha;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getIdentificacao() {
    return identificacao;
  }

  public void setIdentificacao(String identificacao) {
    this.identificacao = identificacao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

}
