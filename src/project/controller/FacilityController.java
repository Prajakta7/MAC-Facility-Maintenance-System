package project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.data.FacilityDAO;
import project.model.Facility;
import project.model.FacilityErrorMsgs;
import project.model.MAR;
import project.model.MARErrorMsgs;
import project.model.Reservation;
import project.model.User;






@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		session.removeAttribute("errorMsgs");
		if (action.equalsIgnoreCase("listMar")) {
			String msg="";
			ArrayList<MAR> unassignedMarsInDB = new ArrayList<MAR>();
			MARErrorMsgs mr = new MARErrorMsgs(); 
			unassignedMarsInDB=FacilityDAO.getUnassignedMars();
			if(unassignedMarsInDB.isEmpty())
			{
				//msg="No Unassigned MARS left!";
				//mr.setMsg(msg);
				session.setAttribute("msg", mr);
			}
			else {
			session.setAttribute("mars", unassignedMarsInDB);
			}
			getServletContext().getRequestDispatcher("/list_mar.jsp").forward(request, response);
			session.removeAttribute("msg");
			session.removeAttribute("mars");
		}
	
		
		else if(action.equalsIgnoreCase("assignMar"))
		{
			ArrayList<MAR> marInDB = new ArrayList<MAR>();
			ArrayList<User> repairerInDB = new ArrayList<User>();
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			
			repairerInDB = FacilityDAO.searchUser("repairer");
			facilityInDB = FacilityDAO.getFacilityBy("facility_type, facility_name");
			
			MAR selectedMar = new MAR();
			marInDB=FacilityDAO.searchMar(request.getParameter("id"));
			selectedMar.setMar(	marInDB.get(0).getMar_number(), marInDB.get(0).getFacility_type(), 
					marInDB.get(0).getFacility_name(), marInDB.get(0).getDesc(),marInDB.get(0).getDate(),
					marInDB.get(0).getReported_by(), 
					marInDB.get(0).getAssigned_to(), marInDB.get(0).getAssigned_date(),marInDB.get(0).getEstimate_of_repair(),marInDB.get(0).getUrgency());
			session.setAttribute("facility",facilityInDB );
			
			session.setAttribute("mar", selectedMar);
			session.setAttribute("user", repairerInDB);
			
			getServletContext().getRequestDispatcher("/assign_mar.jsp").forward(request, response);
			session.removeAttribute("facility");
			session.removeAttribute("mar");
			session.removeAttribute("user");
		}
		
		else if(action.equalsIgnoreCase("facilityDetails"))
		{
			getServletContext().getRequestDispatcher("/facility_detail.jsp").forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("failityNumber"))
		{
			getServletContext().getRequestDispatcher("/facility_number.jsp").forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("reparierSchedule"))
		{
			getServletContext().getRequestDispatcher("/repairer_schedule.jsp").forward(request, response);
		}
		
		else if(action.equalsIgnoreCase("viewbydate")) 
		{	
			MAR mar = new MAR();
			MARErrorMsgs errorMsgs = new MARErrorMsgs();
			String str3 = request.getParameter("date");
			mar.setAssigned_date(str3);
			try {
				String res = mar.isValidDate(mar, errorMsgs);
				errorMsgs.setAssigned_DateErrorr(res);
				errorMsgs.setErrorMsg();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((errorMsgs.getErrorMsg().equalsIgnoreCase("")))
			{
			ArrayList<MAR> marsInDB = new ArrayList<MAR>();
			marsInDB=FacilityDAO.getMarsD(mar.getAssigned_date());
			session.setAttribute("mars", marsInDB);				
			getServletContext().getRequestDispatcher("/view_mars.jsp").forward(request, response);
			session.removeAttribute("mars");
			}
			else {
				session.setAttribute("errorMsgs", errorMsgs);
				session.setAttribute("mar", mar);
				getServletContext().getRequestDispatcher("/view_mars_date.jsp").forward(request, response);
				session.removeAttribute("mars");
				session.removeAttribute("errorMsgs");
				
				
			}

		}
		
		else if(action.equalsIgnoreCase("viewbydateun")) 
		{	
			MAR mar = new MAR();
			MARErrorMsgs errorMsgs = new MARErrorMsgs();
			String str3 = request.getParameter("date");
			mar.setAssigned_date(str3);
			try {
				String res = mar.isValidDate(mar, errorMsgs);
				errorMsgs.setAssigned_DateErrorr(res);
				errorMsgs.setErrorMsg();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if((errorMsgs.getErrorMsg().equalsIgnoreCase("")))
			{
			ArrayList<MAR> marsInDB = new ArrayList<MAR>();
			marsInDB=FacilityDAO.getMarsU(mar.getAssigned_date());
			session.setAttribute("mars", marsInDB);				
			getServletContext().getRequestDispatcher("/view_mars.jsp").forward(request, response);
			session.removeAttribute("mars");
			}
			else {
				session.setAttribute("errorMsgs", errorMsgs);
				session.setAttribute("mar", mar);
				getServletContext().getRequestDispatcher("/view_unmars_date.jsp").forward(request, response);
				session.removeAttribute("mars");
				session.removeAttribute("errorMsgs");
				
				
			}

		}
		else if (action.equalsIgnoreCase("ViewMar")) {
			ArrayList<MAR> marsInDB = new ArrayList<MAR>();
			marsInDB=FacilityDAO.getMars();
			session.setAttribute("mars", marsInDB);				
			getServletContext().getRequestDispatcher("/view_mars.jsp").forward(request, response);
			session.removeAttribute("mars");
		}
		else if(action.equalsIgnoreCase("repairerSchedule"))
        {
	        try {
	        	FacilityDAO dao = new FacilityDAO();
	            List<User> listRepairer = dao.list();
	            
	            session.setAttribute("listRepairer", listRepairer);
	            //request.setAttribute("listRepairer", listRepairer);
	            
	            getServletContext().getRequestDispatcher("/view.jsp").forward(request, response);
	            session.removeAttribute("listRepairer");
	            //RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
	            //dispatcher.forward(request, response);
	 
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new ServletException(e);
	        }
    }
        else if (action.equalsIgnoreCase("numberOfFacilities"))
        {
        	try {
        		FacilityDAO dao = new FacilityDAO();
        	
				List<Facility> typeDropdown = dao.getTypeDropdown();
				List<Facility> nameDropdown = dao.getNameDropdown();
				List<Facility> venueDropdown = dao.getVenueDropdown();
				
				session.setAttribute("typeDropdown", typeDropdown);
				session.setAttribute("nameDropdown", nameDropdown);
				session.setAttribute("venueDropdown", venueDropdown);
				
				getServletContext().getRequestDispatcher("/facilityNumbers.jsp").forward(request, response);
				session.removeAttribute("typeDropdown");
				session.removeAttribute("nameDropdown");
				session.removeAttribute("venueDropdown");
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(action.equalsIgnoreCase("facilitynameDrop"))
        {
        	try {
        		FacilityDAO dao = new FacilityDAO();
        		List<Facility> nameDropdown = dao.getNameDropdown();
				
				session.setAttribute("nameDropdown", nameDropdown);
				
				getServletContext().getRequestDispatcher("/facilityDetails.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	
		else // redirect all other gets to post
			doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("assign_mar"))
		{
			ArrayList<User> repairerInDB = new ArrayList<User>();
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			
			repairerInDB = FacilityDAO.searchUser("repairer");
			facilityInDB = FacilityDAO.getFacilityBy("facility_type, facility_name");
			MAR mar = new MAR();
			MARErrorMsgs errorMsgs = new MARErrorMsgs();			
			String assigned_to = request.getParameter("assigned_to");
			String assigned_date = request.getParameter("assigned_date");
			String estimate_of_repair = request.getParameter("estimate"); 
			String mar_number = request.getParameter("mar_number");
			String facility_type = request.getParameter("facility_type");
			String facility_name = request.getParameter("facility_name");
			String urgency = request.getParameter("urgency");
			String desc = request.getParameter("desc");
			String date1 =  request.getParameter("date");
			System.out.println("fac controller dt:"+date1);
			String reported_by = request.getParameter("reported_by");
			mar.setMar(mar_number,facility_type,facility_name,desc,date1,reported_by,assigned_to, assigned_date,estimate_of_repair,urgency);
			try {
				mar.validateMarAssignment(mar, errorMsgs);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("facility",facilityInDB );
			session.setAttribute("user", repairerInDB);
			session.setAttribute("errorMsgs", errorMsgs);
			session.setAttribute("mar", mar);
			if(!(errorMsgs.getErrorMsg()==""))
			{
				getServletContext().getRequestDispatcher("/assign_mar.jsp").forward(request, response);
				session.removeAttribute("errorMsgs");
			}
			else {
				
				FacilityDAO.updateMar(mar);
				session.removeAttribute("facility");
				session.removeAttribute("mar");
				session.removeAttribute("user");
				getServletContext().getRequestDispatcher("/facilityManager.jsp").forward(request, response);
			
			}
			
		}
		else if (action.equalsIgnoreCase("facility_add"))
		{
		Facility facilityAddForm = new Facility();
		FacilityErrorMsgs fErrorMsgs = new FacilityErrorMsgs();
		facilityAddForm.setFacility(request.getParameter("facility_name"),
				request.getParameter("facility_type"),
				request.getParameter("interval"),
				request.getParameter("duration"), 
				request.getParameter("venue"));
		
		facilityAddForm.validateFacility(facilityAddForm, fErrorMsgs);
		session.setAttribute("facility", facilityAddForm);
		session.setAttribute("errorMsgs", fErrorMsgs);
		if(!(fErrorMsgs.getErrorMsg()==""))
		{
			getServletContext().getRequestDispatcher("/facility_add.jsp").forward(request, response);
			session.removeAttribute("errorMsgs");
			session.removeAttribute("facility");
			
		}
		else {
		FacilityDAO.insertNewFacility(facilityAddForm);
		session.removeAttribute("errorMsgs");
		session.removeAttribute("facility");
		getServletContext().getRequestDispatcher("/facilityManager.jsp").forward(request, response);		
	
		}
		}
		
	
	else if(action.equalsIgnoreCase("view"))
	{
    	
        String rep_id = request.getParameter("repairer");
        List<Reservation> repairerSchedule = new ArrayList<Reservation>();
        FacilityDAO dao = new FacilityDAO();
		try {
			repairerSchedule = dao.getRepairerSchedule(rep_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		session.setAttribute("repairerSchedule", repairerSchedule);				
		getServletContext().getRequestDispatcher("/repairerSchedule.jsp").forward(request, response);
		session.removeAttribute("repairerSchedule");
    }
	else if(action.equalsIgnoreCase("facilityNumbers"))
	{
		
        String result = "";
        String facility_type = request.getParameter("facilityType");
        String facility_name = "test";
        String date = request.getParameter("date");
        String timeslot = request.getParameter("timeslot");
        MAR mar = new MAR();
        MARErrorMsgs errorMsgs = new MARErrorMsgs();
		
		mar.setAssigned_date(date);
		try {
			String res = mar.isValidDate(mar, errorMsgs);
			errorMsgs.setAssigned_DateErrorr(res);
			errorMsgs.setErrorMsg();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
        FacilityDAO dao = new FacilityDAO();
        try {
			result = dao.getFacilityNumbers(facility_type, facility_name, date, timeslot);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
		
		
		
		if((errorMsgs.getErrorMsg().equalsIgnoreCase("")))
		{
			  session.setAttribute("facilityNumbers", result);				
				getServletContext().getRequestDispatcher("/facilityNumbers.jsp").forward(request, response);
				session.removeAttribute("facilityNumbers");
		}
		else {
			session.setAttribute("errorMsgs", errorMsgs);
			
			getServletContext().getRequestDispatcher("/facilityNumbers.jsp").forward(request, response);
			
			session.removeAttribute("errorMsgs");
			
			
		}
		
		
		
		
        
	}
	else if(action.equalsIgnoreCase("facilityDetails"))
	{
		FacilityDAO dao = new FacilityDAO();
        List<Facility>  result = null;
        String facility_name = request.getParameter("facilityname");
        try {
			result = dao.getFacilityDetails(facility_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        session.setAttribute("facilityDetails", result);
        int i=0;
		getServletContext().getRequestDispatcher("/facilityDetails.jsp").forward(request, response);
		session.removeAttribute("facilityDetails");
	}
}}
