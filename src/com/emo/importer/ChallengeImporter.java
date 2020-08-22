package com.emo.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.emo.constants.Constants;
import com.emo.domain.Trait;

public class ChallengeImporter {

	private static final String filename = "Level3_Debrief_Report_For_";

	public String getChallengesFromFile(String clientName) throws IOException {

		PDDocument pd = PDDocument.load(new File(
				Constants.getPath().concat(clientName).concat("/").concat(filename).concat(clientName).concat(".pdf")));
		COSDocument cosDoc = pd.getDocument();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		PDDocument pdDoc = new PDDocument(cosDoc);
		String parsedText = pdfStripper.getText(pdDoc);

		if (parsedText.length() > 0) {
			System.out.println(filename + clientName + ".pdf has been parsed");
			parseOnlyChallenges(parsedText);
		}

		pdDoc.close();
		return parsedText;
	}

	// find a score and read the next line until the next line equals a trait name
	private String parseOnlyChallenges(String parsedText) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(parsedText));
		String resultBlock = "";

		String line = null;
		while ((line = reader.readLine()) != null) {
			if (Trait.getNamesAndPairs().contains(line)) {
				resultBlock = resultBlock.concat("\n" + line + "\n");
			}
			if (!Trait.getNamesAndPairs().contains(line) && !line.contains("eridiem")
					&& !line.contains("Page 1 of 1")) {
				resultBlock = resultBlock.concat(line + " ");
			}
		}

		addChallengeToEnum(resultBlock);
		return resultBlock;
	}

	private void addChallengeToEnum(String parsedText) throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(parsedText));
		String challenge = "";
		HashMap<String, String> map = new HashMap<String, String>();

		String line = null;
		String trait = "";
		while ((line = reader.readLine()) != null) {
			if (Trait.getNamesAndPairs().contains(line)) {
				trait = line;
				line = reader.readLine();
				String descriptionArray[] = line.split("\\d+\\.\\d+");
				challenge = descriptionArray[1];
				map.put(trait, challenge.trim());
			}
		}

		// iterate over map and put challenges on trait
		for (Map.Entry<String, String> set : map.entrySet()) {
			for (Trait t : Trait.values()) {
				// if t.getname || t.getpairname matches map key, add challenge
				if(set.getKey().equals(t.getName() )|| set.getKey().equals(t.getPairName())) {
					t.setChallenge(set.getValue());
				}
			}
		}
	}

}
