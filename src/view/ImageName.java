package view;

import java.io.InputStream;

import javafx.scene.image.Image;

public class ImageName {
	private Image image;
	private String imageName;
	
	public ImageName(String image,String name) {
		this.image=new Image(image);
		this.imageName=name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	


	
}
