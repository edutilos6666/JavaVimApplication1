import java.util.List ; 
//import static MyAssert.* ; 
public class PersonTester {
  public static void main(String [] args) throws Exception {
      test1(); 
  }


  private static void test1() throws Exception  {
      PersonDAO dao = new PersonDAOImpl(); 
      dao.save(new Person(1, "foo", 10, 100.0)); 
dao.save(new Person(2, "bar", 20, 200.0)); 
dao.save(new Person(3, "bim", 30, 300.0)); 
dao.save(new Person(4, "pako", 40, 400.0)); 

  List<Person> list = dao.findAll(); 
  boolean res = MyAssert.assertEquals(4, list.size());   
   
  Person p1 = list.get(0); 
  MyAssert.assertEquals(1, p1.id); 
  MyAssert.assertEquals("foo", p1.name); 
  MyAssert.assertEquals(10, p1.age); 
  MyAssert.assertEquals(100.0, p1.wage); 


// testing dao.update()

  dao.update(2, new Person(2, "newbar", 66, 666.6)); 
    p1 = dao.findById(2); 
    MyAssert.assertEquals(2, p1.id); 
    MyAssert.assertEquals("newbar", p1.name); 
    MyAssert.assertEquals(66, p1.age); 
    MyAssert.assertEquals(666.6 , p1.wage); 
    

 //testing dao.delete() 
   list = dao.findAll(); 
   MyAssert.assertEquals(4, list.size()); 
    dao.delete(1); 
    list = dao.findAll(); 
  MyAssert.assertEquals(3, list.size()); 
  dao.delete(2); 
  list = dao.findAll(); 
  MyAssert.assertEquals(2, list.size()); 
  dao.delete(3); 
  list = dao.findAll(); 
  MyAssert.assertEquals(1, list.size()); 
  dao.delete(4); 
  list = dao.findAll(); 
  MyAssert.assertEquals(0, list.size()); 

  if(res == true) System.out.println("passed"); 
  }
}
