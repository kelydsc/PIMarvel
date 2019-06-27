package br.com.digitalhouse.digital.pimarvel.movie.model;

public class Movie {
    public String nome;
    public int ano;
    private int image;

    public Movie(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Movie() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Movie(String nome, int ano, int image) {
        this.nome = nome;
        this.ano = ano;
        this.image = image;
    }
}
