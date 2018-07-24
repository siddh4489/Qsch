/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfdc.podio.rest;

import com.sfdc.podio.operation.SalesforcePodioOperation;
import com.sfdc.podio.parser.SalesforcePodioDataParser;
import com.sfdc.podio.util.schedular.InterruptibleJobScheduler;
import com.sfdc.podio.util.schedular.Qschedular;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.quartz.SchedulerException;

/**
 * The SalesforcePodioRestApi class is the Starting point of Salesforce - Podio
 * Api.
 *
 * @author Siddharaj Atodaria
 */
@Path("/podio")
public class SalesforcePodioRestApi {

    /**
     * This newProperty method is used to create new Property in Podio.
     *
     * @param data this is Property data in json format.
     * @param itemId this is the Podio itemId to check existion Podio item.
     * @return String This is return the created Property's ItemId.
     */
    @POST
    @Path("/newProperty")
    public Response newProperty(String data, @HeaderParam("itemId") String itemId) {
        String itemref = "";
        System.out.println(" data : " + data);
        System.out.println(" itemId : " + itemId);
        System.out.println(" called method : newProperty ");
        itemId = (!itemId.isEmpty() ? itemId : "0");
        if (SalesforcePodioOperation.existingPropertyCheck(Integer.parseInt(itemId))) {
            System.out.println(" In  : Update Property ");
            boolean checkUpdate = SalesforcePodioOperation.updateProperty(Integer.parseInt(itemId), SalesforcePodioDataParser.jsonToObject(data));
            System.out.println(" Update Property Status :" + checkUpdate);
            if (checkUpdate) {
                itemref = "Updated Successfully";
            } else {
                itemref = "Update Failed";
            }
        } else {
            System.out.println(" In : New Property");
            itemref = SalesforcePodioOperation.newProperty(SalesforcePodioOperation.getApplicationId("SCS Agreement Intake"), SalesforcePodioDataParser.jsonToObject(data)).toString();
            System.out.println("Created Property itemid : " + itemref);
            itemref = "New ItemId :" + itemref;
        }
        return Response.status(201).entity("Status : " + itemref.toString()).build();
    }

    @GET
    @Path("/start")
    @Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
    public void getInvoke() throws IOException, URISyntaxException, SchedulerException {
        System.out.println("--> get Start");
        //Qschedular.invokeAction();
        InterruptibleJobScheduler q = new InterruptibleJobScheduler();
        q.Start();
    }
    
    @GET
    @Path("/stop")
    @Produces(javax.ws.rs.core.MediaType.TEXT_HTML)
    public void getStop() throws IOException, URISyntaxException, SchedulerException {
        System.out.println("--> get Stop");
        InterruptibleJobScheduler q = new InterruptibleJobScheduler();
        q.stop();
    }
    /*public static void main(String[] args) {
        SalesforcePodioRestApi obj = new SalesforcePodioRestApi();
        //String data = "{\"sfdcid\":\"a003D000001fXItQAM\",\"name\":\"Test Opp Property2\",\"address\":\"\",\"accountnumber\":\"\",\"doors\":\"300\"}";
        String data = "{\"title\":\"Testing\",\"name\":\"Neekens\"}";
        //String data = "{\"sfdcid\":\"a003D000001fXItQAM\",\"name\":\"Test Opp Property2\",\"address\":\"\",\"accountnumber\":\"\",\"doors\":\"300\"}";
        obj.newProperty(data,"895263305");
        //String s = "1234";
        //String s = "1234";
        //String s = "1234";
        //String s = "1234";
        //Integer c = Integer.parseInt(s);
        //System.out.println("--->"+c);
    }*/

}
