/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.service;

import com.fit5046.entity.Appuser;
import java.sql.Date;
import java.util.Calendar;
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
@Path("com.fit5046.entity.appuser")
public class AppuserFacadeREST extends AbstractFacade<Appuser> {

    @PersistenceContext(unitName = "CalorieTrackerAppPU")
    private EntityManager em;

    public AppuserFacadeREST() {
        super(Appuser.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Appuser entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Appuser entity) {
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
    public Appuser find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Appuser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    /*New methods for Task3a*/
    @GET
    @Path("findByFirstname/{firstname}")
    @Produces({"application/json"})
    public List<Appuser> findByFirstname(@PathParam("firstname") String firstname){
        Query query = em.createNamedQuery("Appuser.findByFirstname");
        query.setParameter("firstname", firstname);
        return query.getResultList();
    }
    
    @GET
    @Path("findBySurname/{surname}")
    @Produces({"application/json"})
    public List<Appuser> findBySurname(@PathParam("surname") String surname){
        Query query = em.createNamedQuery("Appuser.findBySurname");
        query.setParameter("surname", surname);
        return query.getResultList();
    }
    
    @GET
    @Path("findByEmail/{email}")
    @Produces({"application/json"})
    public List<Appuser> findByEmail(@PathParam("email") String email){
        Query query = em.createNamedQuery("Appuser.findByEmail");
        query.setParameter("email", email);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDateofbirth/{dateofbirth}")
    @Produces({"application/json"})
    public List<Appuser> findByDateofbirth(@PathParam("dateofbirth") Date dateofbirth){
        Query query = em.createNamedQuery("Appuser.findByDateofbirth");
        query.setParameter("dateofbirth", dateofbirth);
        return query.getResultList();
    }
    
    @GET
    @Path("findByHeight/{height}")
    @Produces({"application/json"})
    public List<Appuser> findByHeight(@PathParam("height") Integer height){
        Query query = em.createNamedQuery("Appuser.findByHeight");
        query.setParameter("height", height);
        return query.getResultList();
    }
    
    @GET
    @Path("findByWeight/{weight}")
    @Produces({"application/json"})
    public List<Appuser> findByWeight(@PathParam("weight") Integer weight){
        Query query = em.createNamedQuery("Appuser.findByWeight");
        query.setParameter("weight", weight);
        return query.getResultList();
    }
    
    @GET
    @Path("findByGender/{gender}")
    @Produces({"application/json"})
    public List<Appuser> findByGender(@PathParam("gender") String gender){
        Query query = em.createNamedQuery("Appuser.findByGender");
        query.setParameter("gender", gender);
        return query.getResultList();
    }
    
    @GET
    @Path("findByAddress/{address}")
    @Produces({"application/json"})
    public List<Appuser> findByAddress(@PathParam("address") String address){
        Query query = em.createNamedQuery("Appuser.findByAddress");
        query.setParameter("address", address);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPostcode/{postcode}")
    @Produces({"application/json"})
    public List<Appuser> findByPostcode(@PathParam("postcode") String postcode){
        Query query = em.createNamedQuery("Appuser.findByPostcode");
        query.setParameter("postcode", postcode);
        return query.getResultList();
    }
    
    @GET
    @Path("findByActivitylevel/{activitylevel}")
    @Produces({"application/json"})
    public List<Appuser> findByActivitylevel(@PathParam("activitylevel") Integer activitylevel){
        Query query = em.createNamedQuery("Appuser.findByActivitylevel");
        query.setParameter("activitylevel", activitylevel);
        return query.getResultList();
    }
    
    @GET
    @Path("findByStepsnumber/{stepsnumber}")
    @Produces({"application/json"})
    public List<Appuser> findByStepsnumber(@PathParam("stepsnumber") Integer stepsnumber){
        Query query = em.createNamedQuery("Appuser.findByStepsnumber");
        query.setParameter("stepsnumber", stepsnumber);
        return query.getResultList();
    }
    
    /*New methods for task3b*/
    @GET
    @Path("findBySurnameAndEmail/{surname}/{email}")
    @Produces({"application/json"})
    public List<Appuser> findBySurnameAndEmail (@PathParam("surname") String surname, @PathParam("email") String email){
        TypedQuery<Appuser> query = em.createQuery("SELECT a FROM Appuser a WHERE a.surname = :surname AND a.email = :email", Appuser.class);
        query.setParameter("surname", surname);
        query.setParameter("email",email);
        return query.getResultList();
    }
    
    /*New method for task4a*/
    @GET
    @Path("calculationOfUserEachStepCalories/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Double calculationOfUserEachStepCalories(@PathParam("userid") Long userid){
        Double eachStepCalories = 0.0;
        //Get new user ready to calculate
        Appuser currentUser = this.find(userid);
        //The unit of weight in DB is kg, Need transfer to lbs first
        //1kg=lb/2.2046
        //Format: (userWeight*0.49)/userStepNum
        if(currentUser.getStepsnumber() != 0){
            eachStepCalories = (currentUser.getWeight()/2.2046*0.49)/currentUser.getStepsnumber();
        }
        return eachStepCalories;
    }
    
    /*New method for task4b*/
    @GET
    @Path("calculationOfBMR/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Double calculationOfBMR(@PathParam("userid") Long userid){
        Double userBMR = 0.0;
        Appuser tempUser = this.find(userid);
        // Harris-Benedict equation:
        // Men: (13.75*weight) + (5.003*height) - (6.755*age) + 66.5
        // Women: (9.563*weight) + (1.85*height) - (4.676*age) + 65.1
        if(tempUser.getGender().equals("M")){
            userBMR = (13.75 * tempUser.getWeight()) + (5.003 * tempUser.getHeight()) - (6.775 * this.calUserAge(tempUser.getDateofbirth())) + 66.5;
        } else if (tempUser.getGender().equals("F")){
            userBMR = (9.563 * tempUser.getWeight()) + (1.85 * tempUser.getHeight()) - (4.676 * this.calUserAge(tempUser.getDateofbirth())) + 65.1;
        }
        return userBMR;
    }
    
    private int calUserAge(java.util.Date dob){
        Calendar calendar = Calendar.getInstance();
        int yearCurrent = calendar.get(calendar.YEAR);
        int monthCurrent = calendar.get(calendar.MONTH);
        int dayCurrent = calendar.get(calendar.DAY_OF_MONTH);
        
        calendar.setTime(dob);
        int yearBirth = calendar.get(calendar.YEAR);
        int monthBirth = calendar.get(calendar.MONTH);
        int dayBirth = calendar.get(calendar.DAY_OF_MONTH);
        
        int age = yearCurrent - yearBirth;
        
        if(monthCurrent <= monthBirth){
            if(monthCurrent == monthBirth){
                if(dayBirth < dayCurrent)
                    age--;
            } else {
                age--;
            }
        }
        return age;
    }
    
    /*New method for 4c*/
    @GET
    @Path("calculationOfRestCaloriesBurned/{userid}")
    @Produces(MediaType.TEXT_PLAIN)
    public Double calculationOfRestCaloriesBurned(@PathParam("userid") Long userid){
        Appuser tempuser = this.find(userid);
        Double restCaloriesBurned = 0.0;
        switch(tempuser.getActivitylevel()){
            case 1:
                restCaloriesBurned = this.calculationOfBMR(userid) * 1.2;
                return restCaloriesBurned;
            case 2:
                restCaloriesBurned = this.calculationOfBMR(userid) * 1.375;
                return restCaloriesBurned;
            case 3:
                restCaloriesBurned = this.calculationOfBMR(userid) * 1.55;
                return restCaloriesBurned;
            case 4:
                restCaloriesBurned = this.calculationOfBMR(userid) * 1.725;
                return restCaloriesBurned;
            case 5:
                restCaloriesBurned = this.calculationOfBMR(userid) * 1.9;
                return restCaloriesBurned;
            default:
                return restCaloriesBurned;
        }
    }

}
