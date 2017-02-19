import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*; 

public class MainFrame extends JFrame {
    public MainFrame() {
      super("Person Manager");
     prepareGUI();  
    }

    //variables 
     private JPanel panelRoot; 
    private JLabel lblWelcome ;
   private JButton btnSave, btnUpdate , btnDelete , btnFindById , btnFindAll; 
  private JTextField  fieldUpdate , fieldDelete , fieldFindById ; 

   public void prepareGUI() {
      this.setSize(new Dimension(500, 500)); 
      this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
      addComponents(); 
      registerEvents(); 
      this.setVisible(true); 
   }

   public void addComponents() {
      panelRoot =new JPanel(); 
      panelRoot.setLayout(new BoxLayout(panelRoot, BoxLayout.Y_AXIS)); 
      this.getContentPane().add(panelRoot); 

      lblWelcome = new JLabel("Available commands:"); 
      panelRoot.add(lblWelcome); 

      btnSave = new JButton("Save new Person"); 
      panelRoot.add(btnSave); 

      btnUpdate = new JButton("Update Person"); 
     fieldUpdate =new JTextField(15); 
     JPanel panelUpdate = new JPanel(); 
     panelUpdate.add(btnUpdate); 
     panelUpdate.add(fieldUpdate); 
      panelRoot.add(panelUpdate); 

      btnDelete = new JButton("Delete Person"); 
      fieldDelete =new JTextField(15); 
      JPanel panelDelete =new JPanel(); 
      panelDelete.add(btnDelete);
      panelDelete.add(fieldDelete); 
      panelRoot.add(panelDelete); 

      btnFindById = new JButton("Find by id"); 
      fieldFindById = new JTextField(15); 
      JPanel panelFindById = new JPanel(); 
      panelFindById.add(btnFindById); 
      panelFindById.add(fieldFindById); 
      panelRoot.add(panelFindById); 

      btnFindAll = new JButton("Find All"); 
      panelRoot.add(btnFindAll); 
   }

   private PersonDAO dao = new PersonDAOMysql(); 
	   public void registerEvents() {
       btnSave.addActionListener(l -> {
         DialogSavePerson dialog = new DialogSavePerson(MainFrame.this, dao); 
     
       });

      btnFindAll.addActionListener(l -> {
          DialogFindAllPerson dialog = new DialogFindAllPerson(MainFrame.this, dao); 
      }); 


       btnUpdate.addActionListener(l -> {
	       try {
		       long id = Long.parseLong(fieldUpdate.getText()); 
           DialogUpdatePerson dialog = new DialogUpdatePerson(MainFrame.this, dao, id); 
	       }catch(Exception ex) {
                ex.printStackTrace(); 
	       }
       }); 
        
       btnFindById.addActionListener(l-> {
          try {
             long id = Long.parseLong(fieldFindById.getText()); 
	     DialogFindByIdPerson dialog = new DialogFindByIdPerson(MainFrame.this, dao, id); 
	  } catch(Exception ex) {
              ex.printStackTrace(); 
	  }
       }); 

       btnDelete.addActionListener(l -> {
           try {
              long id = Long.parseLong(fieldDelete.getText()); 
	      dao.delete(id) ;
	   } catch(Exception ex) {
              ex.printStackTrace(); 
	   }
       }); 
   }
    
}
