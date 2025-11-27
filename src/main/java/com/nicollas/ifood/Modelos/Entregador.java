package com.nicollas.ifood.Modelos;

public class Entregador {
    private int id;
    private String nome;
    private String telefone;
    private String dataNasc;
    private String email;
    private String senha;
    private Veiculo veiculo;
    private int idVeiculo;

    public Entregador(String nome, String telefone, String dataNasc, String email, String senha, Veiculo veiculo){
        this.nome = nome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.veiculo = veiculo;
        if (veiculo != null) {
            this.idVeiculo = veiculo.getId();
        }
    }

    public Entregador(int id, String nome, String telefone, String dataNasc,
                      String email, String senha, Veiculo veiculo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.veiculo = veiculo;
        if (veiculo != null) {
            this.idVeiculo = veiculo.getId();
        }
    }

    public Entregador(int id, String nome, String telefone, String dataNasc, String email, String senha, int idVeiculo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
        this.email = email;
        this.senha = senha;
        this.idVeiculo = idVeiculo;
        this.veiculo = null;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

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

    public Veiculo getVeiculo() {return veiculo;}
    public void setVeiculo(Veiculo veiculo) {this.veiculo = veiculo;}

    public int getIdVeiculo() {return idVeiculo;}
    public void setIdVeiculo(int idVeiculo) {this.idVeiculo = idVeiculo;}
}
