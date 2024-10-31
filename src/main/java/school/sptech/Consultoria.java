package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
  private String nome;
  private Integer vagas;
  private List<Desenvolvedor> desenvolvedores;

  public Consultoria(String nome, Integer vagas, List<Desenvolvedor> desenvolvedores) {
    this.nome = nome;
    this.vagas = vagas;
    this.desenvolvedores = desenvolvedores;
  }

  public Consultoria() {
  }

  public Consultoria(Integer vagas, String nome) {
    this.vagas = vagas;
    this.nome = nome;
  }

  public Integer getVagas() {
    return vagas;
  }

  public void setVagas(Integer vagas) {
    this.vagas = vagas;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void contratar (Desenvolvedor desenvolvedor){
    if(desenvolvedores.size()>= vagas) {
      System.out.println("Não é possivel adicionar, não há vagas");
    }else{
      desenvolvedores.add(desenvolvedor);
    }
  }
  public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor){
    if(desenvolvedor.isFullstack()){
      if(desenvolvedores.size()< vagas){
        desenvolvedores.add(desenvolvedor);
        return true;
      }else{
        System.out.println("Não é possivel adicionar, não há vagas");
        return false;
      }
    }else{
      System.out.println("Não foi possivel verificar se o desenvolvedor é fullstack");
      return false;
    }
  }
  public Double getTotalSalarios(){
    Double somaTotal = 0.0;
    for (Desenvolvedor desenvolvedor:desenvolvedores){
      somaTotal+= desenvolvedor.calcularSalario();
    }
    return somaTotal;
  }
  public Integer qtdDesenvolvedoresMobile(){
    Integer cont = 0;
    for(Desenvolvedor desenvolvedor : desenvolvedores){
      if(desenvolvedor instanceof DesenvolvedorMobile){
        cont++;
      }
    }
    return cont;
  }
  public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){
    List<Desenvolvedor> desenvolvedoresAux = new ArrayList<>();

    for(Desenvolvedor desenvolvedor:desenvolvedores){
      if(desenvolvedor.calcularSalario()>=salario){
        desenvolvedoresAux.add(desenvolvedor);
      }
    }
    return desenvolvedoresAux;
  }
  public Desenvolvedor buscarMenorSalario(){
    if(desenvolvedores.isEmpty()) {
      return null;
    }
    Double menorSalario = desenvolvedores.get(0).calcularSalario();
    Desenvolvedor desenvolvedorMenor = desenvolvedores.get(0);
    for(Desenvolvedor desenvolvedor: desenvolvedores){
      if(menorSalario > desenvolvedor.calcularSalario()){
        menorSalario = desenvolvedor.calcularSalario();
        desenvolvedorMenor = desenvolvedor;
      }
    }
    return desenvolvedorMenor;
    }
  public List<Desenvolvedor> buscarPorTecnologia(String tecnologia){
    List<Desenvolvedor> desenvolvedorsTech = new ArrayList<>();
    for (Desenvolvedor desenvolvedor : desenvolvedores) {
      if(desenvolvedor instanceof DesenvolvedorWeb) {
        if (((DesenvolvedorWeb) desenvolvedor).getFrontend().equals(tecnologia)) {
          desenvolvedorsTech.add(desenvolvedor);
        }
        if (((DesenvolvedorWeb) desenvolvedor).getBackend().equals(tecnologia)) {
          desenvolvedorsTech.add(desenvolvedor);
        }
        if (((DesenvolvedorWeb) desenvolvedor).getSgbd().equals(tecnologia)) {
          desenvolvedorsTech.add(desenvolvedor);
        }
      }
      if(desenvolvedor instanceof DesenvolvedorMobile){
        if(((DesenvolvedorMobile)desenvolvedor).getPlataforma().equals(tecnologia)){
          desenvolvedorsTech.add(desenvolvedor);
        }
        if(((DesenvolvedorMobile)desenvolvedor).getLinguagem().equals(tecnologia)){
          desenvolvedorsTech.add(desenvolvedor);
        }
      }

    }
    return desenvolvedorsTech;
  }
  public Double getTotalSalariosPorTecnologia(String tecnologia){
    List<Desenvolvedor> desenvolvedors = buscarPorTecnologia(tecnologia);
    Double totalSalario = 0.0;
    for(Desenvolvedor desenvolvedor : desenvolvedors){
      totalSalario+= desenvolvedor.calcularSalario();
    }
    return totalSalario;
  }
}
