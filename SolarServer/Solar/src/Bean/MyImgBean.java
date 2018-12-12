package Bean;

public class MyImgBean {

	private int id;
	private String imageSrc;
	private String price;
	
	public MyImgBean() {
		super();
	}
	
	public MyImgBean(int id,String imageSrc, String price) {
		super();
		this.id = id;
		this.imageSrc = imageSrc;
		this.price = price;
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageSrc() {
		return imageSrc;
	}
	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "myImgs"+id+"" + imageSrc+"" + price+ "";
	}

}


