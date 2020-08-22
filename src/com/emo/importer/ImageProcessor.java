package com.emo.importer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.emo.constants.Constants;

public class ImageProcessor {

	public final static String circleColor = "#add8e6";
	public final static String backgroundImg = "resources/images/spectrum.png";

	public void createImageFromScore(String clientName, String traitName, double score) throws IOException {

		BufferedImage img = ImageIO.read(new File(backgroundImg));
		Graphics2D g = img.createGraphics();
		drawScore(img, g, score);
		
		File directory = new File(Constants.getPath().concat(clientName));
		if (!directory.exists()) {
			directory.mkdir();
		}

		// create image directory for client
		String directoryName = Constants.getPath().concat(clientName).concat("/images");
		String fileName = traitName.replaceAll("\\s", "") + ".png";

		directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}

		File file = new File(directoryName + "/" + fileName);
		ImageIO.write(img, "png", file);

		// Clean up -- dispose the graphics context that was created.
		g.dispose();
	}

	private void drawScore(BufferedImage img, Graphics2D g, double score) {

		g.setColor(Color.decode(circleColor));

		// say score is 7, we need to plot it at width * 70%
		double scoreAsPercentage = score / 10;

		// Draw on the BufferedImage via the graphics context.
		int x = (int) (score != 0.0 ? ((img.getWidth() * scoreAsPercentage) - img.getHeight()) : 0.0);
		int y = 0;
		int width = img.getHeight();
		int height = img.getHeight();
		g.fillOval(x, y, width, height);

		// draw circle border
		g.setColor(Color.DARK_GRAY);
		g.drawOval(x, y, width, height);
		;
	}

}
