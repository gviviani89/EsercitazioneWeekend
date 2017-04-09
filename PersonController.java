/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.controllers;

import com.uiip.gviviani.esercizioweekend.models.PersonModel;
import com.uiip.gviviani.esercizioweekend.views.PersonView;


public class PersonController {

    private PersonModel person;
    private PersonView view;

    public PersonController(PersonModel person, PersonView view) {
        this.person = person;
        this.view = view;
    }

    public void aggiornaView(PersonModel person) {
        view.setPerson(person);
    }

    public void aggiornaModel(PersonView view) {

        this.person = view.getPerson();
    }

    public PersonView getPersonView() {
        return view;
    }

    public void setPersonView(PersonView view) {
        this.view = view;
    }

    public PersonModel getPersonModel() {
        return person;
    }

    public void setPersonModel(PersonModel person) {
        this.person = person;
    }

}
