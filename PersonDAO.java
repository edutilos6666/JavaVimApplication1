 import java.util.List; 
public interface PersonDAO {
  void save(Person p) ; 
  void update(long id, Person p) ; 
  void delete(long id) ; 
  Person findById(long id); 
  List<Person> findAll(); 
}
