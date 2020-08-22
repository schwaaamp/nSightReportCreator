package com.emo.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import com.emo.domain.AssessmentScores;
import com.emo.domain.Trait;
import com.emo.importer.ImportScores;

import junit.framework.TestCase;

public class ImportScoresTest extends TestCase {

	@Ignore
	public AssessmentScores getScores() throws IOException {
		ImportScores importer = new ImportScores();
		AssessmentScores scores = new AssessmentScores();
		scores = importer.getScoresFromFile("Zahn Krava");
		return scores;
	}

	@Test
	public void testGetCategories() throws IOException {

		AssessmentScores scores = getScores();		
		assertEquals(8, scores.getCategories().size());
		assertEquals("Self-Perception", scores.getCategories().get(0).getCategoryName());
		assertEquals("Thinking Style", scores.getCategories().get(1).getCategoryName());
		assertEquals("Drive", scores.getCategories().get(2).getCategoryName());
		assertEquals("Stress", scores.getCategories().get(3).getCategoryName());
		assertEquals("Communication", scores.getCategories().get(4).getCategoryName());
		assertEquals("Leadership", scores.getCategories().get(5).getCategoryName());
		assertEquals("Reliability", scores.getCategories().get(6).getCategoryName());
		assertEquals("Aptitude Profile", scores.getCategories().get(7).getCategoryName());
	}
	
	@Test
	public void testSelfPerceptionTraits() throws IOException {

		AssessmentScores scores = getScores();		

		// Self-Perception traits
		List<Trait> selfPerceptionTraits = scores.getCategories().get(0).getCategoryTraits();
		assertEquals("Candid", selfPerceptionTraits.get(0).getName());
		assertEquals("Distorted", selfPerceptionTraits.get(0).getPairName());
		assertEquals(1.0, selfPerceptionTraits.get(0).getScore());

		assertEquals("High Self-Esteem", selfPerceptionTraits.get(1).getName());
		assertEquals("Low Self-Esteem", selfPerceptionTraits.get(1).getPairName());
		assertEquals(3.0, selfPerceptionTraits.get(1).getScore());

		assertEquals("Low Self-Concept", selfPerceptionTraits.get(2).getName());
		assertEquals("High Self-Concept", selfPerceptionTraits.get(2).getPairName());
		assertEquals(6.0, selfPerceptionTraits.get(2).getScore());
		
	}
	
	@Test
	public void testThinkingStyleTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Thinking style traits
		List<Trait> thinkingStyleTraits = scores.getCategories().get(1).getCategoryTraits();
		assertEquals("Emotional", thinkingStyleTraits.get(0).getName());
		assertEquals("Cognitive", thinkingStyleTraits.get(0).getPairName());
		assertEquals(10.0, thinkingStyleTraits.get(0).getScore());

		assertEquals("Analytical", thinkingStyleTraits.get(1).getName());
		assertEquals("Instinctive", thinkingStyleTraits.get(1).getPairName());
		assertEquals(1.0, thinkingStyleTraits.get(1).getScore());

		assertEquals("Logical", thinkingStyleTraits.get(2).getName());
		assertEquals("Imaginative", thinkingStyleTraits.get(2).getPairName());
		assertEquals(6.0, thinkingStyleTraits.get(2).getScore());

		assertEquals("Practical", thinkingStyleTraits.get(3).getName());
		assertEquals("Sensitive", thinkingStyleTraits.get(3).getPairName());
		assertEquals(6.0, thinkingStyleTraits.get(3).getScore());
		
	}
	
	@Test
	public void testDriveTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Drive
		List<Trait> driveTraits = scores.getCategories().get(2).getCategoryTraits();
		assertEquals("Security-oriented", driveTraits.get(0).getName());
		assertEquals("Recognition-oriented", driveTraits.get(0).getPairName());
		assertEquals(5.0, driveTraits.get(0).getScore());

		assertEquals("Cooperative", driveTraits.get(1).getName());
		assertEquals("Competitive", driveTraits.get(1).getPairName());
		assertEquals(5.5, driveTraits.get(1).getScore());

		assertEquals("Rule-bound", driveTraits.get(2).getName());
		assertEquals("Risk-taking", driveTraits.get(2).getPairName());
		assertEquals(4.5, driveTraits.get(2).getScore());
		
	}
	
	@Test
	public void testStressTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Stress
		List<Trait> stressTraits = scores.getCategories().get(3).getCategoryTraits();
		assertEquals("Anxious", stressTraits.get(0).getName());
		assertEquals("Steady", stressTraits.get(0).getPairName());
		assertEquals(6.0, stressTraits.get(0).getScore());

		assertEquals("Tolerant", stressTraits.get(1).getName());
		assertEquals("Intolerant", stressTraits.get(1).getPairName());
		assertEquals(6.0, stressTraits.get(1).getScore());

		assertEquals("Apprehensive", stressTraits.get(2).getName());
		assertEquals("Confident", stressTraits.get(2).getPairName());
		assertEquals(9.0, stressTraits.get(2).getScore());
		
	}
	
	@Test
	public void testCommunicationTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Communication
		List<Trait> communicationTraits = scores.getCategories().get(4).getCategoryTraits();
		assertEquals("Serious", communicationTraits.get(0).getName());
		assertEquals("Enthusiastic", communicationTraits.get(0).getPairName());
		assertEquals(5.5, communicationTraits.get(0).getScore());

		assertEquals("Reserved", communicationTraits.get(1).getName());
		assertEquals("Self-Disclosing", communicationTraits.get(1).getPairName());
		assertEquals(4.5, communicationTraits.get(1).getScore());

		assertEquals("Forthright", communicationTraits.get(2).getName());
		assertEquals("Political", communicationTraits.get(2).getPairName());
		assertEquals(9.0, communicationTraits.get(2).getScore());
		
	}
	
	@Test
	public void testLeadershipTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Leadership
		List<Trait> leadershipTraits = scores.getCategories().get(5).getCategoryTraits();
		assertEquals("Passive", leadershipTraits.get(0).getName());
		assertEquals("Assertive", leadershipTraits.get(0).getPairName());
		assertEquals(6.0, leadershipTraits.get(0).getScore());

		assertEquals("Submissive", leadershipTraits.get(1).getName());
		assertEquals("Aggressive", leadershipTraits.get(1).getPairName());
		assertEquals(7.0, leadershipTraits.get(1).getScore());

		assertEquals("Suspicious", leadershipTraits.get(2).getName());
		assertEquals("Trusting", leadershipTraits.get(2).getPairName());
		assertEquals(5.0, leadershipTraits.get(2).getScore());
		
	}
	
	@Test
	public void testReliabilityTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Reliability
		List<Trait> reliabilityTraits = scores.getCategories().get(6).getCategoryTraits();
		assertEquals("Indifferent", reliabilityTraits.get(0).getName());
		assertEquals("Committed", reliabilityTraits.get(0).getPairName());
		assertEquals(10.0, reliabilityTraits.get(0).getScore());

		assertEquals("Changeable", reliabilityTraits.get(1).getName());
		assertEquals("Persistent", reliabilityTraits.get(1).getPairName());
		assertEquals(6.5, reliabilityTraits.get(1).getScore());

		assertEquals("Expedient", reliabilityTraits.get(2).getName());
		assertEquals("Conscientious", reliabilityTraits.get(2).getPairName());
		assertEquals(7.0, reliabilityTraits.get(2).getScore());
	}
	
	@Test
	public void testAptitudeProfileTraits() throws IOException {

		AssessmentScores scores = getScores();

		// Reliability
		List<Trait> aptitudeTraits = scores.getCategories().get(7).getCategoryTraits();
		assertEquals("Verbal Reasoning", aptitudeTraits.get(0).getName());
		assertEquals(7.5, aptitudeTraits.get(0).getScore());

		assertEquals("Numerical Reasoning", aptitudeTraits.get(1).getName());
		assertEquals(7.0, aptitudeTraits.get(1).getScore());

		assertEquals("Word Knowledge", aptitudeTraits.get(2).getName());
		assertEquals(7.0, aptitudeTraits.get(2).getScore());

		assertEquals("Scanning Accuracy", aptitudeTraits.get(3).getName());
		assertEquals(10.0, aptitudeTraits.get(3).getScore());
	}

}
