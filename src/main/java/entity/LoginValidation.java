package entity;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginValidate")
public class LoginValidation extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	
	
	PrintWriter pw=resp.getWriter();
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("reshma");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	Query qr=em.createQuery("Select a from user a where a.email=?1 and a.password=?2");
	
	qr.setParameter(1, email);
	qr.setParameter(2, password);
	
	List<Principal> principal=qr.getResultList();
	if(principal.size()>0)
	{
		pw.write("welcome to school");
		RequestDispatcher rd=req.getRequestDispatcher("Main.html");
		rd.include(req,resp);
		resp.setContentType("text/html");
	}
	else
	{
		RequestDispatcher rd=req.getRequestDispatcher("Login.html");
		resp.setContentType("text/html");
		pw.write("invalid credientials");
		rd.include(req, resp);
		
	}
	
	}
}
