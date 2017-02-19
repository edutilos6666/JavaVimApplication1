import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class DialogFindByIdPerson extends JDialog {
   private PersonDAO dao; 
   private long id ; 

   public DialogFindByIdPerson(JFrame owner, PersonDAO dao , long id) {
      super(owner); 
      this.dao = dao ; 
      this.id = id ; 
      prepareGUI(); 
   }


 
  public void prepareGUI() {
     this.setSize(new Dimension(500, 500)); 
     this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
     addComponents(); 
     registerEvents(); 
     this.setVisible(true); 
  }

 private JPanel panelRoot ; 
 private JLabel lblWelcome, lblId, lblName, lblAge, lblWage ; 
 private JTextField fieldId, fieldName , fieldAge, fieldWage; 
 private JButton btnClose ; 

 public void addComponents() {
   panelRoot = new JPanel(new GridLayout(6, 2)); 
   this.getContentPane().add(panelRoot);

   lblWelcome = new JLabel("Find person by id");
   panelRoot.add(lblWelcome); 
   panelRoot.add(new JLabel()); 

   lblId = new JLabel("Id: "); 
   fieldId = new JTextField(); 
   fieldId.setEnabled(false); 
   panelRoot.add(lblId); 
   panelRoot.add(fieldId); 

     lblName= new JLabel("Name: "); 
   fieldName = new JTextField(); 
   fieldName.setEnabled(false); 
   panelRoot.add(lblName); 
   panelRoot.add(fieldName); 


      lblAge= new JLabel("Age: "); 
   fieldAge = new JTextField(); 
   fieldAge.setEnabled(false); 
   panelRoot.add(lblAge); 
   panelRoot.add(fieldAge); 

      lblWage= new JLabel("Wage: "); 
   fieldWage = new JTextField(); 
   fieldWage.setEnabled(false); 
   panelRoot.add(lblWage); 
   panelRoot.add(fieldWage); 


     btnClose = new JButton("Close"); 
     panelRoot.add(btnClose) ; 

 }

 public void registerEvents() {
     this.addWindowListener(new WindowAdapter(){
          public void windowActivated(WindowEvent evt) {
           try {
             Person p = dao.findById(id); 
	     fieldId.setText(id+""); 
	     fieldName.setText(p.name); 
	     fieldAge.setText(p.age+""); 
	     fieldWage.setText(p.wage+""); 
	   } catch(Exception ex) {
               ex.printStackTrace(); 
	       DialogFindByIdPerson.this.dispose(); 
	   }
	  }
     }); 

     btnClose.addActionListener(l-> {
          DialogFindByIdPerson.this.dispose(); 
     }); 
 }

}
