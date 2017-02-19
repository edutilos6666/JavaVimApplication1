import java.util.List; 
import java.util.ArrayList; 
import java.sql.* ; 

public class PersonDAOMysql implements PersonDAO {
 
	private final String user = "root", 
		pass = "", 
		url = "jdbc:mysql://localhost/test" ; 

	private Connection conn ; 
	private Statement stmt ; 
	private ResultSet rs ; 
	private PreparedStatement pstmt ; 


	public PersonDAOMysql() {
		try {
           DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver()); 
	   dropCreateTable(); 
		} catch(Exception ex) {
               ex.printStackTrace(); 
		}
	}
      private void connect() {
           try {
              conn = DriverManager.getConnection(url, user, pass); 
	      stmt = conn.createStatement(); 
	   } catch(Exception ex) {
           // ex.printStackTrace(); 
	   }
      }	

private void disconnect() {
  try {
        pstmt.close(); 
	rs.close(); 
	stmt.close(); 
	conn.close(); 
  } catch(Exception ex) {
//       ex.printStackTrace(); 
  }
}	

private void dropCreateTable() {
    connect(); 
    try {
   String cmd = "drop table if exists Person"; 
     stmt.executeUpdate(cmd); 
     cmd = "create table if not exists Person (" + 
	     "id bigint not null primary key , "+ 
	     "name varchar(50) not null , " + 
	     "age int not null, " + 
	     "wage double not null)" ; 
     stmt.executeUpdate(cmd); 
    } catch(Exception ex) {
        ex.printStackTrace(); 
    }
    disconnect(); 
}

public  void save(Person p)  {
  connect(); 
  try {
    String cmd = "insert into Person values(?, ?, ?, ?)" ; 
    pstmt = conn.prepareStatement(cmd); 
    pstmt.setLong(1, p.id); 
    pstmt.setString(2, p.name); 
    pstmt.setInt(3, p.age); 
    pstmt.setDouble(4, p.wage); 
    pstmt.executeUpdate(); 
  } catch(Exception ex) {
   ex.printStackTrace(); 
  }
  disconnect(); 
  }
  public void update(long id, Person p)  {
    connect(); 

     try {
    String cmd = "update Person set name = ? , age = ? , wage = ?  where id = ?" ; 
     pstmt = conn.prepareStatement(cmd); 
     pstmt.setString(1, p.name); 
     pstmt.setInt(2, p.age); 
     pstmt.setDouble(3, p.wage); 
     pstmt.setLong(4, id); 
     pstmt.executeUpdate(); 
     } catch(Exception ex) {
      ex.printStackTrace(); 
     }
    disconnect(); 
  
  }
  public void delete(long id)  {
       connect(); 
       try {
         String cmd = "delete from Person where id = ?" ; 
	 pstmt = conn.prepareStatement(cmd); 
	 pstmt.setLong(1, id); 
	 pstmt.executeUpdate(); 
       } catch(Exception ex) {
           ex.printStackTrace(); 
       }
       disconnect(); 
  }
public   Person findById(long id) {
	connect(); 
        Person p = null  ; 
	 try {
	 String cmd = "select * from Person where id = ? " ; 
	 pstmt = conn.prepareStatement(cmd); 
	 pstmt.setLong(1, id); 
	 rs = pstmt.executeQuery(); 
	 rs.next(); 
	 p = rsToPerson(); 
	 } catch(Exception ex) {
             ex.printStackTrace(); 
	 }
	disconnect(); 
 return  p ; 
}

  private Person rsToPerson() throws Exception {
      long id = rs.getLong(1); 
      String name = rs.getString(2); 
      int age = rs.getInt(3); 
      double wage = rs.getDouble(4); 
      return new Person(id, name,age , wage); 
  }

  public List<Person> findAll() {
	  List<Person> list = new ArrayList<>(); 
	  connect(); 
            try {
        String cmd = "select * from Person" ; 
	  rs = stmt.executeQuery(cmd); 
	  while(rs.next()) {
            list.add(rsToPerson()); 
	  }
	    } catch(Exception ex) {
            ex.printStackTrace(); 
	    }
	  disconnect(); 
    return list ; 
  }

}
