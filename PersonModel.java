/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.models;


public class PersonModel {

    private String nome, cognome, data, numero;
    private PhoneModel model;

    public PersonModel() {
    }

    public PersonModel(String nome, String cognome, String data, String numero, PhoneModel model) {
        this.nome = nome;
        this.cognome = cognome;
        this.data = data;
        this.numero = numero;
        this.model = model;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PhoneModel getModel() {
        return model;
    }

    public void setModel(PhoneModel model) {
        this.model = model;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append("Name = ").append(nome).append("\n")
                .append("Cognome = ").append(cognome).append("\n")
                .append("Date = ").append(data).append("\n")
                .append("Phone number = ").append(numero).append("\n")
                .append("Model = ").append(model.getNome());
        return build.toString();
    }

}
