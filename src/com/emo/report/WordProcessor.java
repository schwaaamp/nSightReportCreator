package com.emo.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFAbstractNum;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.emo.constants.Constants;
import com.emo.domain.Category;
import com.emo.domain.Client;
import com.emo.domain.Trait;
import com.itextpdf.text.Font.FontFamily;

public class WordProcessor {

	private static final String HOR_LINE = "resources/images/horizontal_line.png";
	private static final String DEFAULT_FONT_COLOR = "000000";
	private static final String DARK_RED_FONT_COLOR = "781f12";
	private static final String DEFAULT_FONT_FAMILY = "Times New Roman";
	private static final String HELVETICA_NEUE = "Helvetica Neue";
	private static final int DEFAULT_FONT_SIZE = 11;
	private static final int H1_FONT_SIZE = 30;
	private static final int H2_FONT_SIZE = 18;
	private static final int H3_FONT_SIZE = 13;

	static String cTAbstractNumBulletXML = "<w:abstractNum xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:abstractNumId=\"0\">"
			+ "<w:multiLevelType w:val=\"hybridMultilevel\"/>"
			+ "<w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Wingdings\" w:hAnsi=\"Wingdings\" w:hint=\"default\"/></w:rPr></w:lvl>"
			+ "<w:lvl w:ilvl=\"1\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"-\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"1440\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Courier New\" w:hAnsi=\"Courier New\" w:cs=\"Courier New\" w:hint=\"default\"/></w:rPr></w:lvl>"
			+ "<w:lvl w:ilvl=\"2\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"2160\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Symbol\" w:hAnsi=\"Symbol\" w:hint=\"default\"/></w:rPr></w:lvl>"
			+ "</w:abstractNum>";

	public void createFinalReport(Client client) throws IOException, InvalidFormatException, XmlException {

		String fullPath = Constants.getPath().concat(client.getClientName()).concat("/")
				.concat(client.getClientName().concat(" Final Review autogen.docx"));

		XWPFDocument doc = new XWPFDocument();
		FileOutputStream out = new FileOutputStream(new File(fullPath));

		// create footer
		addFooter(client, doc);

		// add primary report image
		addHeaderImage(doc);
		insertSpacerParagraph(doc);

		writeTextParagraph(doc, false, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR,
				"Even the most successful people in business use coaches.", ParagraphAlignment.LEFT,
				DEFAULT_FONT_FAMILY);
		writeTextParagraph(doc, true, true, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR,
				"The fact is, they are successful because they get formal coaching", ParagraphAlignment.LEFT,
				DEFAULT_FONT_FAMILY);

		insertSpacerParagraph(doc);
		XWPFParagraph p = doc.createParagraph();
		XWPFRun run1 = p.createRun();
		writeText(run1, true, true, H1_FONT_SIZE, DEFAULT_FONT_COLOR, DEFAULT_FONT_FAMILY, "nSight Assessment: ");

		XWPFRun run2 = p.createRun();
		writeText(run2, true, true, H1_FONT_SIZE, DARK_RED_FONT_COLOR, DEFAULT_FONT_FAMILY, client.getClientName());

		insertSpacerParagraph(doc);
		insertSpacerParagraph(doc);

		// toc
		insertSectionHeader(doc, Headers.TOC, client);
		for (int i = 1; i < Headers.values().length; i++) {
			writeTextParagraph(doc, false, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR,
					i + ". " + Headers.values()[i].getTitle(), ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);
		}

		// exec summary
		insertSpacerParagraph(doc);
		insertSectionHeader(doc, Headers.EXEC_SUMMARY, client);

		// results detail
		insertSpacerParagraph(doc);
		insertSectionHeader(doc, Headers.RESULTS_DETAIL, client);

		for (Category c : Category.values()) {
			insertSpacerParagraph(doc);
			writeTextParagraph(doc, true, false, H2_FONT_SIZE, DEFAULT_FONT_COLOR, c.getCategoryName(),
					ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);
			for (Trait t : c.getCategoryTraits()) {
				insertSpacerParagraph(doc);
				writeTextParagraph(doc, true, false, H3_FONT_SIZE, DEFAULT_FONT_COLOR, t.getTitle(),
						ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);
				insertSpacerParagraph(doc);

				// add trait description
				writeTextParagraph(doc, false, true, H3_FONT_SIZE, DEFAULT_FONT_COLOR, t.getDescription(),
						ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);
				insertSpacerParagraph(doc);

				// add spectrum image
				XWPFParagraph paragraph = doc.createParagraph();
				XWPFRun run = paragraph.createRun();
				String img = Constants.getPath().concat(client.getClientName()).concat("/images/")
						.concat(t.getName().replaceAll("\\s", "").concat(".png"));
				run.addPicture(new FileInputStream(img), XWPFDocument.PICTURE_TYPE_PNG, img, Units.toEMU(468),
						Units.toEMU(37));
				insertSpacerParagraph(doc);

				// add supervisor report comments
				String supervisorComments = improveGrammar(client, t.getSupervisorComments());
				writeTextParagraph(doc, false, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR, supervisorComments,
						ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);
				insertSpacerParagraph(doc);

				// add challenges/areas to watch for if exists on trait
				if (t.getChallenge() != null) {
					writeTextParagraph(doc, true, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR, "Areas to watch for:",
							ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);

					writeTextParagraph(doc, false, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR, t.getChallenge(),
							ParagraphAlignment.LEFT, DEFAULT_FONT_FAMILY);
					insertSpacerParagraph(doc);
				}
			}
		}

		// leadership style
		insertSpacerParagraph(doc);
		insertSectionHeader(doc, Headers.LEADERSHIP_STYLE, client);

		// development plan
		insertSpacerParagraph(doc);
		insertSectionHeader(doc, Headers.DEVELOPMENT_PLAN, client);

		// next steps
		insertSpacerParagraph(doc);
		insertSectionHeader(doc, Headers.NEXT_STEPS, client);
		insertSpacerParagraph(doc);

		// insert bulleted list
		insertNextStepsBullets(doc);

		// final quote
		insertSpacerParagraph(doc);
		insertSpacerParagraph(doc);
		insertSpacerParagraph(doc);

		writeTextParagraph(doc, true, true, 14, DARK_RED_FONT_COLOR,
				"\"In any given moment, we have two options: \n"
						+ "Step forward into growth or step back into safety\"",
				ParagraphAlignment.CENTER, HELVETICA_NEUE);

		insertSpacerParagraph(doc);
		writeTextParagraph(doc, false, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR, "- Abraham Maslow -",
				ParagraphAlignment.CENTER, HELVETICA_NEUE);

		// Close document
		doc.write(out);
		out.close();
		doc.close();
		System.out.println(fullPath.concat(" written successfully"));
	}

	private void insertNextStepsBullets(XWPFDocument doc) throws XmlException {
		XWPFParagraph p = doc.createParagraph();
		CTNumbering cTNumbering = CTNumbering.Factory.parse(cTAbstractNumBulletXML);
		CTAbstractNum cTAbstractNum = cTNumbering.getAbstractNumArray(0);
		XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
		XWPFNumbering numbering = doc.createNumbering();
		BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
		BigInteger numID = numbering.addNum(abstractNumID);

		String[][] bulletArray = {
				{ "Confirmation: ", "Confirm the evaluation and areas of development with John in person." },
				{ "Choose focus areas: ", "Choose the top 3 development areas to address." },
				{ "Create Development plan:  ", "Create a development plan for the top 3 areas." },
				{ "Support Development plan: ", "Work together to implement development plan." }, };

		for (int i = 0; i < 4; i++) {
			p = doc.createParagraph();
			p.setNumID(numID);

			XWPFRun r1 = p.createRun();
			writeText(r1, true, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR, DEFAULT_FONT_FAMILY, bulletArray[i][0]);

			XWPFRun r2 = p.createRun();
			writeText(r2, false, false, DEFAULT_FONT_SIZE, DEFAULT_FONT_COLOR, DEFAULT_FONT_FAMILY, bulletArray[i][1]);
		}
	}

	private void addFooter(Client client, XWPFDocument doc) throws IOException {
		XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
		if (headerFooterPolicy == null)
			headerFooterPolicy = doc.createHeaderFooterPolicy();
		XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);

		XWPFParagraph footerParagraph = footer.getParagraphArray(0);
		if (footerParagraph == null)
			footerParagraph = footer.createParagraph();

		String footerText = "Prepared for " + client.getClientName()
				+ " by Rocket Fuel for Careers  |  © Rocket Fuel for Careers 2020  |  All Rights Reserved";

		XWPFRun footerRun = footerParagraph.createRun();
		footerRun.setFontSize(6);
		footerRun.setFontFamily(HELVETICA_NEUE);
		footerRun.setText(footerText);
	}

	private void writeTextParagraph(XWPFDocument doc, boolean bold, boolean italic, int fontSize, String hexColor,
			String text, ParagraphAlignment a, String fontFamily) {
		XWPFParagraph p = doc.createParagraph();
		p.setAlignment(a);
		XWPFRun r = p.createRun();
		writeText(r, bold, italic, fontSize, hexColor, fontFamily, text);
	}

	private void writeText(XWPFRun r, boolean bold, boolean italic, int fontSize, String hexColor, String fontFamily,
			String text) {
		r.setBold(bold);
		r.setItalic(italic);
		r.setFontSize(fontSize);
		r.setColor(hexColor);
		r.setFontFamily(fontFamily);
		r.setText(text);
	}

	private void addHeaderImage(XWPFDocument doc) throws InvalidFormatException, FileNotFoundException, IOException {
		XWPFParagraph paragraph = doc.createParagraph();
		XWPFRun run = paragraph.createRun();
		String imgFile = "resources/images/report_header.png";
		run.addPicture(new FileInputStream(imgFile), XWPFDocument.PICTURE_TYPE_PNG, imgFile, Units.toEMU(468),
				Units.toEMU(182));
	}

	private void insertSpacerParagraph(XWPFDocument doc) {
		XWPFParagraph spacerParagraph = doc.createParagraph();
		spacerParagraph.createRun();
	}

	private void insertSectionHeader(XWPFDocument doc, Headers h, Client client)
			throws InvalidFormatException, IOException, FileNotFoundException {
		XWPFParagraph p = doc.createParagraph();
		p.setVerticalAlignment(TextAlignment.CENTER);
		XWPFRun r = p.createRun();
		r.setBold(true);
		r.setFontSize(H2_FONT_SIZE);
		String img = h.getImg();
		r.addPicture(new FileInputStream(img), XWPFDocument.PICTURE_TYPE_PNG, img, Units.toEMU(24), Units.toEMU(27));
		r.setText("  " + h.getTitle());
		r.addPicture(new FileInputStream(HOR_LINE), XWPFDocument.PICTURE_TYPE_PNG, HOR_LINE, Units.toEMU(550),
				Units.toEMU(5));
	}

	private String improveGrammar(Client client, String paragraph) {
		// their should be his/her
		// they should be he/she
		// them should be him/her
		String uppercasePronoun = client.getGender().equals("m") ? "His" : "Her";
		String lowercasePronoun = client.getGender().equals("m") ? " his" : " her";
		paragraph = paragraph.replace("Their", uppercasePronoun);
		paragraph = paragraph.replace(" their", lowercasePronoun);
		paragraph = paragraph.replace("oneself", lowercasePronoun.concat("self"));

		// replace full string usage of a person's name
		String firstname = client.getClientName().substring(0, client.getClientName().indexOf(" "));
		paragraph = paragraph.replace(client.getClientName().concat("'s"), firstname.concat("'s"));
		paragraph = paragraph.replace(client.getClientName(), firstname);

		paragraph = paragraph.replace(" this individual ", client.getGender().equals("m") ? " he " : " she ");
		paragraph = paragraph.replace("This individual's ", client.getGender().equals("m") ? "His " : "Her ");
		paragraph = paragraph.replace(" this individual's ", client.getGender().equals("m") ? " his " : " her ");

		// need to determine how many times the client's name is used and replace with
		// him/her some
		// difficult because sometimes the pronoun will be different (his/her, him/her)
		return paragraph;
	}

}
