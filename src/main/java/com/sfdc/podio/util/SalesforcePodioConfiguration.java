/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Siddhrajsinh_Atodari
 * 
 * This SalesforcePodioConfiguration class is used to load configuration data.
 */

public class SalesforcePodioConfiguration {
/** 
     * This loadConfiguration method is used to load config file.
     * @param configfile this is configuration file stored in application.
     * @return Properties This is return the properties object to get conf data.
     */
    
    public static Properties loadConfiguration() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
}
