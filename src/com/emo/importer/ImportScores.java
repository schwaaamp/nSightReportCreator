package com.emo.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.emo.constants.Constants;
import com.emo.domain.AssessmentScores;
import com.emo.domain.Category;
import com.emo.domain.Trait;

public class ImportScores {

	private static final String filename = "NSight_Coach_Summary_Report_For_";

	public AssessmentScores getScoresFromFile(String clientName) throws IOException {

		AssessmentScores scoreResults = new AssessmentScores();
		PDDocument pd = PDDocument.load(new File(
				Constants.getPath().concat(clientName).concat("/").concat(filename).concat(clientName).concat(".pdf")));
		COSDocument cosDoc = pd.getDocument();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		PDDocument pdDoc = new PDDocument(cosDoc);
		String parsedText = pdfStripper.getText(pdDoc);
		System.out.println(parsedText);

		if (parsedText.length() > 0) {
			System.out.println(filename + clientName + ".pdf has been parsed");

			assignTraitScores(parsedText);
			List<Category> categories = assignCategoryTraits();

			pdDoc.close();
			scoreResults = new AssessmentScores(clientName, categories);
			return scoreResults;
		}

		pdDoc.close();
		return scoreResults;
	}

	private List<Category> assignCategoryTraits() {
		List<Category> categories = new ArrayList<Category>();

		// Category Self_Perception = new Category();
		List<Trait> selfPerceptionTraits = new ArrayList<Trait>();
		selfPerceptionTraits.add(Trait.CANDID);
		selfPerceptionTraits.add(Trait.HIGH_SELF_ESTEEM);
		selfPerceptionTraits.add(Trait.LOW_SELF_CONCEPT);

		Category.SELF_PERCEPTION.setCategoryTraits(selfPerceptionTraits);
		categories.add(Category.SELF_PERCEPTION);

		List<Trait> thinkingStyleTraits = new ArrayList<Trait>();
		thinkingStyleTraits.add(Trait.EMOTIONAL);
		thinkingStyleTraits.add(Trait.ANALYTICAL);
		thinkingStyleTraits.add(Trait.LOGICAL);
		thinkingStyleTraits.add(Trait.PRACTICAL);

		Category.THINKING_STYLE.setCategoryTraits(thinkingStyleTraits);
		categories.add(Category.THINKING_STYLE);

		List<Trait> driveTraits = new ArrayList<Trait>();
		driveTraits.add(Trait.SECURITY_ORIENTED);
		driveTraits.add(Trait.COOPERATIVE);
		driveTraits.add(Trait.RULE_BOUND);

		Category.DRIVE.setCategoryTraits(driveTraits);
		categories.add(Category.DRIVE);

		List<Trait> stressTraits = new ArrayList<Trait>();
		stressTraits.add(Trait.ANXIOUS);
		stressTraits.add(Trait.TOLERANT);
		stressTraits.add(Trait.APPREHENSIVE);

		Category.STRESS.setCategoryTraits(stressTraits);
		categories.add(Category.STRESS);

		List<Trait> communicationTraits = new ArrayList<Trait>();
		communicationTraits.add(Trait.SERIOUS);
		communicationTraits.add(Trait.RESERVED);
		communicationTraits.add(Trait.FORTHRIGHT);

		Category.COMMUNICATION.setCategoryTraits(communicationTraits);
		categories.add(Category.COMMUNICATION);

		List<Trait> leadershipTraits = new ArrayList<Trait>();
		leadershipTraits.add(Trait.PASSIVE);
		leadershipTraits.add(Trait.SUBMISSIVE);
		leadershipTraits.add(Trait.SUSPICIOUS);

		Category.LEADERSHIP.setCategoryTraits(leadershipTraits);
		categories.add(Category.LEADERSHIP);

		List<Trait> reliabilityTraits = new ArrayList<Trait>();
		reliabilityTraits.add(Trait.INDIFFERENT);
		reliabilityTraits.add(Trait.CHANGEABLE);
		reliabilityTraits.add(Trait.EXPEDIENT);

		Category.RELIABILITY.setCategoryTraits(reliabilityTraits);
		categories.add(Category.RELIABILITY);
		
		List<Trait> aptitudeTraits = new ArrayList<Trait>();
		aptitudeTraits.add(Trait.VERBAL_REASONING);
		aptitudeTraits.add(Trait.NUMERICAL_REASONING);
		aptitudeTraits.add(Trait.WORD_KNOWLEDGE);
		aptitudeTraits.add(Trait.SCANNING_ACCURACY);

		Category.APTITUDE_PROFILE.setCategoryTraits(aptitudeTraits);
		categories.add(Category.APTITUDE_PROFILE);
		return categories;
	}

	private void assignTraitScores(String parsedText) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(parsedText));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("Distorted")) {
				Trait.CANDID.setScore(parseScore(line));
			} else if (line.startsWith("Low Self-Esteem")) {
				Trait.HIGH_SELF_ESTEEM.setScore(parseScore(line));
			} else if (line.startsWith("High Self-Concept")) {
				Trait.LOW_SELF_CONCEPT.setScore(parseScore(line));
			} else if (line.startsWith("Cognitive")) {
				Trait.EMOTIONAL.setScore(parseScore(line));
			} else if (line.startsWith("Instinctive")) {
				Trait.ANALYTICAL.setScore(parseScore(line));
				System.out.println("analytical score: " + Trait.ANALYTICAL.getScore());
			} else if (line.startsWith("Imaginative")) {
				Trait.LOGICAL.setScore(parseScore(line));
			} else if (line.startsWith("Sensitive")) {
				Trait.PRACTICAL.setScore(parseScore(line));
			} else if (line.startsWith("Recognition")) {
				double score = parseScore(line);
				Trait.SECURITY_ORIENTED.setScore(score);
			} else if (line.startsWith("Competitive")) {
				double score = parseScore(line);
				Trait.COOPERATIVE.setScore(score);
			} else if (line.startsWith("Risk-taking")) {
				double score = parseScore(line);
				Trait.RULE_BOUND.setScore(score);
			} else if (line.startsWith("Steady")) {
				double score = parseScore(line);
				Trait.ANXIOUS.setScore(score);
			} else if (line.startsWith("Intolerant")) {
				Trait.TOLERANT.setScore(parseScore(line));
			} else if (line.startsWith("Confident")) {
				Trait.APPREHENSIVE.setScore(parseScore(line));
			} else if (line.startsWith("Enthusiastic")) {
				Trait.SERIOUS.setScore(parseScore(line));
			} else if (line.startsWith("Self-Disclosing")) {
				Trait.RESERVED.setScore(parseScore(line));
			} else if (line.startsWith("Political")) {
				Trait.FORTHRIGHT.setScore(parseScore(line));
			} else if (line.startsWith("Assertive")) {
				Trait.PASSIVE.setScore(parseScore(line));
			} else if (line.startsWith("Aggressive")) {
				Trait.SUBMISSIVE.setScore(parseScore(line));
			} else if (line.startsWith("Trusting")) {
				Trait.SUSPICIOUS.setScore(parseScore(line));
			} else if (line.startsWith("Committed")) {
				Trait.INDIFFERENT.setScore(parseScore(line));
			} else if (line.startsWith("Persistent")) {
				Trait.CHANGEABLE.setScore(parseScore(line));
			} else if (line.startsWith("Conscientious")) {
				Trait.EXPEDIENT.setScore(parseScore(line));
			}

		}
	}

	private double parseScore(String line) {
		String[] spaceArrays = line.split(" ");
		String score = "";
		double dblScore = -1;
		if (spaceArrays != null) {
			score = spaceArrays[spaceArrays.length - 1];
			dblScore = new Double(score);
		}
		return dblScore;
	}

}
