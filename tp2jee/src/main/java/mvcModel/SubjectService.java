package mvcModel;

import java.util.ArrayList;
import java.util.List;

import entities.Subject;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

import jakarta.persistence.*;


/**
 * Session Bean implementation class SubjectService
 */
@Stateless
@LocalBean
public class SubjectService {
	
	@PersistenceContext(unitName="onside")
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public SubjectService() {
        // TODO Auto-generated constructor stub
    }
    public  List<Subject> getAllSujects(){
    TypedQuery<Subject> query = em.createNamedQuery("Subject.findAll",Subject.class);
    System.out.println("EJB ACCESSED 2");
    return query.getResultList(); 
}
    public List<Subject> deleteSubjectById(int subjectId)
    {
    	List<Subject> subjects = new ArrayList<Subject>();
    	Subject sub = em.find(Subject.class, subjectId);
    	if(sub!=null)
    	em.remove(sub);
    	TypedQuery<Subject> query = em.createNamedQuery("Subject.findAll",Subject.class);
    	subjects = query.getResultList();
    	return subjects;
    }
    public List<Subject> getAllSujectsByTitleAndAffiliation(String subjectTitle, String teacherAffiliation)
    {
    		List<Subject> subjects = new ArrayList<Subject>();
    		TypedQuery<Subject> query =	em.createNamedQuery("Subject.findAllByTitleAndAffiliation",Subject.class);
    		System.out.println("EJB ACCESSED");
    		query.setParameter(1, subjectTitle);
    		query.setParameter(2, teacherAffiliation);
    		subjects = query.getResultList();
    		return subjects;
    }
    public void addSubject(Subject subject) {
        em.persist(subject);
    }
}

