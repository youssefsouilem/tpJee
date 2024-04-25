package mvcController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mvcModel.StudentService;
import mvcModel.SubjectService;
import mvcModel.TeacherServic;
import entities.Student;
import entities.Subject;

public class Controller extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private SubjectService SubjectService;
	@EJB
	private TeacherServic TeacherService;
	@EJB
	private StudentService StudentService;

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Subject> subjects=SubjectService.getAllSujects();
		request.setAttribute("subjects", subjects);
		StringBuilder sb = new StringBuilder();
		for(Subject subject:subjects) {
			 String subjectTitle = subject.getSubjectTitle();
		     String teacherName = subject.getTeacher().getName(); 
		        
		     String subjectInfo = subjectTitle + "-" + teacherName;
		     sb.append(subjectInfo).append("<br>");
		    }
    	String buttonValue = request.getParameter("myBtn");
        System.out.println("Servlet Accessed");
        if (buttonValue != null && buttonValue.equals("Rechercher Matieres")) {
        	System.out.println("Case Rechercher Matieres");
            String title = request.getParameter("intitule");
            String affiliation = request.getParameter("affiliation");

            List<Subject> subjects1 = SubjectService.getAllSujectsByTitleAndAffiliation(title, affiliation);
            System.out.println("List size :"+subjects1.size());
            for (Subject subject : subjects1) {
                response.getWriter().println("Matiere: " + subject.getSubjectTitle() + ", Enseignant: " + subject.getTeacher().getName());
            }
        } else if (buttonValue != null && buttonValue.equals("Ajouter")) {
	        String Studentname = request.getParameter("Id");
	        String password = request.getParameter("pwd");
	        String nom=request.getParameter("name");
	        String mail=request.getParameter("email");
	        String login=request.getParameter("login");

	        Student student = new Student();
	        student.setLogin(login);
	        student.setFirstName(Studentname);
	        student.setPwd(password);
	        student.setName(nom);
	        student.setEmail(mail);

	        StudentService.addStudent(student);

	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<html><body>");
	        out.println("<h2>Utilisateur ajouté avec succès !</h2>");
	        out.println("</body></html>");
	        }
		        else if (buttonValue != null && buttonValue.equals("Login")) {
		        	System.out.println("Login Use case");
			        String Studentname = request.getParameter("Studentname");
			        String password = request.getParameter("password");
			        Student student =new Student();
			        student = StudentService.getStudentByLoginAndPassword(Studentname, password);

			        if (student != null) {
			        	 HttpSession session = request.getSession(true);
			            session = request.getSession(true);
			            session.setMaxInactiveInterval(300); // 5 minutes en secondes
			            
			            session.setAttribute("activeStudent", student);
			            response.sendRedirect("rechercheSubject.jsp");
			        } else {
			        	System.out.println("case Student not found");
			        	request.setAttribute("erreur", "Login et / ou mot de passe incorrecte(s)");
			            request.getRequestDispatcher("index.jsp").forward(request, response);
			        }


			        }
                }	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		doGet(request,response);
	}

}
