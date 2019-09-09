/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit5046.service;

import com.fit5046.entity.Report;
import com.fit5046.entity.ReportQueryEntity;
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
@Path("com.fit5046.entity.report")
public class ReportFacadeREST extends AbstractFacade<Report> {

    @PersistenceContext(unitName = "CalorieTrackerAppPU")
    private EntityManager em;

    public ReportFacadeREST() {
        super(Report.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Report entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Report entity) {
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
    public Report find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Report> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    /*New methods for 3a*/
    @GET
    @Path("findByReportdate/{reportdate}")
    @Produces({"application/json"})
    public List<Report> findByReportdate(@PathParam("reportdate") Date reportdate){
        Query query = em.createNamedQuery("Report.findByReportdate");
        query.setParameter("reportdate", reportdate);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCaloriesconsumed/{caloriesconsumed}")
    @Produces({"application/json"})
    public List<Report> findByCaloriesconsumed(@PathParam("caloriesconsumed") Integer caloriesconsumed){
        Query query = em.createNamedQuery("Report.findByCaloriesconsumed");
        query.setParameter("caloriesconsumed", caloriesconsumed);
        return query.getResultList();
    }
    
    @GET
    @Path("findByCaloriesburned/{caloriesburned}")
    @Produces({"application/json"})
    public List<Report> findByCaloriesburned(@PathParam("caloriesburned") Integer caloriesburned){
        Query query = em.createNamedQuery("Report.findByCaloriesburned");
        query.setParameter("caloriesburned", caloriesburned);
        return query.getResultList();
    }
    
    @GET
    @Path("findByTotalstepsnum/{totalstepsnum}")
    @Produces({"application/json"})
    public List<Report> findByTotalstepsnum(@PathParam("totalstepsnum") Integer totalstepsnum){
        Query query = em.createNamedQuery("Report.findByTotalstepsnum");
        query.setParameter("totalstepsnum", totalstepsnum);
        return query.getResultList();
    }
    
    @GET
    @Path("findByClaoriegoal/{claoriegoal}")
    @Produces({"application/json"})
    public List<Report> findByClaoriegoal(@PathParam("claoriegoal") Integer claoriegoal){
        Query query = em.createNamedQuery("Report.findByClaoriegoal");
        query.setParameter("claoriegoal", claoriegoal);
        return query.getResultList();
    }
    
    /*New methods for task3b*/
    @GET
    @Path("findByReportdateAndClaoriegoal/{reportdate}/{claoriegoal}")
    @Produces({"application/json"})
    public List<Report> findByDateAndConsumptionamount(@PathParam("reportdate") Date reportdate, @PathParam("claoriegoal") Integer claoriegoal){
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.reportdate = :reportdate AND r.claoriegoal = :claoriegoal", Report.class);
        query.setParameter("reportdate", reportdate);
        query.setParameter("claoriegoal", claoriegoal);
        return query.getResultList();
    }
    
    /*New method for task 5a*/
    @GET
    @Path("createReport1/{userid}/{queryDate}")
    @Produces({"application/json"})
    public ReportQueryEntity createReport1 (@PathParam("userid") Long userid, @PathParam("queryDate") Date queryDate){
        ReportQueryEntity rqe = new ReportQueryEntity();
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.reportdate = :reportdate", Report.class);
        query.setParameter("userid", userid);
        query.setParameter("reportdate", queryDate);
        List<Report> tempList = query.getResultList();
        for(Report r : tempList){
            rqe.setUser(r.getUserid());
            rqe.setSingleDayConsumedCalories(r.getCaloriesconsumed());
            rqe.setSingleDayBurnedCalories(r.getCaloriesburned());
            //remainingCalorie = (consumed - burned) - goal
            rqe.setSingleDayRemainingCalorie((r.getCaloriesconsumed() - r.getCaloriesburned()) - r.getClaoriegoal());
        }
        return rqe;
    }
    
    /*New method for task5b*/
    @GET
    @Path("createReport2/{userid}/{startDate}/{endDate}")
    @Produces({"application/json"})
    public ReportQueryEntity createReport2 (@PathParam("userid") Long userid, @PathParam("startDate") Date startDate, @PathParam("endDate") Date endDate){
        ReportQueryEntity rqe = new ReportQueryEntity();
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.reportdate BETWEEN :startDate AND :endDate", Report.class);
        query.setParameter("userid", userid);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        List<Report> tempList = query.getResultList();
        System.out.print(tempList.size());
        double totalCaloriesBurned = 0.0;
        double totalCaloriesConsumed = 0.0;
        int totalStepsTaken = 0;
        for(Report r : tempList){
            totalCaloriesBurned += r.getCaloriesburned();
            totalCaloriesConsumed += r.getCaloriesconsumed();
            totalStepsTaken += r.getTotalstepsnum();
        }
        rqe.setUser(tempList.get(0).getUserid());
        rqe.setTotalBurnedCalories(totalCaloriesBurned);
        rqe.setTotalConsumedCalories(totalCaloriesConsumed);
        rqe.setTotalStepNum(totalStepsTaken);
        return rqe;
    }
    
    @GET
    @Path("findByUseridAndDate/{userid}/{reportdate}")
    @Produces({"application/json"})
    public List<Report> findByUseridAndDate(@PathParam("userid")Long userid, @PathParam("reportdate") Date reportdate){
        TypedQuery<Report> query = em.createQuery("SELECT r FROM Report r WHERE r.userid.userid = :userid AND r.reportdate = :reportdate", Report.class);
        query.setParameter("userid", userid);
        query.setParameter("reportdate", reportdate);
        return query.getResultList();
    }
    
}
