/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uiip.gviviani.esercizioweekend.interfaces.impl;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.uiip.gviviani.esercizioweekend.interfaces.PhoneDAO;
import static com.uiip.gviviani.esercizioweekend.interfaces.impl.DefaultPersonDAO.logger;
import com.uiip.gviviani.esercizioweekend.models.PhoneModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.dbutils.DbUtils;


public class DefaultPhoneDAO implements PhoneDAO {

    @Override
    public PhoneModel getPhoneInfo(String name) {
        PhoneModel phone = new PhoneModel();
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("root");
        datasource.setPassword("root");
        datasource.setUrl("jdbc:mysql://localhost:3306/Rubrica");
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            String sql = "SELECT id, name, brand, opsys, displaySize "
                    + "FROM telefono WHERE name = ? ;";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, name);
            ResultSet res = stat.executeQuery();
            if (res.first()) {
                phone.setNome(res.getString("name"));
                phone.setBrand(res.getString("brand"));
                phone.setOpsys(res.getString("opsys"));
                phone.setDisplay(res.getString("displaySize"));
            } else {
                phone = null;
            }
        } catch (SQLException e) {
            logger.error(e);
            phone=null;
        } finally {
            DbUtils.closeQuietly(connection);
        }
        return phone;
    }

    @Override
    public boolean inserisciPhone(PhoneModel phone) {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setUser("root");
        datasource.setPassword("root");
        datasource.setUrl("jdbc:mysql://localhost:3306/Rubrica");
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            String sql = "INSERT INTO telefono (name, brand, opsys, displaySize) VALUE "
                    + "(?, ?, ?, ?);";
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setString(1, phone.getNome());
            stat.setString(2, phone.getBrand());
            stat.setString(3, phone.getOpsys());
            stat.setString(4, phone.getDisplay());
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
