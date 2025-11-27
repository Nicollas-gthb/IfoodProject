package com.nicollas.ifood.Modelos;

public class Veiculo {
    private int id;
    private String placa;
    private String ano;
    private String modelo;

    public Veiculo(String placa, String ano, String modelo){
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
    }

    public Veiculo(int id, String placa, String ano, String modelo){
        this.id = id;
        this.placa = placa;
        this.ano = ano;
        this.modelo = modelo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() {return placa;}
    public void setPlaca(String placa) {this.placa = placa;}

    public String getAno() {return ano;}
    public void setAno(String ano) {this.ano = ano;}

    public String getModelo() {return modelo;}
    public void setModelo(String modelo) {this.modelo = modelo;}
}
