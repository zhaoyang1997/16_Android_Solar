package Bean;

public class UserBean {
	private int Id;
	private String name;
	private String password;
	private String email;
	private String telephone;
    private int user_score;
    private String user_image;

	public String getUser_image() {
		return user_image;
	}


	public void setUser_image(String user_image) {
		this.user_image = user_image;
	}


	public UserBean(String name, String password) {
		
	
		this.name = name;
		this.password = password;
		
	}
	
	
	public UserBean(int id, String name, String password, String email, String telephone,int score, String image) {
		super();
		Id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.telephone = telephone;
		this.user_image=image;
		this.user_score=score;
		
	}

	public int getUser_score() {
		return user_score;
	}


	public void setUser_score(int user_score) {
		this.user_score = user_score;
	}


	public UserBean() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String phone) {
		this.telephone = phone;
	}

	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}
