/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend;

import com.uiip.gviviani.esercizioweekend.controllers.PersonController;
import com.uiip.gviviani.esercizioweekend.interfaces.PersonDAO;
import com.uiip.gviviani.esercizioweekend.interfaces.PhoneDAO;
import com.uiip.gviviani.esercizioweekend.interfaces.impl.DefaultPersonDAO;
import com.uiip.gviviani.esercizioweekend.interfaces.impl.DefaultPhoneDAO;
import com.uiip.gviviani.esercizioweekend.models.PersonModel;
import com.uiip.gviviani.esercizioweekend.models.PhoneModel;
import com.uiip.gviviani.esercizioweekend.views.PersonView;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;


public class App {

   
    public static void main(String[] args) {
        try {
            int scelta;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()));
            PersonModel person = new PersonModel();
            PhoneModel phone = null;
            String nome, cognome, numeroTel, brand, opsys, data = null, modello, display;
            PersonView view = new PersonView(person);
            PersonController controller = new PersonController(person, view);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String menu = "GESTIONE DATABASE RUBRICA TELEFONICA\n\n\n1.Nuovo utente\n2.Nuovo telefono\n"
                    + "3.Visualizza utente\n4.Aggiorna data utente\n"
                    + "5.Cancella utente\n0.Esci";
            do {
                System.out.println(menu);
                scelta = Integer.parseInt(in.readLine());
                switch (scelta) {
                    case 1:
                    	System.out.print("Inserisci nome: ");
                        nome = in.readLine();
                        System.out.print("Inserisci cognome: ");
                        cognome = in.readLine();
                        System.out.print("Inserisci data di nascita (yyyy-MM-dd): ");
                        try{
                        data = in.readLine();
                        data = formato.format(formato.parse(data));
                        }
                        catch(ParseException e){
                        	System.out.println("Formato sbagliato! Non è possibile inserire data");
                        }
                        System.out.print("Inserisci numero di telefono: ");
                        numeroTel = in.readLine();
                        person = new PersonModel();
                        person.setNome(nome);
                        person.setCognome(cognome);
                        person.setData(data);
                        person.setNumero(numeroTel);
                        System.out.print("Inserisci modello telefono: ");                       
                        modello = in.readLine();
                        phone = getPhoneFromDB(modello);
                        if (phone != null) {
                            person.setModel(phone);
                            if (inserisciPersona(person, modello)) {
                                System.out.println("Utente inserito correttamente");
                            } else {
                                System.out.println("Utente non inserito correttamente");
                            }
                        } else {
                            System.out.println("Telefono non presente.");
                            System.out.println("Non puoi aggiungere un telefono non presente in database");
                        }
                        break;
                    case 2:
                        System.out.print("Inserisci nome: ");
                        nome = in.readLine();
                        System.out.print("Inserisci brand: ");
                        brand = in.readLine();
                        System.out.print("Inserisci sistema operativo: ");
                        opsys = in.readLine();
                        System.out.print("Inserisci grandezza del display: ");
                        display = in.readLine();
                        phone = new PhoneModel();
                        phone.setNome(nome);
                        phone.setBrand(brand);
                        phone.setOpsys(opsys);
                        phone.setDisplay(display);
                        if (inserisciTelefono(phone)) {
                            System.out.println("Telefono inserito correttamente");
                        } else {
                            System.out.println("Telefono non inserito");
                        }
                        break;
                    case 3:
                    	System.out.println("Inserisci numero di telefono: ");
                        numeroTel = in.readLine();
                        person = getPersonFromDB(numeroTel);
                        if (person != null) {
                            controller.setPersonModel(person);
                            controller.aggiornaView(person);
                            view.printInfo();
                        } else {
                            System.out.println("Numero non salvato in rubrica");
                        }
                        break;
                    case 4:
                    	System.out.println("Inserisci numero di telefono: ");                    	
                        numeroTel = in.readLine();
                    	System.out.println("Inserisci data di nascita: ");
                    	 try{
                             data = in.readLine();
                             data = formato.format(formato.parse(data));
                             }
                             catch(ParseException e){
                             	System.out.println("Formato sbagliato! Non è possibile inserire data");
                             }
                        if (aggiornaUtente(numeroTel, data)) {
                            person = getPersonFromDB(numeroTel);
                            controller.setPersonModel(person);
                            controller.aggiornaView(person);
                            System.out.println("Utente aggiornato:");
                            view.printInfo();
                        } else {
                            System.out.println("Numero non salvato in rubrica");
                        }
                        break;
                    case 5:
                    	System.out.println("Inserisci numero di telefono: ");                    	
                        numeroTel = in.readLine();
                        if (deleteUserFromDB(numeroTel)) {
                            System.out.println("Utente cancellato");
                        } else {
                            System.out.println("Numero non salvato in rubrica");
                        }
                        break;
                    case 0:
                        System.out.println("Grazie per aver scelto il nostro servizio!");
                        break;
                    default:
                        System.out.println("Scelta errata!");
                        break;
                }
            } while (scelta != 0);
        } catch (IOException ex) {
        }
    }

    private static boolean aggiornaUtente(String person, String data) {
        PersonDAO personDAO = new DefaultPersonDAO();
        return personDAO.aggiornaPersonInfo(person, data);
    }

    private static PersonModel getPersonFromDB(String numeroTel) {
        PersonDAO personDAO = new DefaultPersonDAO();
        return personDAO.getPersonInfo(numeroTel);
    }

    private static PhoneModel getPhoneFromDB(String nome) {
        PhoneDAO phoneDAO = new DefaultPhoneDAO();
        return phoneDAO.getPhoneInfo(nome);
    }

    private static boolean inserisciTelefono(PhoneModel phone) {
        PhoneDAO phoneDAO = new DefaultPhoneDAO();
        return phoneDAO.inserisciPhone(phone);
    }

    private static boolean deleteUserFromDB(String numeroTel) {
        PersonDAO personDAO = new DefaultPersonDAO();
        return personDAO.cancellaPerson(numeroTel);
    }

    private static boolean inserisciPersona(PersonModel person, String modello) {
        PersonDAO personDAO = new DefaultPersonDAO();
        return personDAO.inserisciPerson(person, modello);
    }

}
