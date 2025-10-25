package Modelos;

public class Cliente {
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String dataNasc;

    public Cliente(String nome, String email, String senha, String telefone, String dataNasc)
    {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataNasc = dataNasc;
    }

    public String getNome()
    {
        return nome;
    }
    public void setNome(String n)
    {
        this.nome = n;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String e)
    {
        this.email = e;
    }

    public String getSenha()
    {
        return senha;
    }
    public void setSenha(String s)
    {
        this.senha = s;
    }

    public String getTelefone()
    {
        return telefone;
    }
    public void setTelefone(String t)
    {
        this.telefone = t;
    }

    public String getDataNasc()
    {
        return dataNasc;
    }
    public void setDataNasc(String d)
    {
        this.dataNasc = d;
    }
}
