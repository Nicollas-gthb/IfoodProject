package Modelos;

public class Endereco {
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

    
    //@Override 
    //public String toString(){
    //    String r = String.format("Rua: %s\nNº: %s\nBairro: %s\nCidade: %s\nCEP: %s\nComplemento: %s", rua, numero, bairro, cidade, cep, complemento);
    //    return r;
    //}
}
