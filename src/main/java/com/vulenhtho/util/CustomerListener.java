package com.vulenhtho.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class CustomerListener implements ServletContextListener {
    private Driver driver = null;

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            this.driver = new com.mysql.jdbc.Driver();
            boolean skipRegistration = false;
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver1 = drivers.nextElement();
                if (driver1 instanceof com.mysql.jdbc.Driver) {
                    com.mysql.jdbc.Driver alreadyRegistered = (com.mysql.jdbc.Driver) driver1;
                    if (alreadyRegistered.getClass() == this.driver.getClass()) {
                        skipRegistration = true;
                        this.driver = alreadyRegistered;
                        break;
                    }
                }
            }
            if (!skipRegistration) {
                DriverManager.registerDriver(driver);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (this.driver != null) {
            try {
                DriverManager.registerDriver(this.driver);
                this.driver = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
