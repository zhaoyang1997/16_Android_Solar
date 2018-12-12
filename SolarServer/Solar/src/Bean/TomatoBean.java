package Bean;

public class TomatoBean {

	private String tomatoName;
	private int tomato_id;
	private int user_id;
	private int tomato_num;
	private int tomato_score;
	private int tomato_year;
	private int tomato_month;
	private int tomato_day;
	public TomatoBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TomatoBean(int tomato_id, int user_id, int tomato_num,  int tomato_year, int tomato_month,
			int tomato_day) {
		super();
		this.tomato_id = tomato_id;
		this.user_id = user_id;
		this.tomato_num = tomato_num;
		this.tomato_year = tomato_year;
		this.tomato_month = tomato_month;
		this.tomato_day = tomato_day;
		this.tomato_score = tomato_num*5;
	}
	public String getTomatoName() {
		return tomatoName;
	}
	public void setTomatoName(String tomatoName) {
		this.tomatoName = tomatoName;
	}

	public void setTomato_score(int tomato_score) {
		this.tomato_score = tomato_score;
	}
	public int getTomato_id() {
		return tomato_id;
	}
	public void setTomato_id(int tomato_id) {
		this.tomato_id = tomato_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getTomato_num() {
		return tomato_num;
	}
	public void setTomato_num(int tomato_num) {
		this.tomato_num = tomato_num;
	}
	public int getTomato_score() {
		return tomato_score;
	}
    public void setTomato_score() {
    	this.tomato_score=tomato_num*5;
    }

	public int getTomato_year() {
		return tomato_year;
	}


	public void setTomato_year(int tomato_year) {
		this.tomato_year = tomato_year;
	}


	public int getTomato_month() {
		return tomato_month;
	}

	public void setTomato_month(int tomato_month) {
		this.tomato_month = tomato_month;
	}
	public int getTomato_day() {
		return tomato_day;
	}
	public void setTomato_day(int tomato_day) {
		this.tomato_day = tomato_day;
	}
    
}
