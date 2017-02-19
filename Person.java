public class Person {
	public long id ; 
     	public String name; 
   public int age; 
   public double wage ; 

   public Person(long id , String name, int age, double wage) {
	   this.id = id ; 
     this.name = name; 
     this.age = age ; 
     this.wage = wage ; 
   }


   public String toString() {
        String fmt = String.format("id = %d , name = %s , age = %d , wage = %g" , id , name, age, wage); 
	return fmt ; 
   }
}
