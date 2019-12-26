package project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


import project.data.MARDAO;
import project.data.UserDAO;
import project.model.Facility;
import project.model.MAR;
import project.model.MARErrorMsgs;
import project.model.User;
import project.model.UserErrorMsgs;


@WebServlet("/MARController")
public class MARController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MARDAO dao = new MARDAO();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("reportMar") )
		{
			 try {
		        	

		            List<Facility> listFacility = dao.list();
		          
		            
		            session.setAttribute("listFacility", listFacility);
		          
		            
		            getServletContext().getRequestDispatcher("/ReportMAR.jsp").forward(request, response);
		 
		 
		        } catch (SQLException e) {
		            e.printStackTrace();
		            throw new ServletException(e);
		        }
		
				
		}
		
		  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action"), url="";
		if(action.equalsIgnoreCase("addMar"))
		{
			HttpSession session = request.getSession();
			MAR MARForm = new MAR();
			
		
			Date date = new Date();  
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");  
		    String strDate = formatter.format(date);  
	
		    User login = (User)request.getSession().getAttribute("login");

		    String username = login.getUserName();
		
		    
			String id = MARDAO.getUTAID(username);
			
			MARForm.setMar(null, request.getParameter("facilitytype"),
					request.getParameter("facilityname"),
					request.getParameter("desc"), 
					strDate,id,null,null,null,
					request.getParameter("urgency"));

			MARErrorMsgs errorMsgs = new MARErrorMsgs();

			session.setAttribute("errorMsgs", errorMsgs);
			
			String ret = MARForm.validateDescription(MARForm,errorMsgs);
			errorMsgs.setDesc(ret);

			
			if (errorMsgs.getDesc().equals("")) {
				
				MARForm.setMar(
						null, request.getParameter("facilitytype"),
						request.getParameter("facilityname"),
						request.getParameter("desc"), 
						strDate,id,null,null,null,
						request.getParameter("urgency"));
				System.out.println("desc=" + request.getParameter("desc"));

				
				int res = MARDAO.insertNewMAR(MARForm);
					
				getServletContext().getRequestDispatcher("/user_success.jsp").forward(request, response);

				
				session.setAttribute("facilityAddForm",MARForm );

				
				session.removeAttribute("errorMsgs");
			} else {

				getServletContext().getRequestDispatcher("/ReportMAR.jsp").forward(request, response);
				session.removeAttribute("errorMsgs");

			}
			
			
			
			
		
				
			
			
			
		}
	}
	
}
