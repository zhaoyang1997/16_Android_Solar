package Bean;

public class SingBean {
	 private int sing_id;
	   private String sing_url;
	   private String sing_name;
	   public SingBean() {
			super();
			// TODO Auto-generated constructor stub
	   }
	public SingBean(int sing_id, String sing_url, String sing_name) {
		super();
		this.sing_id = sing_id;
		this.sing_url = sing_url;
		this.sing_name = sing_name;
	}
	public int getSing_id() {
		return sing_id;
	}
	public void setSing_id(int sing_id) {
		this.sing_id = sing_id;
	}
	public String getSing_url() {
		return sing_url;
	}
	public void setSing_url(String sing_url) {
		this.sing_url = sing_url;
	}
	public String getSing_name() {
		return sing_name;
	}
	public void setSing_name(String sing_name) {
		this.sing_name = sing_name;
	}
	   
	   
	}
