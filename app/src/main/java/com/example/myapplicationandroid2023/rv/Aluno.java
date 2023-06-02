package com.example.myapplicationandroid2023.rv;

public class Aluno
{
    public String nome;
    public String idade;
    public Integer fotoId;

    public Aluno(String nome, String idade, Integer fotoId) {
        this.nome = nome;
        this.idade = idade;
        this.fotoId = fotoId;
    }

    // Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Idade
    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }

    // Foto
    public Integer getFotoId() {
        return fotoId;
    }
    public void setFotoId(Integer fotoId) {
        this.fotoId = fotoId;
    }
}
