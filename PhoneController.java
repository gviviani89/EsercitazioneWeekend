/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.controllers;

import com.uiip.gviviani.esercizioweekend.models.PhoneModel;
import com.uiip.gviviani.esercizioweekend.views.PhoneView;

/**
 *
 * @author cdigr
 */
public class PhoneController {

    private PhoneModel model;
    private PhoneView view;

    public PhoneController(PhoneModel model, PhoneView view) {
        this.model = model;
        this.view = view;
    }

    public void updateView(PhoneModel phone) {
        view.setPhone(phone);
    }

    public void updateModel(PhoneView view) {
        this.model = view.getPhone();
    }

    public PhoneModel getModel() {
        return model;
    }

    public void setModel(PhoneModel model) {
        this.model = model;
    }

    public PhoneView getView() {
        return view;
    }

    public void setView(PhoneView view) {
        this.view = view;
    }

}
