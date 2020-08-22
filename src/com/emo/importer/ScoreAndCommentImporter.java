package com.emo.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.emo.constants.Constants;
import com.emo.domain.Trait;

public class ScoreAndCommentImporter {

	private static final String filename = "NSight_Supervisor_Report_For_";

	public String getScoresAndCommentsFromFile(String clientName) throws IOException {

		PDDocument pd = PDDocument.load(new File(Constants.getPath().concat(clientName).concat("/").concat(filename).concat(clientName).concat(".pdf")));
		COSDocument cosDoc = pd.getDocument();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		PDDocument pdDoc = new PDDocument(cosDoc);
		String parsedText = pdfStripper.getText(pdDoc);

		if (parsedText.length() > 0) {
			System.out.println(filename + clientName + ".pdf has been parsed");
			parsedText = deleteFirstPage(parsedText, clientName);
			assignTraitSupervisorComments(parsedText);
		}

		pdDoc.close();
		return parsedText;
	}

	private String deleteFirstPage(String parsedText, String clientName) throws IOException {
		//split where we find page number 1/9, 1/10, etc.
		String[] result = parsedText.split("1\\/\\d+");
		parsedText = result[1];
		String resultBlock = "";
		BufferedReader reader = new BufferedReader(new StringReader(parsedText));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (!line.contains("eridiem") && !line.matches("\\d+\\/\\d+") && !line.contains(clientName + " | ")
					&& !line.equals("THINKING STYLE") && !line.equals("DRIVE") && !line.equals("STRESS")
					&& !line.equals("COMMUNICATION") && !line.equals("LEADERSHIP") && !line.equals("RELIABILITY")) {
				resultBlock = resultBlock.concat(line + "\n");
			}

		}

		System.out.println("Removed page 1 and all unnecessary headers and footers from supervisor report");
		return resultBlock;
	}

	public void assignTraitSupervisorComments(String parsedText) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(parsedText));
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (line.equals(Trait.CANDID.getName() + " " + Trait.CANDID.getPairName())) {
				extractAnalysis(reader, line, Trait.CANDID, "Self-Esteem");
			} else if (line.equals(Trait.HIGH_SELF_ESTEEM.getName() + " " + Trait.HIGH_SELF_ESTEEM.getPairName())) {
				extractAnalysis(reader, line, Trait.HIGH_SELF_ESTEEM, "Self-Concept");
			} else if (line.equals(Trait.LOW_SELF_CONCEPT.getName() + " " + Trait.LOW_SELF_CONCEPT.getPairName())) {
				extractAnalysis(reader, line, Trait.LOW_SELF_CONCEPT, "Emotional/Cognitive");
			} else if (line.equals(Trait.EMOTIONAL.getName() + " " + Trait.EMOTIONAL.getPairName())) {
				extractAnalysis(reader, line, Trait.EMOTIONAL, "Analytical/Instinctive");
			} else if (line.equals(Trait.ANALYTICAL.getName() + " " + Trait.ANALYTICAL.getPairName())) {
				extractAnalysis(reader, line, Trait.ANALYTICAL, "Logical/Imaginative");
			} else if (line.equals(Trait.LOGICAL.getName() + " " + Trait.LOGICAL.getPairName())) {
				extractAnalysis(reader, line, Trait.LOGICAL, "Practical/Sensitive");
			} else if (line.equals(Trait.PRACTICAL.getName() + " " + Trait.PRACTICAL.getPairName())) {
				extractAnalysis(reader, line, Trait.PRACTICAL, "Security/Recognition Oriented");
			} else if (line.equals(Trait.SECURITY_ORIENTED.getName() + " " + Trait.SECURITY_ORIENTED.getPairName())) {
				extractAnalysis(reader, line, Trait.SECURITY_ORIENTED, "Cooperative/Competitive");
			} else if (line.equals(Trait.COOPERATIVE.getName() + " " + Trait.COOPERATIVE.getPairName())) {
				extractAnalysis(reader, line, Trait.COOPERATIVE, "Rule Bound/Risk-Taker");
			} else if (line.equals(Trait.RULE_BOUND.getName() + " " + Trait.RULE_BOUND.getPairName())) {
				extractAnalysis(reader, line, Trait.RULE_BOUND, "Anxious/Steady");
			} else if (line.equals(Trait.ANXIOUS.getName() + " " + Trait.ANXIOUS.getPairName())) {
				extractAnalysis(reader, line, Trait.ANXIOUS, "Tolerant/Intolerant");
			} else if (line.equals(Trait.TOLERANT.getName() + " " + Trait.TOLERANT.getPairName())) {
				extractAnalysis(reader, line, Trait.TOLERANT, "Apprehensive/Confident");
			} else if (line.equals(Trait.APPREHENSIVE.getName() + " " + Trait.APPREHENSIVE.getPairName())) {
				extractAnalysis(reader, line, Trait.APPREHENSIVE, "Serious/Enthusiastic");
			} else if (line.equals(Trait.SERIOUS.getName() + " " + Trait.SERIOUS.getPairName())) {
				extractAnalysis(reader, line, Trait.SERIOUS, "Reserved/Self-Disclosing");
			} else if (line.equals(Trait.RESERVED.getName() + " " + Trait.RESERVED.getPairName())) {
				extractAnalysis(reader, line, Trait.RESERVED, "Forthright/Political");
			} else if (line.equals(Trait.FORTHRIGHT.getName() + " " + Trait.FORTHRIGHT.getPairName())) {
				extractAnalysis(reader, line, Trait.FORTHRIGHT, "Passive/Assertive");
			} else if (line.equals(Trait.PASSIVE.getName() + " " + Trait.PASSIVE.getPairName())) {
				extractAnalysis(reader, line, Trait.PASSIVE, "Submissive/Aggressive");
			} else if (line.equals(Trait.SUBMISSIVE.getName() + " " + Trait.SUBMISSIVE.getPairName())) {
				extractAnalysis(reader, line, Trait.SUBMISSIVE, "Suspicious/Trusting");
			} else if (line.equals(Trait.SUSPICIOUS.getName() + " " + Trait.SUSPICIOUS.getPairName())) {
				extractAnalysis(reader, line, Trait.SUSPICIOUS, "Indifferent/Committed");
			} else if (line.equals(Trait.INDIFFERENT.getName() + " " + Trait.INDIFFERENT.getPairName())) {
				extractAnalysis(reader, line, Trait.INDIFFERENT, "Changeable/Persistent");
			} else if (line.equals(Trait.CHANGEABLE.getName() + " " + Trait.CHANGEABLE.getPairName())) {
				extractAnalysis(reader, line, Trait.CHANGEABLE, "Expedient/Conscientious");
			} else if (line.equals(Trait.EXPEDIENT.getName() + " " + Trait.EXPEDIENT.getPairName())) {
				extractAnalysis(reader, line, Trait.EXPEDIENT, "Verbal Reasoning");
			}else if (line.equals(Trait.VERBAL_REASONING.getName())) {
				extractAnalysis(reader, line, Trait.VERBAL_REASONING, "Numerical Reasoning");
			}else if (line.equals(Trait.NUMERICAL_REASONING.getName())) {
				extractAnalysis(reader, line, Trait.NUMERICAL_REASONING, "Word Knowledge");
			}else if (line.equals(Trait.WORD_KNOWLEDGE.getName())) {
				extractAnalysis(reader, line, Trait.WORD_KNOWLEDGE, "Scanning Accuracy");
			}else if (line.equals(Trait.SCANNING_ACCURACY.getName())) {
				extractAnalysis(reader, line, Trait.SCANNING_ACCURACY, "eof");
			}

		}
	}

	private void extractAnalysis(BufferedReader reader, String line, Trait trait, String stopTrait) throws IOException {
		String result = "";
		// while line is not a decimal score, keep reading lines
		while (!line.matches("^-?\\d*\\.{0,1}\\d+$")) {
			line = reader.readLine();
		}
		
		//assign score
		if(line.matches("^-?\\d*\\.{0,1}\\d+$")) {
			trait.setScore(new Double(line));
		}

		line = reader.readLine();
		while (line != null && !line.startsWith(stopTrait)) {
			result = result.concat(" ").concat(line);
			line = reader.readLine();
		}
		
		trait.setSupervisorComments(result.trim());
	}
}