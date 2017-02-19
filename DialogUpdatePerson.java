import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 


public class DialogUpdatePerson extends JDialog {
   private PersonDAO dao ;   
   private long id ; 
  public DialogUpdatePerson(JFrame owner , PersonDAO dao, long id) {
      super(owner); 
      this.dao = dao; 
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
  private JLabel lblWelcome , lblId, lblName, lblAge, lblWage ; 
  private JTextField fieldId , fieldName , fieldAge, fieldWage; 
  private JButton btnUpdate , btnCancel ; 


  public void addComponents() {
           panelRoot =new JPanel(new GridLayout(6, 2)); 
	   this.getContentPane().add(panelRoot); 

	   lblWelcome = new JLabel("Person Properties to update"); 
	   panelRoot.add(lblWelcome); 
	   panelRoot.add(new JLabel()); 

	   lblId = new JLabel("Id: "); 
	   fieldId = new JTextField(); 
	   panelRoot.add(lblId); 
	   panelRoot.add(fieldId); 

	   lblName = new JLabel("Name: "); 
	   fieldName = new JTextField(); 
	   panelRoot.add(lblName); 
	   panelRoot.add(fieldName); 


  lblAge = new JLabel("Age: "); 
  fieldAge = new JTextField(); 
  panelRoot.add(lblAge); 
  panelRoot.add(fieldAge); 

  lblWage = new JLabel("Wage: "); 
  fieldWage = new JTextField(); 
  panelRoot.add(lblWage); 
  panelRoot.add(fieldWage); 


  btnUpdate = new JButton("Update"); 
  btnCancel = new JButton("Cancel"); 
  panelRoot.add(btnUpdate); 
  panelRoot.add(btnCancel); 
  }


  public void registerEvents() {
     btnUpdate.addActionListener(l -> {
         try {
           long id = Long.parseLong(fieldId.getText()); 
	   String name = fieldName.getText(); 
	   int age = Integer.parseInt(fieldAge.getText()); 
	   double wage = Double.parseDouble(fieldWage.getText()); 
	   dao.update(id, new Person(id, name, age, wage)); 
	 } catch(Exception ex) {
            ex.printStackTrace(); 
	 } finally {
              this.dispose(); 
	 }
     }); 

     btnCancel.addActionListener(l -> {
      this.dispose(); 
     });


    this.addWindowListener(new WindowAdapter() {
       public void windowActivated(WindowEvent evt) {
          Person p = dao.findById(id); 
	  fieldId.setText(p.id+""); 
	  fieldName.setText(p.name); 
	  fieldAge.setText(p.age+""); 
	  fieldWage.setText(p.wage+""); 
       }
    });  
  }

  
}
