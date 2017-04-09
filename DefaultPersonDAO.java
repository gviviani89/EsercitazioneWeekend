/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.interfaces.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.uiip.gviviani.esercizioweekend.interfaces.PersonDAO;
import com.uiip.gviviani.esercizioweekend.models.PersonModel;
import com.uiip.gviviani.esercizioweekend.models.PhoneModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;


public class DefaultPersonDAO implements PersonDAO {

    final static Logger logger = Logger.getLogger(DefaultPersonDAO.class);

    @Override
    public PersonModel getPersonInfo(String numero) {
        PersonModel personModel = new PersonModel();
        PhoneModel phone = new PhoneModel();
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("root");
        datasource.setPassword("root");
        datasource.setUrl("jdbc:mysql://localhost:3306/Rubrica");
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            String sql = "SELECT c.nome, c.cognome, c.data_nascita, t.name "
                    + "FROM contatti c INNER JOIN telefono t ON (c.modello = t.id)"
                    + "WHERE c.numero = ? ;";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, numero);
            ResultSet res = stat.executeQuery();
            if (res.first()) {
                personModel.setNome(res.getString("nome"));
                personModel.setCognome(res.getString("cognome"));
                personModel.setData(res.getString("data_nascita"));
                personModel.setNumero(numero);
                phone.setNome(res.getString("name"));
                personModel.setModel(phone);
            } else {
                personModel = null;
            }
        } catch (SQLException e) {
            logger.error(e);
            personModel=null;
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return personModel;
    }

    @Override
    public boolean aggiornaPersonInfo(String numero, String data) {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("root");
        datasource.setPassword("root");
        datasource.setUrl("jdbc:mysql://localhost:3306/Rubrica");
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            String sql = "UPDATE contatti SET data_nascita = ?"
                    + "WHERE numero = ? ;";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, data);
            stat.setString(2, numero);
            if (stat.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean inserisciPerson(PersonModel person, String nomeTel) {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("root");
        datasource.setPassword("root");
        datasource.setUrl("jdbc:mysql://localhost:3306/Rubrica");
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            String sql = "INSERT INTO contatti (nome, cognome, data_nascita, numero, modello) VALUE "
                    + "(?, ?, ?, ?, ?);";
            int id;
            String sql2 = "SELECT id FROM telefono WHERE name = ? ;";
            PreparedStatement stat2 = connection.prepareStatement(sql2);
            stat2.setString(1, nomeTel);
            ResultSet res = stat2.executeQuery();
            if (res.first()) {
                id = res.getInt("id");
                PreparedStatement stat = connection.prepareStatement(sql);
                stat.setString(1, person.getNome());
                stat.setString(2, person.getCognome());
                stat.setString(3, person.getData());
                stat.setString(4, person.getNumero());
                stat.setInt(5, id);
                if (stat.executeUpdate() > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

    @Override
    public boolean cancellaPerson(String numero) {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("root");
        datasource.setPassword("root");
        datasource.setUrl("jdbc:mysql://localhost:3306/Rubrica");
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            String sql = "DELETE FROM contatti "
                    + "WHERE numero = ? ;";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, numero);
            if (stat.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return false;
    }

}
