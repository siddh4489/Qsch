/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.connection;

import com.podio.APIFactory;
import com.podio.ResourceFactory;
import com.podio.oauth.OAuthClientCredentials;
import com.podio.oauth.OAuthUsernameCredentials;
import com.sfdc.podio.util.SalesforcePodioConfiguration;
import java.util.Properties;

/**
 *
 * @author Siddhrajsinh_Atodaria
 * 
 * SalesforcePodioConnectionPool class is used for connection between Salesforce and Podio system.
 */
public class SalesforcePodioConnectionPool {

    static APIFactory apiFactoryConnection;
    
    /** 
     * This podioConncetion method is used to connect with Podio.
     * 
     * @param username this is Podio username.
     * @param password this is Podio password.
     * @param clientId this is Podio clientId.
     * @param SecretId this is Podio SecretId.
     * @return APIFactory This is return the connection object to Podio.
     */
    public static APIFactory podioConncetion() {
        Properties prop = SalesforcePodioConfiguration.loadConfiguration();
        String username = (System.getenv("PODIO_USERNAME") != null ? 
                                   System.getenv("PODIO_USERNAME") : 
                                   prop.getProperty("username"));
        String password = (System.getenv("PODIO_PASSWORD") != null ? 
                                   System.getenv("PODIO_PASSWORD") : 
                                   prop.getProperty("password"));
        String client = (System.getenv("PODIO_CLIENT") != null ? 
                                 System.getenv("PODIO_CLIENT") : 
                                 prop.getProperty("client"));
        String Secret = (System.getenv("PODIO_SECRET") != null ? 
                                 System.getenv("PODIO_SECRET") : 
                                 prop.getProperty("secret"));

        ResourceFactory resourceFactory = new ResourceFactory(
                new OAuthClientCredentials(client, Secret),
                new OAuthUsernameCredentials(username, password));
        apiFactoryConnection = new APIFactory(resourceFactory);
        return apiFactoryConnection;
    }
}
