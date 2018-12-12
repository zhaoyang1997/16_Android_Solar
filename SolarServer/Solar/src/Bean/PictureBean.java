package Bean;

public class PictureBean {

	private int user_id;
	private int image_id;
	private String image_url;
	public PictureBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PictureBean(int user_id, int image_id, String image_url) {
		super();
		this.user_id = user_id;
		this.image_id = image_id;
		this.image_url = image_url;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
}
