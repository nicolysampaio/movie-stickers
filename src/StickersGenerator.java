import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StickersGenerator {
    public void create(InputStream inputStream, String movieTitle, String text) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);

        int width = image.getWidth();
        int height = image.getHeight();

        int newHeight = height + 200;

        BufferedImage newBufferedImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) newBufferedImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setFont(font);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        int stringWidth = fontMetrics.stringWidth(text);
        int textX = (width - stringWidth) / 2;
        int textY = newHeight - 100;

        FontRenderContext frc = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, frc);
        Shape outline = textLayout.getOutline(null);

        AffineTransform transform = AffineTransform.getTranslateInstance(textX, textY);
        Shape transformedOutline = transform.createTransformedShape(outline);

        graphics.setColor(Color.BLACK);
        graphics.setStroke(new BasicStroke(2));
        graphics.draw(transformedOutline);

        graphics.setColor(Color.YELLOW);
        graphics.fill(outline);


        graphics.drawString(text, (width - stringWidth) / 2, newHeight - 100);

        File dir = new File("src/stickers/");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File path = new File(dir, movieTitle + ".png");

        ImageIO.write(newBufferedImage, "png", path);
    }
}
