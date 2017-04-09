/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.views;

import com.uiip.gviviani.esercizioweekend.models.PhoneModel;


public class PhoneView {

    private PhoneModel phone;

    public PhoneView(PhoneModel person) {
        this.phone = person;
    }

    public void printInfo() {
        System.out.println(phone.toString());
    }

    public PhoneModel getPhone() {
        return phone;
    }

    public void setPhone(PhoneModel phone) {
        this.phone = phone;
    }

}
