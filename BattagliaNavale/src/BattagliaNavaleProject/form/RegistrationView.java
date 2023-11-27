package BattagliaNavaleProject.form;

import java.awt.Color;
import java.awt.EventQueue;
//import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class RegistrationView extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private static final JLabel NicknameField = null;
	private JTextField txtNickname;
	private JTextField txtName;
	private JTextField txtSurname;
    private JPasswordField txtPassword;
    
  
    private JButton btnRegistration;
    private JButton backbutton;
    private RegistrationModel model;
    
    public RegistrationView() {
   
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 405);
		
		final ImageIcon sfondo = new ImageIcon("../docs/resources/SfondoTest.jpeg");
		
		JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(sfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        getContentPane().add(backgroundPanel);
    	backgroundPanel.setLayout(null);
      	
    	JLabel titleLabel = new JLabel("Registration");
    	titleLabel.setBounds(5, 5, 99, 20);
		titleLabel.setForeground(new Color(0, 0, 255));
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundPanel.add(titleLabel);
		
		JLabel nicknameLabel = new JLabel("Nickname:");
		nicknameLabel.setBounds(67, 175, 120, 13);
		backgroundPanel.add(nicknameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(67, 229, 120, 13);
		backgroundPanel.add(passwordLabel);
		
		txtName = new JTextField(15);
		txtName.setBounds(197, 65, 132, 19);
		backgroundPanel.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField(15);
		txtSurname.setBounds(197, 118, 132, 19);
		backgroundPanel.add(txtSurname);
		txtSurname.setColumns(10);
		
		
		txtNickname = new JTextField(15);
		txtNickname.setBounds(197, 172, 132, 19);
		backgroundPanel.add(txtNickname);
		txtNickname.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(67, 68, 120, 13);
		backgroundPanel.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(67, 121, 120, 13);
		backgroundPanel.add(surnameLabel);
		
		 txtPassword = new JPasswordField(15);
		 txtPassword.setBounds(197, 226, 137, 19);
	     txtPassword.setEchoChar('*');
		backgroundPanel.add(txtPassword);
		txtPassword.setColumns(10);
	
		btnRegistration = new JButton("Save");
		btnRegistration.setBounds(5, 307, 99, 35);
        backgroundPanel.add(btnRegistration);
        //btnRegistration.addActionListener(this);
        btnRegistration.addActionListener(new RegistrationControl(this));
        
		backbutton = new JButton("back");
		backbutton.setBounds(382, 307, 99, 35);
        backgroundPanel.add(backbutton);
        //backbutton.addActionListener(this);
        backbutton.addActionListener(new RegistrationControl(this));
       
 
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
    
    	
   }

    public RegistrationModel getUser(){
        model = new RegistrationModel(txtName.getText(),txtSurname.getText(),txtNickname.getText(),txtPassword.getText());
        return model;       
    }
 
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
    }
    public void close() {
    	  SchermataIniziale inizio;
    	  inizio = new SchermataIniziale(); 
    	  dispose();
    	  inizio.setVisible(true);
         }
    
    public void addRecListener(ActionListener log) {
          btnRegistration.addActionListener(log);
        }

    		
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	RegistrationView frame = new RegistrationView();
                    RegistrationControl controller = new RegistrationControl(frame);
                    frame.setVisible(true);
                   
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
	
