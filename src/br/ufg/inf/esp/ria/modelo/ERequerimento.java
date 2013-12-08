package br.ufg.inf.esp.ria.modelo;

import java.util.Date;

import org.droidpersistence.annotation.Column;
import org.droidpersistence.annotation.PrimaryKey;
import org.droidpersistence.annotation.Table;

@Table(name = "requerimento")
public class ERequerimento {

  @PrimaryKey(autoIncrement = true)
  @Column(name = "id")
  private Long id;

  @Column(name = "tipo_requerimento")
  private String tipoRequerimento;

  @Column(name = "data_cadastro")
  private Date dataCadastro;

  @Column(name = "situacao")
  private String situacao;

  @Column(name = "dataSituacao")
  private Date dataSituacao;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public void setDataCadastro(Date dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public String getTipoRequerimento() {
    return tipoRequerimento;
  }

  public void setTipoRequerimento(String tipoRequerimento) {
    this.tipoRequerimento = tipoRequerimento;
  }

  public String getSituacao() {
    return situacao;
  }

  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }

  public Date getDataSituacao() {
    return dataSituacao;
  }

  public void setDataSituacao(Date dataSituacao) {
    this.dataSituacao = dataSituacao;
  }

}
