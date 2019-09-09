/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.service;

import com.fit5046.entity.Consumption;
import com.fit5046.entity.Food;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Leo
 */
@Stateless
@Path("com.fit5046.entity.consumption")
public class ConsumptionFacadeREST extends AbstractFacade<Consumption> {

    @PersistenceContext(unitName = "CalorieTrackerAppPU")
    private EntityManager em;

    public ConsumptionFacadeREST() {
        super(Consumption.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Consumption entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Consumption entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Consumption find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Consumption> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*New methods for task3a*/
    @GET
    @Path("findByRecorddate/{recorddate}")
    @Produces({"application/json"})
    public List<Consumption> findByRecorddate(@PathParam("recorddate") Date recorddate){
        Query query = em.createNamedQuery("Consumption.findByRecorddate");
        query.setParameter("recorddate", recorddate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByConsumptionamount/{consumptionamount}")
    @Produces({"application/json"})
    public List<Consumption> findByConsumptionamount(@PathParam("consumptionamount") Double consumptionamount){
        Query query = em.createNamedQuery("Consumption.findByConsumptionamount");
        query.setParameter("consumptionamount", consumptionamount);
        return query.getResultList();
    }
    
    /*New method for task3b*/
    @GET
    @Path("findByDateAndConsumptionamount/{recorddate}/{consumptionamount}")
    @Produces({"application/json"})
    public List<Consumption> findByDateAndConsumptionamount(@PathParam("recorddate") Date date, @PathParam("consumptionamount") Double consumptionamount){
        TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.recorddate = :recorddate AND c.consumptionamount = :consumptionamount", Consumption.class);
        query.setParameter("recorddate", date);
        query.setParameter("consumptionamount", consumptionamount);
        return query.getResultList();
    }
    
    /*New method for task3c*/
    @GET
    @Path("findByRecorddateAndFoodname/{recorddate}/{foodname}")
    @Produces({"application/json"})
    public List<Consumption> findByRecorddateAndFoodname(@PathParam("recorddate") Date date, @PathParam("foodname") String foodname){
        TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.foodid.foodname = :foodname AND c.recorddate = :recorddate", Consumption.class);
        query.setParameter("recorddate", date);
        query.setParameter("foodname", foodname);
        return query.getResultList();
    }
    
    /*New method for task3d*/
    @GET
    @Path("findByRecorddateAndSurname/{recorddate}/{surname}")
    @Produces({"application/json"})
    public List<Consumption> findByRecorddateAndSurname(@PathParam("recorddate") Date date, @PathParam("surname") String surname){
        Query query = em.createNamedQuery("Consumption.findByRecorddateAndSurname");
        query.setParameter("recorddate", date);
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    /*New method for task 4d*/
    @GET
    @Path("calculateTotalCaloriesForDay/{userid}/{recorddate}")
    @Produces(MediaType.TEXT_PLAIN)
    public Double calculateTotalCaloriesForDay(@PathParam("userid") Long userid, @PathParam("recorddate") Date recorddate){
        Double totalConsumption = 0.0;
        TypedQuery<Consumption> query = em.createQuery("SELECT c FROM Consumption c WHERE c.userid.userid = :userid AND c.recorddate = :recorddate", Consumption.class);
        query.setParameter("recorddate", recorddate);
        query.setParameter("userid", userid);
        List<Consumption> tempList = query.getResultList();
        System.out.print(query.getResultList().size());
        for(Consumption c : tempList){
            Food tempFood = c.getFoodid();
            System.out.print(tempFood.getFoodname());
            totalConsumption += c.getConsumptionamount() * tempFood.getCalorieamount();
        }
        return totalConsumption;
    }

}
