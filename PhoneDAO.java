/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.interfaces;

import com.uiip.gviviani.esercizioweekend.models.PhoneModel;


public interface PhoneDAO {

    public PhoneModel getPhoneInfo(String name);

    public boolean inserisciPhone(PhoneModel phone);

}
