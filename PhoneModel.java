/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.models;


public class PhoneModel {

    private String nome, brand, opsys, display;

    public PhoneModel() {
    }

    public PhoneModel(String nome, String brand, String opsys, String display) {
        this.nome = nome;
        this.brand = brand;
        this.opsys = opsys;
        this.display = display;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOpsys() {
        return opsys;
    }

    public void setOpsys(String opsys) {
        this.opsys = opsys;
    }
    

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append("Nome = ").append(nome).append("\n")
                .append("Brand = ").append(brand).append("\n")
                .append("OpSys = ").append(opsys).append("\n")
                .append("Display = ").append(display);
        return build.toString();
    }

}
