/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.views;

import com.uiip.gviviani.esercizioweekend.models.PersonModel;

/**
 *
 * @author cdigr
 */
public class PersonView {

    private PersonModel person;

    public PersonView(PersonModel person) {
        this.person = person;
    }

    public void printInfo() {
        System.out.println(person);
    }

    public PersonModel getPerson() {
        return person;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

}
