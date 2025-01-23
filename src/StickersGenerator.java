import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StickersGenerator {
    public  void create() throws IOException {
        BufferedImage image = ImageIO.read(new File("src/assets/TheShawshankRedemption.jpg"));

        int width = image.getWidth();
        int height = image.getHeight();

        int newHeight = height + 200;

        BufferedImage newBufferedImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newBufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);

        graphics.drawString("GREAT", 0, newHeight - 100);

        ImageIO.write(newBufferedImage, "png", new File("src/assets/stickers/TheShawshankRedemption.png"));
    }

    public static void main(String[] args) throws Exception {
        StickersGenerator generator = new StickersGenerator();
        generator.create();
    }
}