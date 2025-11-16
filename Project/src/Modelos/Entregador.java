package Modelos;

public class Entregador {
    private String nome;
    private String telefone;
    private String dataNasc;
    private String email;
    private String senha;

    public Entregador(String nome, String telefone, String dataNasc, String email, String senha){
        this.nome = nome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getDataNasc() {return dataNasc;}
    public void setDataNasc(String dataNasc) {this.dataNasc = dataNasc;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}
}
