package project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.data.UserDAO;
import project.model.UserErrorMsgs;
import project.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDAO dao = new UserDAO();
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
	
		if (request.getParameter("btnLogout") != null) {
			try {
				session.removeAttribute("login");
				session.removeAttribute("errorMsgs");

				session.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}

		else if (action.equalsIgnoreCase("searchByUsername")) {
			ArrayList<User>usersInDB = new ArrayList<User>();
			String un = request.getParameter("username");
			usersInDB=UserDAO.getUserRoleUsername(un);
			session.setAttribute("user", usersInDB);
			if(usersInDB.size()==0)
			{
				UserErrorMsgs em = new UserErrorMsgs();
				em.setUserNameError("no one present with the user name '"+un+"'!");
				session.setAttribute("em", em);
				getServletContext().getRequestDispatcher("/admin_search.jsp").forward(request,response);
				session.removeAttribute("user");
				session.removeAttribute("em");
			}
			else {
			getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request,response);
			session.removeAttribute("user");
			}
		}
		
	
			
		
		
		else if (action.equalsIgnoreCase("searchByRole")) {
			ArrayList<User>usersInDB = new ArrayList<User>();
			String type = request.getParameter("role");
			
			usersInDB=UserDAO.getUserRoleType(type);
			session.setAttribute("user", usersInDB);
			if(usersInDB.size()==0)
			{
				UserErrorMsgs em = new UserErrorMsgs();
				em.setUserNameError("no one present with the role type '"+type+"'!");
				session.setAttribute("em", em);
				getServletContext().getRequestDispatcher("/admin_search.jsp").forward(request,response);
				session.removeAttribute("user");
				session.removeAttribute("em");
			}
			else {
			getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request,response);
			session.removeAttribute("user");
			}
			
		}
		
		
		else if(action.equalsIgnoreCase("updateProfile") )
		{

		
			User login = (User)request.getSession().getAttribute("login");

			String username = login.getUserName();
		

			List<User> User = null;
			try {
				User = dao.list(username);
		

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("User", User);





			getServletContext().getRequestDispatcher("/UpdateProfile.jsp").forward(request, response);


		}
		
		else if(action.equalsIgnoreCase("updateProfileu") )
		{

		

			User login = (User)request.getSession().getAttribute("login");

			String username = login.getUserName();
		

			List<User> User = null;
			try {
				User = dao.list(username);
		

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("User", User);





			getServletContext().getRequestDispatcher("/UpdateProfileUser.jsp").forward(request, response);


		}
		else if(action.equalsIgnoreCase("updateProfilea") )
		{

		

			User login = (User)request.getSession().getAttribute("login");

			String username = login.getUserName();
		

			List<User> User = null;
			try {
				User = dao.list(username);
		

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("User", User);





			getServletContext().getRequestDispatcher("/UpdateProfileAdmin.jsp").forward(request, response);


		}
		else if(action.equalsIgnoreCase("modifyProfile") )
		{

		
				
			//User login = (User)request.getSession().getAttribute("login");

			String username = request.getParameter("username");
		

			List<User> User = null;
			try {
				User = dao.list(username);
		

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("User", User);





			getServletContext().getRequestDispatcher("/modifyProfileAdmin.jsp").forward(request, response);


		}
		else if(action.equalsIgnoreCase("updateProfiler") )
		{

		

			User login = (User)request.getSession().getAttribute("login");

			String username = login.getUserName();
		

			List<User> User = null;
			try {
				User = dao.list(username);
		

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			session.setAttribute("User", User);





			getServletContext().getRequestDispatcher("/UpdateProfileRep.jsp").forward(request, response);


		}
		else if(action.equalsIgnoreCase("listRoles"))
		{
			ArrayList<User>usersInDB = new ArrayList<User>();
			usersInDB=UserDAO.getUserRole();
			session.setAttribute("user", usersInDB);
			getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request,response);
			session.removeAttribute("user");
		}
		
		else 	if(action.equalsIgnoreCase("logout"))
		{
			session.invalidate();
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		// String message = null;
		String sessionID = null;
		String action = request.getParameter("action"), url = "";
		if (action.equalsIgnoreCase("Login")) {
			User login = new User();
			if (request.getParameter("btnLogin") != null) {
				login.setUserName(request.getParameter("userName"));
				login.setPassword(request.getParameter("password"));
				UserErrorMsgs errorMsgs = new UserErrorMsgs();
				login.validateUserNamePassword(login, errorMsgs);
				String roleType = UserDAO.getRoleTypeFromDb(login.getUserName());
				//String roleType = login.getRoleType(login);
				String utid = UserDAO.getIDFromDb(login.getUserName());
				//String utid = login.getID(login);
				login.setRoleType(roleType);
				login.setUtaID(utid);
				String userName = login.getUserName();
				login.setUserName(userName);
				session.setAttribute("login", login);
				session.setAttribute("errorMsgs", errorMsgs);
				HttpSession newSession = request.getSession(true);
				newSession.setMaxInactiveInterval(120 * 180);

				if (errorMsgs.getUserNamePasswordError().equals("")) {
						session.removeAttribute("errorMsgs");
					if (roleType.equals("Student")) {
						getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);

					} else if (roleType.equals("Faculty")) {
						getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);

					} else if (roleType.equals("Facility Manager")) {
						getServletContext().getRequestDispatcher("/facilityManager.jsp").forward(request, response);

					} else if (roleType.equalsIgnoreCase("Repairer")) {
				
						
						getServletContext().getRequestDispatcher("/repairer.jsp").forward(request, response);
						

					} else if (roleType.equalsIgnoreCase("Admin")) {
						getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
					}

				} else {
					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
					session.removeAttribute("errorMsgs");
				}
			}
		}
	
		else if (action.equalsIgnoreCase("Register")) {
			HttpSession sessionone = request.getSession();
			User register = new User();
			if (request.getParameter("btnRegister") != null) {
				UserErrorMsgs errorMsgs = new UserErrorMsgs();
				register.setUtaID(request.getParameter("utaid"));
				register.validateUtaId(register, errorMsgs);
				register.setUserName(request.getParameter("userName"));
				register.validateUserName(register, errorMsgs);
				register.setPassword(request.getParameter("password"));
				register.validatePassword(register, errorMsgs);
				register.setCpassword(request.getParameter("cpassword"));
				register.validateCpassword(register, errorMsgs);
				register.setLastName(request.getParameter("lname"));
				register.validateLastName(register, errorMsgs);
				register.setFirstName(request.getParameter("fname"));
				register.validateFirstName(register, errorMsgs);
				register.setRoleType(request.getParameter("roleType"));
				register.setMobile(request.getParameter("mobile"));
				register.validateMobile(register, errorMsgs);
				register.setAddress(request.getParameter("address"));
				register.setSecurityans(request.getParameter("securityans"));
				sessionone.setAttribute("register", register);
				sessionone.setAttribute("errorMsgs", errorMsgs);
				if (errorMsgs.getLnameError().equals("") && errorMsgs.getFnameError().equals("")
						&& errorMsgs.getUtaIDError().equals("") && errorMsgs.getPasswordError().equals("")
						&& errorMsgs.getCpasswordError().equals("") && errorMsgs.getUserNameError().equals("")
						&& errorMsgs.getMobileError().equals("")) {
					UserDAO.insertUser(register);
					sessionone.removeAttribute("register");
					sessionone.removeAttribute("errorMsgs");
					getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
				} else {

					getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
					sessionone.removeAttribute("errorMsgs");

				}
			}
		}
		
		else if(action.equalsIgnoreCase("assignRoles"))
		{		
		User user = new User();
		user.setUtaID((request.getParameter("uta_id")));
		user.setRoleType(request.getParameter("role"));
		UserDAO.updateRole(user);
		getServletContext().getRequestDispatcher("/admin_success.jsp").forward(request,response);	
		}	
		
		else if (action.equalsIgnoreCase("Reset")) {
			User resPass = new User();
		
			HttpSession sessiontwo = request.getSession();
			UserErrorMsgs resetPassErrMsgs = new UserErrorMsgs();
			if (request.getParameter("btnReset") != null) {
				resPass.setUtaID(request.getParameter("utaid"));
				resPass.setSecurityans(request.getParameter("securityquestion"));
				resPass.setPassword(request.getParameter("password"));
				resPass.setCpassword(request.getParameter("cpassword"));
				resPass.validateForPasswordReset(resPass, resetPassErrMsgs);
				sessiontwo.setAttribute("errorMsgs", resetPassErrMsgs);
				System.out.println(resPass.getUtaID()+" "+resetPassErrMsgs.getResetPasswordError());
				if (resetPassErrMsgs.getResetPasswordError().equals("")) {
					UserDAO.resetPassword(resPass);
				} else {
					getServletContext().getRequestDispatcher("/forgotpswd.jsp").forward(request, response);
				}
			}
		}
		
		
	
		
		
		else if(action.equalsIgnoreCase("modifyProfile"))
		{



			User updateUser = new User();

			//User login = (User)request.getSession().getAttribute("login");

			String username = request.getParameter("username");
		//	String roleType = request.getParameter("role");
			UserErrorMsgs uErrMsgs = new UserErrorMsgs();
			updateUser.setUtaID(request.getParameter("utaid"));
			updateUser.setPassword(request.getParameter("pwd"));
			updateUser.validatePassword(updateUser, uErrMsgs);
			updateUser.setRoleType(request.getParameter("role"));
			updateUser.setLastName(request.getParameter("lname"));
			updateUser.validateLastName(updateUser, uErrMsgs);

			updateUser.setFirstName(request.getParameter("fname"));
			updateUser.validateFirstName(updateUser, uErrMsgs);

			updateUser.setAddress(request.getParameter("address"));


			session.setAttribute("updateUser", updateUser);
			session.setAttribute("uErrMsgs", uErrMsgs);

			if (uErrMsgs.getPasswordError().equals("")
					&& uErrMsgs.getFnameError().equals("") && uErrMsgs.getLnameError().equals("")) {

				UserDAO.modifyProfile(request.getParameter("pwd"), request.getParameter("fname"), request.getParameter("lname"), request.getParameter("addr"), username, request.getParameter("role"));

				session.removeAttribute("uErrMsgs");

			
					getServletContext().getRequestDispatcher("/admin_success.jsp").forward(request, response);
					session.removeAttribute("updateUser");
				}
			


			 else {

				
				
				getServletContext().getRequestDispatcher("/modifyProfileAdmin.jsp").forward(request, response);
				session.removeAttribute("uErrMsgs");
				session.removeAttribute("updateUser");


			}
			

		}
		
		
		
		
		
		
		
		
		
		
		else if(action.equalsIgnoreCase("updateProfile"))
		{



			User updateUser = new User();

			User login = (User)request.getSession().getAttribute("login");

			String username = login.getUserName();
			String roleType = login.getRoleType();
			UserErrorMsgs uErrMsgs = new UserErrorMsgs();
			updateUser.setUtaID(request.getParameter("utaid"));
			updateUser.setPassword(request.getParameter("pwd"));
			updateUser.validatePassword(updateUser, uErrMsgs);

			updateUser.setLastName(request.getParameter("lname"));
			updateUser.validateLastName(updateUser, uErrMsgs);

			updateUser.setFirstName(request.getParameter("fname"));
			updateUser.validateFirstName(updateUser, uErrMsgs);

			updateUser.setAddress(request.getParameter("address"));


			session.setAttribute("updateUser", updateUser);
			session.setAttribute("uErrMsgs", uErrMsgs);

			if (uErrMsgs.getPasswordError().equals("")
					&& uErrMsgs.getFnameError().equals("") && uErrMsgs.getLnameError().equals("")) {

				UserDAO.updateProfile(request.getParameter("pwd"), request.getParameter("fname"), request.getParameter("lname"), request.getParameter("addr"), username);

				session.removeAttribute("uErrMsgs");

				
				if (roleType.equals("Student")) {
					getServletContext().getRequestDispatcher("/user_success.jsp").forward(request, response);

				} else if (roleType.equals("Faculty")) {
					getServletContext().getRequestDispatcher("/user_success.jsp").forward(request, response);

				} else if (roleType.equals("Facility Manager")) {
					getServletContext().getRequestDispatcher("/fm_success.jsp").forward(request, response);

				} else if (roleType.equalsIgnoreCase("Repairer")) {
			
					
					getServletContext().getRequestDispatcher("/rep_success.jsp").forward(request, response);
					

				} else if (roleType.equalsIgnoreCase("Admin")) {
					getServletContext().getRequestDispatcher("/admin_success.jsp").forward(request, response);
				}
				;


			} else {

				

				getServletContext().getRequestDispatcher("/UpdateProfile.jsp").forward(request, response);
				session.removeAttribute("uErrMsgs");


			}
			session.setAttribute("uErrMsgs", uErrMsgs);

		}
	}
}

