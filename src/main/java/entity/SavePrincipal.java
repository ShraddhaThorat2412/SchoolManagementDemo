package entity;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveprinci")
public class SavePrincipal extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name=req.getParameter("name");
	long phoneno=Long.parseLong(req.getParameter("phoneno"));
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("krupal");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	Principal p=new Principal();
	p.setName(name);
	p.setPhone_no(phoneno);
	p.setEmail(email);
	p.setPassword(password);
	
	et.begin();
	em.persist(p);
	et.commit();
	
	RequestDispatcher rd=req.getRequestDispatcher("login.html");
	rd.include(req, resp);
	resp.setContentType("text/html");



}
}
