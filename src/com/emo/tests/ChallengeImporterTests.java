package com.emo.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.emo.domain.Trait;
import com.emo.importer.ChallengeImporter;

class ChallengeImporterTests {

	@Test
	void Level3DebriefReportImporterTest() throws IOException {
		ChallengeImporter importer = new ChallengeImporter();
		String text = importer.getChallengesFromFile("Zahn Krava");
		assertNotNull(text);
		assertEquals("May fail to recognize matters of the heart in self and others. May be perceived as coldly rational.", Trait.EMOTIONAL.getChallenge());
		assertEquals("May stick with people, causes, and organizations beyond what is reasonable and healthy. May be co- dependent.",  Trait.INDIFFERENT.getChallenge());
		assertEquals("May suffer “paralysis by analysis,” and struggle to make decisions when speed is necessary and there is inadequate data.", Trait.ANALYTICAL.getChallenge());
		assertEquals("May over-estimate security and under-estimate threat, exposing self and others to potential failure and harm.", Trait.APPREHENSIVE.getChallenge());
		assertEquals("May come across as disingenuous or dishonest, and may struggle to deliver unpleasant news.", Trait.FORTHRIGHT.getChallenge());
		assertEquals("May disengage when under-challenged. May introduce unnecessary complexity when following direction is best. May get frustrated with others who can’t keep up.", Trait.ABSTRACT.getChallenge());
		assertEquals("May alienate by coming across as stern, dictatorial, domineering. Hoarding control may limit growth of others.", Trait.SUBMISSIVE.getChallenge());
		assertEquals("May succumb to perfectionism, struggling with deadlines and causing undue stress when expedience is required.", Trait.EXPEDIENT.getChallenge());
		assertEquals("May set oneself up for failure by unrealistically overestimating self compared to others.", Trait.HIGH_SELF_ESTEEM.getChallenge());
	}

}
