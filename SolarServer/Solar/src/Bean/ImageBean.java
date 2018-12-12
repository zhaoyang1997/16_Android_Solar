package Bean;

public class ImageBean {
	private int image_id;
	private String image_src;
	private int image_score;
	
	public ImageBean(int image_id, String image_src, int image_score) {
		super();
		this.image_id = image_id;
		this.image_src = image_src;
		this.image_score = image_score;
	}
	public ImageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public String getImage_src() {
		return image_src;
	}
	public void setImage_src(String image_src) {
		this.image_src = image_src;
	}
	public int getImage_score() {
		return image_score;
	}
	public void setImage_score(int image_score) {
		this.image_score = image_score;
	}
	
}
