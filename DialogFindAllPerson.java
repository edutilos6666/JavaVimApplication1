import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.table.* ; 
import java.util.Vector ; 
import java.util.List; 


public class DialogFindAllPerson extends JDialog {
  private PersonDAO dao ; 

   public DialogFindAllPerson(JFrame owner, PersonDAO dao) {
	   super(owner, "Find All People" , true); 
   this.dao = dao ; 
   prepareGUI(); 
   }


   private JPanel panelRoot ; 
   private JLabel lblWelcome ; 
   private JTable tablePeople ; 
   private DefaultTableModel modelPeople ; 
   private JButton btnClose ; 


   public void prepareGUI() {
       this.setSize(new Dimension(500, 500)); 
       this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
       addComponents(); 
       registerEvents(); 
       this.setVisible(true); 
   }

   public void addComponents() {
        panelRoot =new JPanel(); 
	panelRoot.setLayout(new BoxLayout(panelRoot, BoxLayout.Y_AXIS));
	this.getContentPane().add(panelRoot); 

	lblWelcome =new JLabel("All People"); 
	panelRoot.add(lblWelcome); 
	
		Vector<String> colNames = new Vector<>(); 
	colNames.add("Id"); 
	colNames.add("Name"); 
	colNames.add("Age"); 
	colNames.add("Wage"); 
	modelPeople = new DefaultTableModel(colNames, 0); 

	tablePeople = new JTable(modelPeople); 
	JScrollPane pane = new JScrollPane(tablePeople); 
	panelRoot.add(pane); 

	btnClose = new JButton("Close"); 
	panelRoot.add(btnClose); 
	
   }

   public void registerEvents() {
       btnClose.addActionListener(l -> {
          this.dispose(); 
       }); 


       this.addWindowListener(new WindowAdapter() {
        public void windowActivated(WindowEvent evt) {
           try {
             List<Person> list = dao.findAll(); 
	      for(Person p: list) {
                  modelPeople.addRow(personToVector(p));    
	      }
	   } catch(Exception ex) {
           ex.printStackTrace(); 
	   }
	}
       }); 
   }


   private Vector<Object> personToVector(Person p) {
   Vector<Object> v = new Vector<>(); 
   v.add(p.id); 
   v.add(p.name); 
   v.add(p.age); 
   v.add(p.wage); 
   return v ; 
   } 
  
}
