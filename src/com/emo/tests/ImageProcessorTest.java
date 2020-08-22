package com.emo.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.emo.constants.Constants;
import com.emo.importer.ImageProcessor;

import junit.framework.TestCase;

public class ImageProcessorTest extends TestCase {

	@Test
	public void testCreatingImage() throws IOException {
		String traitName = "Practical";
		ImageProcessor processImages = new ImageProcessor();
		processImages.createImageFromScore("Zahn Krava", traitName, 5);

		BufferedImage img = ImageIO.read(new File(Constants.getPath().concat("Zahn Krava/images/").concat(traitName).concat(".png")));
		assertNotNull(img);
	}

	@Test
	public void testScoreOfZero() throws IOException {
		String traitName = "Analytical";
		ImageProcessor processImages = new ImageProcessor();
		processImages.createImageFromScore("Test Client", traitName, 0);

		BufferedImage img = ImageIO.read(new File(Constants.getPath().concat("Test Client/images/").concat(traitName).concat(".png")));
		assertNotNull(img);
	}

}
