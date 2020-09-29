package com.emo.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.emo.constants.Constants;
import com.emo.domain.Trait;
import com.emo.importer.ImageProcessor;

import junit.framework.TestCase;

public class ImageProcessorTest extends TestCase {

	@Test
	public void testCreatingImage() throws IOException {
//		String traitName = "Practical";
		Trait t = Trait.PRACTICAL;
		ImageProcessor processImages = new ImageProcessor();
		processImages.createImageFromScore("Test Client", t, 5);

		String fileName = t.getName().replaceAll("\\s", "") + ".png";
		BufferedImage img = ImageIO.read(new File(Constants.getPath().concat("Test Client/images/").concat(fileName)));
		assertNotNull(img);
	}

	@Test
	public void testScoreOfTen() throws IOException {
//		String traitName = "Analytical";
		Trait t = Trait.ANALYTICAL;
		ImageProcessor processImages = new ImageProcessor();
		processImages.createImageFromScore("Test Client", t, 10);

		String fileName = t.getName().replaceAll("\\s", "") + ".png";
		BufferedImage img = ImageIO.read(new File(Constants.getPath().concat("Test Client/images/").concat(fileName)));
		assertNotNull(img);
	}

	@Test
	public void testScoreOfZero() throws IOException {
//		String traitName = "Analytical";
		Trait t = Trait.HIGH_SELF_ESTEEM;
		ImageProcessor processImages = new ImageProcessor();
		processImages.createImageFromScore("Test Client", t, 0);

		String fileName = t.getName().replaceAll("\\s", "") + ".png";
		BufferedImage img = ImageIO.read(new File(Constants.getPath().concat("Test Client/images/").concat(fileName)));
		assertNotNull(img);
	}

	@Test
	public void testConcreteAbstractSpectrum() throws IOException {
//		String traitName = "Analytical";
		Trait t = Trait.VERBAL_REASONING;
		ImageProcessor processImages = new ImageProcessor();
		processImages.createImageFromScore("Test Client", t, 3);

		String fileName = t.getName().replaceAll("\\s", "") + ".png";
		BufferedImage img = ImageIO.read(new File(Constants.getPath().concat("Test Client/images/").concat(fileName)));
		assertNotNull(img);
	}

}
