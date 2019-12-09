package it.unisalento.pps1920.carsharing.model;

public class Modello {

    private int id;
    private String nome;
    private int numPosti;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumPosti() {
        return numPosti;
    }

    public void setNumPosti(int numPosti) {
        this.numPosti = numPosti;
    }

    @Override
    public String toString() {
        return nome;
    }
}
