 
import java.util.* ; 
public class PersonDAOImpl implements PersonDAO {

	private Map<Long , Person> container ; 

	public PersonDAOImpl() {
           if(container == null) 
		   container = new HashMap<>(); 
	}
 @Override 
  public void save(Person p)  {
     container.put(p.id , p); 
  }
  
 @Override 
  public void update(long id, Person p)  {
	  container.put(p.id , p); 
  }
  @Override 
  public  void delete(long id)  { 
	 container.remove(id); 
  }
  @Override 
public   Person findById(long id) {
	return container.get(id); 
}
  @Override 
public   List<Person> findAll() {
    Set<Map.Entry<Long , Person>>  entrySet = container.entrySet(); 
    List<Person> list = new ArrayList<>(); 
    for(Map.Entry<Long , Person> entry : entrySet) {
           list.add(entry.getValue()); 
    }

    return list; 
}

 }

