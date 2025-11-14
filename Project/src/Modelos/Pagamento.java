package Modelos;

public class Pagamento {
    private int tipo;
    private String numero;
    private int senhaCartao;

    private String chave;
    private int senhaPix;

    private String forma;

    /* 
    public Pagamento(int t, String n, int sc, String c, int sp){
        this.tipo = t;
        this.numero = n;
        this.senhaCartao = sc;
        this.chave = c;
        this.senhaPix = sp;
    }
    */
    
    public void DadosCartao(int t, String n, int sc){
        this.tipo = t;
        this.numero = n;
        this.senhaCartao = sc;
    }

    public void DadosPix(String c, int sp){
        this.chave = c;
        this.senhaPix = sp;
    }

    public int getTipo(){
        return tipo;
    }
    public void setTipo(int t){
        this.tipo = t;
    }

    public String getNumero(){
        return numero;
    }
    public void setNumero(String n){
        this.numero = n;
    }

    public int getSenhaCartao(){
        return senhaCartao;
    }
    public void setSenhaCartao(int sc){
        this.senhaCartao = sc;
    }

    public String getChave(){
        return chave;
    }
    public void setChave(String c){
        this.chave = c;
    }

    public int getSenhaPix(){
        return senhaPix;
    }
    public void setSenhaPix(int sp){
        this.senhaPix = sp;
    }

    public void FormaPagamento(String f){
        this.forma = f;
    }

    public String getForma(){
        return forma;
    }
    public void setForma(String forma) {
        this.forma = forma;
    } 
}