package project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.data.FacilityDAO;
import project.data.ReservationDAO;
import project.data.UserDAO;
import project.model.MAR;
import project.model.MARErrorMsgs;
import project.model.Reservation;
import project.model.ReservationErrorMsgs;
import project.model.User;


@WebServlet("/ReservationController")
public class ReservationController extends HttpServlet {
               private static final long serialVersionUID = 1L;
               protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               ReservationDAO dao = new ReservationDAO();
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if(action.equalsIgnoreCase("repairerSchedule"))
        {
                     
                
                           User repairer = new User();
                           repairer = (User)request.getSession().getAttribute("login");

                                                            
                                                            
                                                            String uta_id = repairer.getUtaID();
                                                            
                                                            ArrayList<MAR> assignedMarsInDB = new ArrayList<MAR>();
                                                            MARErrorMsgs mr = new MARErrorMsgs(); 
                                                            assignedMarsInDB=FacilityDAO.getAssignedMars(uta_id);
                                                            
                                                            
                                                            session.setAttribute("marsrep", assignedMarsInDB);
                                                            
                                                            //getServletContext().getRequestDispatcher("/list_mar.jsp").forward(request, response);
                                             
                     
                           
                                                            
                                                             getServletContext().getRequestDispatcher("/schedule.jsp").forward(request, response); 
                                                             session.removeAttribute("marsrep");
                              
                  
                      
    }
        
        else  if(action.equalsIgnoreCase("View/ModifyRepair"))
        {
                     
                
            User repairer = new User();
            repairer = (User)request.getSession().getAttribute("login");

                                             
                                             
                                             String uta_id = repairer.getUtaID();
                                             
                                             ArrayList<Reservation> assignedReservationsInDB = new ArrayList<Reservation>();
                                             
                                             assignedReservationsInDB=ReservationDAO.getAssignedreservations(uta_id);
                                             
                                             
                                             session.setAttribute("marsrep1", assignedReservationsInDB);
                                             
                                             //getServletContext().getRequestDispatcher("/list_mar.jsp").forward(request, response);
                              
      
            
                                             
                                              getServletContext().getRequestDispatcher("/reservations.jsp").forward(request, response); 
                                              session.removeAttribute("marsrep1");
               
   
       
}
        
        
        else if(action.equalsIgnoreCase("reserveMar"))
    {
              User repairer = new User();
        repairer = (User)request.getSession().getAttribute("login");
       
                              
                              
                              String uta_id = repairer.getUtaID();
               String fn = request.getParameter("fn");
                              String id = request.getParameter("id");
                              String date =request.getParameter("adate");
                              String ft = request.getParameter("ft");
        

                              Reservation res = new Reservation();
                              res.setRepairer_id(uta_id);
                              res.setDate(date);
                              res.setMar_id(id);
                              res.setFacility_name(fn);
                              res.setFacility_type(ft);
                              
                              
               
  
       String check =  dao.checkReservation(res);
                              
       if(check.equalsIgnoreCase("notThere"))
       {
                  session.setAttribute("res", res);
                              getServletContext().getRequestDispatcher("/reserve.jsp").forward(request, response); 
                               session.removeAttribute("res");
       }
       else {
                  getServletContext().getRequestDispatcher("/failure_reserve.jsp").forward(request, response); 
       }

   
}
        
        else if(action.equalsIgnoreCase("modifyReservation"))
    {
               User repairer = new User();
        repairer = (User)request.getSession().getAttribute("login");

                              
                              
                              String uta_id = repairer.getUtaID();
               String fn = request.getParameter("fn");
                              String id = request.getParameter("id");
                              String marid = request.getParameter("marid");
                              String date =request.getParameter("adate");
                              String timeslot = request.getParameter("timeslot");
        
                              
                              Reservation res = new Reservation();
                              res.setRepairer_id(uta_id);
                              res.setDate(date);
                              res.setMar_id(marid);
                              res.setFacility_name(fn);
                              res.setReservation_id(id);
                              res.setTimeslot(timeslot);
                              
                              
                              
                              
                              
                              
               session.setAttribute("res", res);
                  getServletContext().getRequestDispatcher("/reservation.jsp").forward(request, response); 
      
                  session.removeAttribute("res");
   
    }
        
        else if(action.equalsIgnoreCase("cancel"))
    {
               

               ReservationErrorMsgs resEr = new ReservationErrorMsgs();
                              
                              String id = request.getParameter("id");
                              String date = request.getParameter("date");
        
                             ;
                              String fn = request.getParameter("fn");
                              String mar_id = request.getParameter("marid");
                             
                
                String timeslot = request.getParameter("timeslot");
                
                Reservation res = new Reservation();
               
                res.setDate(date);
                res.setFacility_name(fn);
                res.setMar_id(mar_id);
                
                res.setTimeslot(timeslot);
                res.setReservation_id(id);
                              
                Date date1=null;
                              try {
                              date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
                              } catch (Exception e) {
                                             // TODO Auto-generated catch block
                                             e.printStackTrace();
                              } 
                              
                              DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                              Date date2 = new Date();
                              //dateFormat.format(date2);
                              
                              
                                 if (date2.after(date1) && (!(date2.compareTo(date1) == 0))) { 
                              
                                	 			resEr.setTimeslotError("");
                                	 			resEr.setDateError("Past date");
                                               // System.out.println("here");
                                                resEr.setErrorMsg("Past Reservations cannot be cacelled");
                                                request.setAttribute("res", res); 
                                             request.setAttribute("resEr", resEr);
                                             getServletContext().getRequestDispatcher("/reservation.jsp").forward(request, response); 
                                             request.removeAttribute("resER");
                                             request.removeAttribute("res");
                              
                       } 
                 
                                 else {
               
                              
                              res.setReservation_id(id);
                              
                              
                              dao.cancelReservation(res);
                              
                              
                              
                              
                              getServletContext().getRequestDispatcher("/reserve_success.jsp").forward(request, response); 
   
    }
    }
       
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               boolean flag = false;
               String action = (String) request.getParameter("action"), url="";
               ReservationDAO dao = new ReservationDAO();
               if(action.equalsIgnoreCase("reserve"))
               {
                              String fn = request.getParameter("fn");
                              String mar_id = request.getParameter("marid");
                              String date =request.getParameter("adate");
                String rep_id = request.getParameter("repid");
                String timeslot = request.getParameter("timeslot");
                String ft = request.getParameter("ft");
                
                Reservation res = new Reservation();
                ReservationErrorMsgs resEr = new ReservationErrorMsgs();
                
                res.setDate(date);
                res.setFacility_name(fn);
                res.setMar_id(mar_id);
                res.setRepairer_id(rep_id);
                res.setTimeslot(timeslot);
                res.setFacility_type(ft);
      
               int k = dao.check(res);
  
       
               if(k==0 )
               {
                dao.insertReservation(res);
               getServletContext().getRequestDispatcher("/reserve_success.jsp").forward(request, response); 
               }
               else
               {
                              resEr.setErrorMsg("Reservation Cannot be done, already another reservation present for the same time!");
                              request.setAttribute("res", res);
                              request.setAttribute("resEr", resEr);
                              getServletContext().getRequestDispatcher("/reserve.jsp").forward(request, response); 
                              request.removeAttribute("resER");
                              request.removeAttribute("res");
               }
                
                               
      
               
               
               
               }
               if(action.equalsIgnoreCase("modify"))
               {
                              boolean flag1=false;
                              String id = request.getParameter("reservation_id");
                              String fn = request.getParameter("fn");
                              String mar_id = request.getParameter("marid");
                              String date =request.getParameter("adate");
                String rep_id = request.getParameter("repid");
                String timeslot = request.getParameter("timeslot");
                
                Reservation res = new Reservation();
                ReservationErrorMsgs resEr = new ReservationErrorMsgs();
                res.setDate(date);
                res.setFacility_name(fn);
                res.setMar_id(mar_id);
                res.setRepairer_id(rep_id);
                res.setTimeslot(timeslot);
                res.setReservation_id(id);
                
       //    System.out.println(res.getDate()+"" +res.getFacility_name()+""+ res.getMar_id()+""+ res.getRepairer_id()+""+res.getTimeslot());
               
               
               int k = dao.check(res);
               System.out.println("res:"+k);
               Date date1=null;
               try {
               date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
               } catch (Exception e) {
                              // TODO Auto-generated catch block
                              e.printStackTrace();
               } 
               
               DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
               Date date2 = new Date();
               //dateFormat.format(date2);
               
               
                  if (date2.after(date1) && (!(date2.compareTo(date1) == 0))) { 
               
                	  flag1=false;
        } 
  
                  else {

                	  flag1=true;

}

               if(k==0 && flag1)
               {
                dao.modifyReservation(res);
               getServletContext().getRequestDispatcher("/reserve_success.jsp").forward(request, response);
               }
               else
               {
                              if(!flag1)
                              {
                            	  resEr.setTimeslotError("");
                              resEr.setDateError("past date");
                              resEr.setErrorMsg("Presvious reservation can not be modified");
                              }
                              else
                              {
                            	  resEr.setTimeslotError("timeslot error"); 
                                  resEr.setDateError("");
                              resEr.setErrorMsg("Reservation Cannot be modified, already another reservation present for the same time!");
                              }
                              request.setAttribute("res", res);
                              request.setAttribute("resEr", resEr);
                              getServletContext().getRequestDispatcher("/reservation.jsp").forward(request, response); 
                              request.removeAttribute("resER");
                              request.removeAttribute("res");
               }
                
                 
                               
      
               
               
               
               }
               
    }
    
               
               
               
}
