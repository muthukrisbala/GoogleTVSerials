package google.tv.serials.main;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * @author mkyong
 *
 */
public class ImageTest {

	private static final int IMG_WIDTH = 180;
	private static final int IMG_HEIGHT = 180;
	static String returnStr="";
	
	public static String scaleImg(String  sourceFile,String fileName,String type1){
		
		String targetPath="C:/xampp/tomcat/webapps/OTP/image/Modified/";
	try{
			
		BufferedImage originalImage = ImageIO.read(new File(sourceFile+fileName));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
			
		//BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		//ImageIO.write(resizeImageJpg, "jpg", new File("E:/OnlineTamilPortal/Images/Modified/Test.jpg")); 
			
		//BufferedImage resizeImagePng = resizeImage(originalImage, type);
		//ImageIO.write(resizeImagePng, "png", new File("E:/OnlineTamilPortal/Images/Modified/Testpng.jpg")); 
			
		BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
		ImageIO.write(resizeImageHintJpg, "jpg", new File(targetPath+fileName)); 
			
		//BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
		//ImageIO.write(resizeImageHintPng, "png", new File("E:/OnlineTamilPortal/Images/Modified/Testhintpng.jpg")); 
		
		//BufferedImage resizeImageNew = createResizedCopy(originalImage,180,180,true);
		//ImageIO.write(resizeImageNew, "jpg", new File("E:/OnlineTamilPortal/Images/Modified/TestNew.jpg")); 
		
	
	if(type1.equalsIgnoreCase("web")){
		returnStr="image/Modified/"+fileName;	}
	else{
		returnStr="image/Modified/"+fileName;	
	}
				
	}catch(IOException e){
		System.out.println(e.getMessage());
	}
	return returnStr;
		
    }
	
    private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
		
	return resizedImage;
    }
	
    
    static BufferedImage createResizedCopy(Image originalImage, 
    		int scaledWidth, int scaledHeight, 
    		boolean preserveAlpha)
    {
    	System.out.println("resizing...");
    	int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
    	BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
    	Graphics2D g = scaledBI.createGraphics();
    	if (preserveAlpha) {
    		g.setComposite(AlphaComposite.Src);
    	}
    	g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null); 
    	g.dispose();
    	return scaledBI;
    }
    
    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){
		
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();	
	g.setComposite(AlphaComposite.Src);

	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
	
	return resizedImage;
    }	
}