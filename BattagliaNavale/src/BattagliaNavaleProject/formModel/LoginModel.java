package BattagliaNavaleProject.formModel;

//Questa classe è il model del Login e viene utilizzato per permette alla classe 
//LoginControl di recuperare i dati e alla classe LoginView di impostarli in base a quello che scrive 
//l'utente
public class LoginModel {
	
	private String userName;
    private String password;
    
    public LoginModel()
    {}

    
    public LoginModel(String username, String password){
        this.userName = username;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
	
	 
}
