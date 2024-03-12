package BattagliaNavaleProject.formModel;

//Questa classe è il model del Register e viene utilizzato per permette alla classe 
//RegistrationControl di recuperare i dati e alla classe RegistrationView di impostarli
//in base a quello che scrive  l'utente
public class RegistrationModel {
	private String name;
	private String surname;
	private String nickname;
	private String password;
	
	//costruttori
	public RegistrationModel() 
	{
	
	}

	public RegistrationModel(String name, String surname, String nickname, String password) {
		this.name = name;
		this.surname = surname;
		this.nickname = nickname;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		

}
