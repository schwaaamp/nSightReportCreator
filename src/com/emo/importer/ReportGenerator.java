package com.emo.importer;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;

import com.emo.domain.Client;
import com.emo.domain.Trait;
import com.emo.report.WordProcessor;

public class ReportGenerator {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, InvalidFormatException, XmlException {

		Client client = new Client("Zahn Krava", "m");
		
		Trait.assignTraitsToCategory();

		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(client.getClientName());

		// Create image files for final report
		ImageProcessor imageProcessor = new ImageProcessor();
		for (Trait t : Trait.values()) {
			imageProcessor.createImageFromScore(client.getClientName(), t, t.getScore());
		}

		ChallengeImporter challengeImporter = new ChallengeImporter();
		challengeImporter.getChallengesFromFile(client.getClientName());

		// Create final report document
		WordProcessor wp = new WordProcessor();
		wp.createFinalReport(client);
	}
}
