package financialmanager.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_orcamento")
public class BudgetModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull(message = "O id do usuário não pode estar em branco")
  private Integer id_user;

  @NotBlank(message = "O usuário não pode estar em branco")
  private String usuario;

  @NotBlank(message = "A categoria de gasto não pode estar em branco")
  private String categoriagasto;

  @NotBlank(message = "A descricao não pode estar em branco")
  private String descricao;

  @NotNull(message = "A data não pode estar em branco")
  private LocalDate mesAno;

  @NotNull(message = "O valor orçado não pode estar em branco")
  private Float valorOrcado;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId_user() {
    return id_user;
  }

  public void setId_user(Integer id_user) {
    this.id_user = id_user;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getCategoriagasto() {
    return categoriagasto;
  }

  public void setCategoriagasto(String categoriagasto) {
    this.categoriagasto = categoriagasto;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public LocalDate getMesAno() {
    return mesAno;
  }

  public void setMesAno(LocalDate mesAno) {
    this.mesAno = mesAno;
  }

  public Float getValorOrcado() {
    return valorOrcado;
  }

  public void setValorOrcado(Float valorOrcado) {
    this.valorOrcado = valorOrcado;
  }
}
