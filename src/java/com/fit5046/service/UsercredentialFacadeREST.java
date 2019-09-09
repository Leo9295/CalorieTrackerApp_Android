/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.service;

import com.fit5046.entity.Usercredential;
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
@Path("com.fit5046.entity.usercredential")
public class UsercredentialFacadeREST extends AbstractFacade<Usercredential> {

    @PersistenceContext(unitName = "CalorieTrackerAppPU")
    private EntityManager em;

    public UsercredentialFacadeREST() {
        super(Usercredential.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usercredential entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Usercredential entity) {
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
    public Usercredential find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usercredential> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usercredential> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("findByUsername/{username}")
    @Produces({"application/json"})
    public List<Usercredential> findByUsername(@PathParam("username") String username){
        Query query = em.createNamedQuery("Usercredential.findByUsername");
        query.setParameter("username", username);
        return query.getResultList();
    }
    
    @GET
    @Path("findByPasswordhash/{passwordhash}")
    @Produces({"application/json"})
    public List<Usercredential> findByPasswordhash(@PathParam("passwordhash") String passwordhash){
        Query query = em.createNamedQuery("Usercredential.findByPasswordhash");
        query.setParameter("passwordhash", passwordhash);
        return query.getResultList();
    }
    
    @GET
    @Path("findByDateofsignup/{dateofsignup}")
    @Produces({"application/json"})
    public List<Usercredential> findByDateofsignup(@PathParam("dateofsignup") Date dateofsignup){
        Query query = em.createNamedQuery("Usercredential.findByDateofsignup");
        query.setParameter("dateofsignup", dateofsignup);
        return query.getResultList();
    }
    
    /*New methods for task3b*/
    @GET
    @Path("findByUsernameAndDateofsignup/{username}/{dateofsignup}")
    @Produces({"application/json"})
    public List<Usercredential> findByDateAndConsumptionamount(@PathParam("username") String username, @PathParam("dateofsignup") Date dateofsignup){
        TypedQuery<Usercredential> query = em.createQuery("SELECT u FROM Usercredential u WHERE u.username = :username AND u.dateofsignup = :dateofsignup", Usercredential.class);
        query.setParameter("username", username);
        query.setParameter("dateofsignup", dateofsignup);
        return query.getResultList();
    }
    
    @GET
    @Path("findByUsernameAndPwd/{username}/{password}")
    @Produces({"application/json"})
    public List<Usercredential> findByUsernameAndPwd(@PathParam("username") String username, @PathParam("password") String password){
        TypedQuery<Usercredential> query = em.createQuery("SELECT u FROM Usercredential u WHERE u.username = :username AND u.passwordhash = :password", Usercredential.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getResultList();
    }
    

}
