package mvcModel;
import java.util.ArrayList;
import java.util.List;

import entities.Student;
import entities.Student;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;


@Stateless
@LocalBean
public class StudentService {

	@PersistenceContext(unitName="onside")
	private EntityManager em;
	public StudentService() {
        // TODO Auto-generated constructor stub
    }
	public void addStudent(Student student) {
        em.persist(student);
    }
    public  Student getStudentByLoginAndPassword(String login, String password) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.login = :login AND s.password = :password", Student.class);
        query.setParameter("login", login);
        query.setParameter("password", password);
        return query.getSingleResult();
    }


	public List<Student> getAllStudents()
	{
	List<Student> students = new ArrayList<Student>();
	    	TypedQuery<Student> query =
	    	em.createNamedQuery("Subject.findAll",Student.class);
	    	students = query.getResultList();
	    	return students;
	    }
		
	    
}