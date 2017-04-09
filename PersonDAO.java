/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.interfaces;

import com.uiip.gviviani.esercizioweekend.models.PersonModel;


public interface PersonDAO {

    public PersonModel getPersonInfo(String numero);

    public boolean aggiornaPersonInfo(String numero, String data);

    public boolean inserisciPerson(PersonModel person, String nomeTel);

    public boolean cancellaPerson(String numero);
}
