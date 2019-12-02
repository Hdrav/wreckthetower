package view;
import javafx.scene.image.Image;

public class ImageName {
	/*
	 * wrapper for image containing also the weapon name
	 * */
	private Image image;
	private String imageName;
	
	public ImageName(String image,String name) {
		this.image=new Image(image);
		this.imageName=name;
	}

	public Image getImage() {
		return image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}