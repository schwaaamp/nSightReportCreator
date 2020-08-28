package com.emo.importer;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.emo.constants.Constants;
import com.emo.domain.Category;
import com.emo.domain.Trait;

public class ImageProcessor {

	public final static String circleColor = "#add8e6";
	public final static String backgroundImg = "resources/images/spectrum2.png";

	public void createImageFromScore(String clientName, Trait trait, double score) throws IOException {

		BufferedImage img = ImageIO.read(new File(backgroundImg));
		Graphics2D g = img.createGraphics();
		drawScore(img, g, trait, score);

		File directory = new File(Constants.getPath().concat(clientName));
		if (!directory.exists()) {
			directory.mkdir();
		}

		// create image directory for client
		String directoryName = Constants.getPath().concat(clientName).concat("/images");
		String fileName = trait.getName().replaceAll("\\s", "") + ".png";

		directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}

		File file = new File(directoryName + "/" + fileName);
		ImageIO.write(img, "png", file);

		// Clean up -- dispose the graphics context that was created.
		g.dispose();
	}

	private void drawScore(BufferedImage img, Graphics2D g, Trait t, double score) {

		// added new image with 61 pixels of additional height for the trait names
		int heightBuffer = 63;

		g.setColor(Color.decode(circleColor));

		// say score is 7, we need to plot it at width * 70%
		double scoreAsPercentage = score / 10;

		// Draw on the BufferedImage via the graphics context.
		int width = 80;
		int height = 80;
		int x = (int) (score != 0.0 ? ((img.getWidth() * scoreAsPercentage) - 80) : 0.0);
		int y = 0 + heightBuffer;
		g.fillOval(x, y, width, height);

		// draw circle border
		g.setColor(Color.DARK_GRAY);
		g.drawOval(x, y, width, height);

		// add score inside circle
		int textBuffer = (score > 9.5) ? 4 : 15;
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 36));
		g.drawString(Double.toString(score), x + textBuffer, y + 55);
		
		//decide if trait name or low high terms will be used for spectrum header
		String leftTerm = Trait.isTraitMemberOfCategory(t, Category.APTITUDE_PROFILE) ? "Low" : t.getName();
		String rightTerm = Trait.isTraitMemberOfCategory(t, Category.APTITUDE_PROFILE) ? "High" : t.getPairName();

		// add left trait name
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 34));
		g.drawString(leftTerm, 0, 40);

		//add right trait name
		//find out how long the pair name is in order to determine x position
		final FontMetrics fm = g.getFontMetrics();
		final Rectangle2D stringBounds = fm.getStringBounds(rightTerm, g);
		int rightTraitX = img.getWidth() - (int)stringBounds.getWidth();
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.PLAIN, 34));
		g.drawString(rightTerm, rightTraitX, 40);
		

	}

}
