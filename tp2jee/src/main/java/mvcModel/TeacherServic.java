package mvcModel;
import java.util.ArrayList;
import java.util.List;

import entities.Teacher;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;


@Stateless
@LocalBean
public class TeacherServic {

	@PersistenceContext(unitName="onside")
	private EntityManager em;
	public TeacherServic() {
        // TODO Auto-generated constructor stub
    }
	
	public List<Teacher> getAllTeachers()
    // Récupération de la liste de tous les enseignants
	{
	List<Teacher> teachers = new ArrayList<Teacher>();
	    	TypedQuery<Teacher> query =
	    	em.createNamedQuery("Subject.findAll",Teacher.class);
	    	teachers = query.getResultList();
	    	return teachers;
	    }
	
	    // Récupération d'un enseignant par id
	public List<Teacher> deleteSubjectById(int TeacherId)
	{
		List<Teacher> teachers = new ArrayList<Teacher>();
		Teacher tea = em.find(Teacher.class, TeacherId);
		if(tea!=null)
		em.remove(tea);
		TypedQuery<Teacher> query =em.createNamedQuery("Teacher.findAll",Teacher.class);
		teachers = query.getResultList();
		return teachers;
	}
	    // Récupération de la liste des enseignants d'une matière avec un intitulé donné
	    public List<Teacher> getTeachersBySubject(String subjectTitle) {
			List<Teacher> teachers = new ArrayList<Teacher>();
			TypedQuery<Teacher> query =
			em.createNamedQuery("Subject.findAllByTitleAndAffiliation",Teacher.class);
			query.setParameter(1, subjectTitle);
			teachers = query.getResultList();
			return teachers;
	    }
}