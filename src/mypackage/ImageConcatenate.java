
package mypackage;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageConcatenate {
    
    /**
     * 
     * @param fileStr1 image file name to be concatenate.
     * @param fileStr2  image file name to be concatenate.
     * @param fileStr3 destination image file name as result
     * @param path (path containing all images.)
     * @throws IOException 
     * @see javax.imageio.ImageIO#read(java.io.File) 
     * @see java.awt.image.BufferedImage
     * @see java.awt.Graphics#drawImage(java.awt.Image, int, int, int, int, java.awt.image.ImageObserver) 
     */
    
     public static void mergeImage(String fileStr1, String fileStr2, String fileStr3, String path)
      throws IOException {
    File file1 = new File(path, fileStr1);
    File file2 = new File(path, fileStr2);
 
     BufferedImage image1 = ImageIO.read(file1);
     BufferedImage image2 = ImageIO.read(file2);
        int img1wid=image1.getWidth();
        int img1hig=image1.getHeight();
        int img2wid=image2.getWidth();
        int img2hig=image2.getHeight();
//         int newwid=Math.min(img1wid, img2wid);
//        int newhig=Math.min(img1hig, img2hig);
    BufferedImage combined = new BufferedImage(Math.max(img1wid,img2wid),(img1hig+img2hig),BufferedImage.TYPE_INT_RGB);
    
 
    // paint both images, preserving the alpha channels
   
    Graphics g = combined.getGraphics();
    try {
      g.drawImage(image1, 0, 0,img1wid,img1hig, null);
      g.drawImage(image2, 0, img1hig,img1wid,img2hig, null);
      
      // Save as new image
      ImageIO.write(combined, "JPG", new File(path, fileStr3));
    } finally {
      if (g != null) {
        g.dispose();
      }
    }
  }
     
     /**
      * main method that initializes all images file name add 
      * call the method <code>mergeImage()</code> to concatenate image
      * @see ImageConcatenate#mergeImage(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
      * @param args command line arguments <code>No requirement </code>
      * 
      */
     public static void main(String[] args){
        String path = "D:\\jakarta EE\\netbeans\\git\\imageconcatenation\\gitcloning\\src\\images";
    String fileStr1 = "image1.jpg";
    String fileStr2 = "image2.jpg";
    String fileStr3 = "result.jpg";
    try{
    ImageConcatenate.mergeImage(fileStr1, fileStr2, fileStr3, path);
    }
    catch(IOException ex)
    {
        ex.printStackTrace();
    }
    }
}
