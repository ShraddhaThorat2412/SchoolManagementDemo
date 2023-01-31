package entity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Entity
public class AddStudent extends HttpServlet{
 @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String id=req.getParameter("id");
	String name=req.getParameter("name");
	String stream=req.getParameter("stream");
	String fees=req.getParameter("fees");
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("reshma");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	int id1=Integer.parseInt(id);
	double fees1=Double.parseDouble(fees);
	
	Student s=new Student();
	s.setId(id1);
	s.setName(name);
	s.setStream(stream);
	s.setFees(fees1);
	
	et.begin();
	em.persist(s);
	et.commit();
	
	resp.setContentType("text/html");
	PrintWriter pw=resp.getWriter();
	pw.write("Student added successfully");
	
	RequestDispatcher rd=req.getRequestDispatcher("Student.html");
	rd.forward(req, resp);
	
	
	
	 
 }
	
	
}
