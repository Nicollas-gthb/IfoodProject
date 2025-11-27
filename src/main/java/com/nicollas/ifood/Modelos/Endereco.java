package com.nicollas.ifood.Modelos;

public class Endereco {
    private int id;
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String cep;
    private String complemento; 

    public Endereco(String r, String n, String c, String b, String cp, String cm){
        this.rua = r;
        this.numero = n;
        this.cidade = c;
        this.bairro = b;
        this.cep = cp;
        this.complemento = cm;
    }

    public Endereco(int id, String r, String n, String c, String b, String cp, String cm){
        this.id = id;
        this.rua = r;
        this.numero = n;
        this.cidade = c;
        this.bairro = b;
        this.cep = cp;
        this.complemento = cm;
    }

    public void getEndereco(){
        System.out.println("Rua: " + getRua());
        System.out.println("Numero: " + getNumero());
        System.out.println("Bairro: " + getBairro());
        System.out.println("Cidade: " + getCidade());
        System.out.println("Complemento: " + getComplemento());
        System.out.println("CEP: " + getCep());
    }

    public String getEnderecoCurto(){
        return getRua() + " " + getNumero() + " " + getBairro() + " " + getCidade() + " "  + getComplemento() + " " + getCep();
    }

    public int getId(){return id;}
    public void setId(int id) {this.id = id;}

    public String getRua(){
        return rua;
    }
    public void setRua(String r){
        this.rua = r;
    }

    public String getNumero(){
        return numero;
    }
    public void setNumero(String n){
        this.numero = n;
    }

    public String getCidade(){
        return cidade;
    }
    public void setCidade(String c){
        this.cidade = c;
    }

    public String getBairro(){
        return bairro;
    }
    public void setBairro(String b){
        this.bairro = b;
    }

    public String getCep(){
        return cep;
    }
    public void setCep(String cp){
        this.cep = cp;
    }

    public String getComplemento(){
        return complemento;
    }
    public void setComplemento(String cm){
        this.complemento = cm;
    }



}
