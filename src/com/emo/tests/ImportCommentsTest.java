package com.emo.tests;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.emo.domain.Trait;
import com.emo.importer.ScoreAndCommentImporter;

import junit.framework.TestCase;

class ImportCommentsTest extends TestCase {
	
	private String clientName = "Zahn Krava";

	@Test
	public void getSupervisorComments() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		String txt = importer.getScoresAndCommentsFromFile(clientName);
		assertNotNull(txt);
		assertTrue(!txt.contains("meridien"));
		assertTrue(!txt.contains("PERSONALITY PROFILE"));
		assertTrue(!txt.contains("2/10"));
		assertTrue(!txt.contains("THINKING STYLE"));
		assertTrue(!txt.contains("DRIVE"));
		assertTrue(!txt.contains("STRESS"));
		assertTrue(!txt.contains("COMMUNICATION"));
		assertTrue(!txt.contains("LEADERSHIP"));
		assertTrue(!txt.contains("RELIABILITY"));
		assertTrue(txt.contains("Slightly more imaginative than logical"));
	}

	@Test
	public void testAssignSupervisorComments() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		String txt = importer.getScoresAndCommentsFromFile(clientName);
		assertNotNull(txt);
		assertEquals(
				"Zahn Krava's answers reveal a response to this group of questions that is strongly candid in manner. The results of this evaluation show no apparent manipulation that may have distorted the findings. The answers are strongly held as straightforward and frank. These answers indicate that the following results validly, reliably, and substantially reflect Zahn Krava's personality. A primary ability to comprehend and respond to written questions has been demonstrated.",
				Trait.CANDID.getSupervisorComments());
		assertEquals(
				"Zahn tends to have a positive view of self. Although the score does not detect an excessively positive view, it still draws attention to the need for self-discovery. While a positive view of self is certainly preferred over a negative view, these scores reflect a slight inability to see negative personality characteristics in relation to others. With this in mind, we would suggest Zahn set aside some time for self-critique with the goal of identifying any areas, which with slight improvement, would prove beneficial to Zahn’s overall growth and development.",
				Trait.HIGH_SELF_ESTEEM.getSupervisorComments());
		assertEquals(
				"Zahn's self-image appears to be somewhat realistic. This mid-range score indicates an ability to see oneself in an appropriate manner that does not stray into the realm of lack of confidence when handling familiar situations. Zahn neither underestimates nor excessively overestimates Zahn’s capabilities. This well-formed self-image is important in order to maintain confidence and balance in the work place. Zahn's mid-range score in self-concept reflects a good capacity to display this positive attribute to others.",
				Trait.LOW_SELF_CONCEPT.getSupervisorComments());
		assertEquals(
				"A brilliantly high score in the cognitive decision-making dimension reflects an impressive ability to incorporate restraint, controlled thinking, and intellect when making decisions. Zahn's score reflects a superior capability to make decisions based on aptitude. Zahn will usually make sound decisions based on an overall extent of knowledge and experience, and will generally be regarded as someone to have around in chaotic or critical periods. Individuals with this extremely high cognitive decision-making score will most likely exhibit the ability to make judgments based on intelligence as related to the job specification.",
				Trait.EMOTIONAL.getSupervisorComments());
		assertEquals(
				"Individuals with an extremely low score in the Analytical/Instinctive thinking dimension are the kind of problem solvers who will examine and investigate essential features, to reach a conclusion. For extreme analytical scorers like Zahn, this process will take an extended amount of time, but decisions are determined through a very thorough elemental process. This strong need to look at things in detail to discover how they happen will be a time-consuming process. This individual will need the proper amount of duration needed in order to review each and every alternative available.",
				Trait.ANALYTICAL.getSupervisorComments());
		assertEquals(
				"Slightly more imaginative than logical, Zahn appears able to incorporate some inventive approaches in decision-making. Zahn has a slightly unorthodox thinking style that may be observed when calling on the imagination to solve problems when the former method fails. Individuals with this score are not so creative that the need to abandon the traditional method is a particularly strong driver. These individuals are somewhat philosophical when reasoning and have a slightly more prominent skill in imagination. Zahn’s Cognitive and Analytical Skills will somewhat influence this characteristic.",
				Trait.LOGICAL.getSupervisorComments());
		assertEquals(
				"High mid-range on the Sensitivity scale, Zahn will combine a no-nonsense manner with more prominent sensitive problem-solving skills. Zahn will exhibit some practicality in their decision-making, yet interact with others in a fairly gentle fashion. Others will commonly perceive Zahn as considerate. Especially in delicate situations, Zahn will react in a concerned fashion and express compassion for others. Individuals with a high mid-range score will be very responsive to being handled in a constructive fashion.",
				Trait.PRACTICAL.getSupervisorComments());
		assertEquals(
				"Individuals who score mid-range on the Security/Recognition scale will prefer a complete balance between security and recognition regarding compensation. Individuals like Zahn will generally desire to feel safe and protected, but will also enjoy added recognition for productive efforts. Zahn will want to step in directions that will increase the acknowledgment of achievements, and Zahn will also want to have a basis of security from which to build on. Acknowledgment of Zahn's efforts can come in many forms, such as bonuses, commissions, or a prestigious job title, in addition to a base pay for added security in a position.",
				Trait.SECURITY_ORIENTED.getSupervisorComments());
		assertEquals(
				"With a mid-range score on the Cooperative/Competitive scale, Zahn will be a team-spirited and goal-oriented individual. Being balanced in this area, Zahn will not only enjoy competing against team members, but will also be regarded as a good team player. In business, Zahn will enjoy servicing other people's needs as well as increasing the bottom line of production. Well-balanced in this area, Zahn will be most effective when working with others to set goals. Zahn can also increase the competitive spirit by competing against former best efforts.",
				Trait.COOPERATIVE.getSupervisorComments());
		assertEquals(
				"With a mid-range score on being rule-bound and a risk-taker, Zahn enjoys having a balance between being a \"by the book\" performer and being adventurous. Zahn leans in the direction of being more rule-bound, but will step out on a limb from time to time and take an occasional risk for potential gain. Zahn will follow company policy and procedure, but can be bold from time to time. Zahn is neither overly conservative nor overly liberal, and will take risks as long as they are non-threatening to a position at work.",
				Trait.RULE_BOUND.getSupervisorComments());
		assertEquals(
				"Scoring as balanced in the Anxious/Steady factor, Zahn is for the most part steady, but not entirely anxiety free. With a mid-range score, Zahn leans towards being generally composed and fixated, but can occasionally feel tension. When frustration occurs, Zahn will need to move about to relieve any anxiety that might be felt at the time. This will usually take place when Zahn needs to meet an important deadline or is feeling extremely restricted from movement. Individuals who have occasional tension can also exhibit stress if confined for long periods of time. Zahn will be able to work on detailed and tedious tasks, but will also need to move about to expend any built-up energy.",
				Trait.ANXIOUS.getSupervisorComments());
		assertEquals(
				"Being high mid-range in tolerance, Zahn remains understanding and generous in normal settings. However, as pressure, stress, or tension increases Zahn may exhibit some frustration. Zahn is likely to become slightly aggravated when needing to meet an important deadline and obstacles stand in the way. If impatience occurs, it will most likely take place when working with others who lack the drive to complete assignments in a timely fashion.",
				Trait.TOLERANT.getSupervisorComments());
		assertEquals(
				"Zahn has a very healthy degree of self-confidence and is not prone to worry much about anything. This strong resilience is displayed through actions of self-assurance and independence. Zahn will be very self-reliant and handle whatever worries life seems to bring. This strong spirit of determination and fearlessness will make others who are less confident feel more self-assured and less apprehensive. Most individuals who possess this high amount of confidence will be looked to in times of confusion for hope and certainty. Feeling almost invulnerable, individuals scoring in this range will believe that they can succeed at almost anything.",
				Trait.APPREHENSIVE.getSupervisorComments());
		assertEquals(
				"Being equally as serious as light-hearted in nature, Zahn appears to be well-balanced in both characteristics. Zahn is able to see the serious side of matters in situations that require a more business-like approach. In less important situations, Zahn will exhibit a good amount of enthusiasm and will show a sense of humor in a cheerful, light-hearted fashion. In instances that are more significant, Zahn will exhibit a somewhat solemn, reflective behavior. This mid-range score shows a well-balanced and positive amount of humor.",
				Trait.SERIOUS.getSupervisorComments());
		assertEquals(
				"Zahn scores slightly towards being reserved. Zahn has a fair desire to communicate, but shares confidences only with those regarded as true friends and close associates. Zahn has little desire to dominate conversations, and while some contact with others is desired for contentment, constant person-to-person contact is not necessary for job satisfaction. With a fairly moderate desire to communicate and a likewise respect for allowing others to express themselves, a specific job description will need to correlate to this level of preferred interaction. Typically, individuals who score in this range are socially reactive. Zahn’s true strength lies in the ability to listen and then respond.",
				Trait.RESERVED.getSupervisorComments());
		assertEquals(
				"When conversing with others, Zahn will be highly diplomatic. Zahn tends to know how to handle social situations in an effective manner. Being political, Zahn can tell others what might need to be heard, rather than openly discussing contrasting viewpoints. These extremely strategic abilities are used in communication to discreetly get a point across without opposition. This approach to conversation can have a positive effect on others. However, Zahn will need to be careful not to make statements or promises to appease others that cannot ultimately be lived up to. Zahn will appear to be quite worldly to make a positive impression on others. This level of diplomacy can be an asset if used with a likewise large degree of integrity.",
				Trait.FORTHRIGHT.getSupervisorComments());
		assertEquals(
				"When interacting with others, Zahn appears able to stand ground on matters of importance. Zahn will speak out if any personal rights are being violated, but may yield in situations that are less significant or meaningful. Zahn generally will not be regarded as someone who instigates conflict. Zahn will generally correlate assertiveness to the importance of the issue at hand. The end result will be to choose battles carefully. In a position that holds the authority to delegate or maintain control, and if Zahn feels the responsibility to be assertive, then taking charge will feel comfortable.",
				Trait.PASSIVE.getSupervisorComments());
		assertEquals(
				"Zahn can become aggressive on matters of personal importance. If trying to get a point across that is deemed significant, Zahn may reveal a rather high dominance level. Zahn holds definitive opinions on matters and may at times appear obstinate. Zahn will prefer to be in control, which may be a challenge initially, if in a position that does not allow for the expression of a high dominance level. Zahn will be able to delegate job responsibilities, if needed, but can reveal an aggressive nature if opposed. Zahn will most likely exhibit aggressive behavior when being challenged personally, or when defending something deemed important. This type of behavior will most likely be revealed when feeling \"backed into a corner,\" or when feeling personally attacked, or someone important is being attacked, verbally or otherwise.",
				Trait.SUBMISSIVE.getSupervisorComments());
		assertEquals(
				"Zahn will have a measured degree of trust for others, but not to the point of being overly negative. Zahn will want to discern whether or not people are honorable and loyal before having faith in them, but after this has been established, Zahn will be less likely to question motives. If the benefit of the doubt has been given once and it was taken advantage of, it is very unlikely Zahn will allow it to occur a second time. Zahn will question when things appear to be out of the ordinary, and will trust when trust is due in most instances.",
				Trait.SUSPICIOUS.getSupervisorComments());
		assertEquals(
				"Extremely committed by nature, Zahn has a very strong set of values that they adhere to on a consistent basis. They will be committed to several things, including their job and family. They will be considered one who can be counted on to help friends and associates in time of need. Their extremely high degree of loyalty makes them a dependable person, and they will be regarded as one who can be trusted to remain true to their word. Their allegiances are considered to be consistent to their set of beliefs. Their devotion to their own set of values will appear extremely constant, and sometimes unwavering. This degree of consistency will be regarded as commendable. They may appear inflexible, however, to those who are less consistent in their beliefs. It will be extremely difficult for them to conform to others’ values, because of their already strong and sturdy standards and ethics.",
				Trait.INDIFFERENT.getSupervisorComments());
		assertEquals(
				"Zahn exhibits a tendency toward structured and planned work habits with strong follow-through, showing a preference to face and power through roadblocks and difficulty. Zahn may, however, at times struggle in environments of rapid change and volatility that require agility and flexibility. This can, however, be influenced by expectations and the amount of flexibility in the environment.",
				Trait.CHANGEABLE.getSupervisorComments());
		assertEquals(
				"Being service-motivated, Zahn will persevere in attempting to meet job requirements. Job functions will most likely be completed efficiently. Zahn will be duty-oriented and will complete responsibilities in a way that corresponds with the rules and structures within the organization. Zahn will be concerned about the obligations within a specific job description, and will want to work in a manner that is consistent with expectations. It is unlikely that Zahn will be informal in the approach to completing projects. Zahn will want instead to be somewhat dedicated, being careful to work in a manner consistent with any previous success within the organization.",
				Trait.EXPEDIENT.getSupervisorComments());
		assertEquals(
				"Zahn has strong abstract reasoning skills. There is a comfort and skill in concrete reasoning, but a preference for dealing with more hypothetical ideas. Zahn is well-read and has good reading comprehension. Zahn responded in a manner that reflected a wide range of knowledge and background information. There is a strong ability to separate essential from non-essential information, to work with complex data, and to reach decisions based upon data presented. Zahn works best when challenged intellectually in the work environment. If required to work with mundane tasks for a lengthy period of time, Zahn may become disengaged.",
				Trait.VERBAL_REASONING.getSupervisorComments());
		assertEquals(
				"Zahn appears to have highly developed numerical reasoning and problem-solving skills. Zahn has an excellent capacity to visualize and organize numerical concepts and will have few if any problems understanding and working with concepts such as percentages, fractions, ratios, and basic geometry.",
				Trait.NUMERICAL_REASONING.getSupervisorComments());
		assertEquals(
				"Zahn has excellent receptive and expressive vocabularies, and is in the top quartile in verbal fluency. Zahn has been exposed to words that are used in the workplace as well as in social and educational circles. Zahn can be very persuasive with this level of verbal understanding, and is able to communicate extremely well when desiring to do so.",
				Trait.WORD_KNOWLEDGE.getSupervisorComments());
		assertEquals(
				"Zahn appears to have the visual perception necessary to accurately deal with numbers, symbols, and signs. Individuals who score high in visual perception are generally able to work quickly and accurately when handling details, and are also typically good at proofreading their work. This level of visual acuity appears to be above average for most jobs that require working with detailed information and data. Zahn's scores indicate a superior ability to work with numbers, symbols, and signs without needing others to proofread completed work.",
				Trait.SCANNING_ACCURACY.getSupervisorComments());
	}
	
	@Test
	public void testSelfPerceptionTraits() throws IOException {

		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(1.0, Trait.CANDID.getScore());
		assertEquals(3.0, Trait.HIGH_SELF_ESTEEM.getScore());
		assertEquals(6.0, Trait.LOW_SELF_CONCEPT.getScore());		
	}
	
	@Test
	public void testThinkingStyleTraits() throws IOException {

		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(10.0, Trait.EMOTIONAL.getScore());
		assertEquals(1.0, Trait.ANALYTICAL.getScore());
		assertEquals(6.0, Trait.LOGICAL.getScore());	
		assertEquals(6.0, Trait.PRACTICAL.getScore());		
	}
	
	@Test
	public void testDriveTraits() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(5.0, Trait.SECURITY_ORIENTED.getScore());
		assertEquals(5.5, Trait.COOPERATIVE.getScore());
		assertEquals(4.5, Trait.RULE_BOUND.getScore());			
	}
	
	@Test
	public void testStressTraits() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(6.0, Trait.ANXIOUS.getScore());
		assertEquals(6.0, Trait.TOLERANT.getScore());
		assertEquals(9.0, Trait.APPREHENSIVE.getScore());		
	}
	
	@Test
	public void testCommunicationTraits() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(5.5, Trait.SERIOUS.getScore());
		assertEquals(4.5, Trait.RESERVED.getScore());
		assertEquals(9.0, Trait.FORTHRIGHT.getScore());
	}
	
	@Test
	public void testLeadershipTraits() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(6.0, Trait.PASSIVE.getScore());
		assertEquals(7.0, Trait.SUBMISSIVE.getScore());
		assertEquals(5.0, Trait.SUSPICIOUS.getScore());	
	}
	
	@Test
	public void testReliabilityTraits() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(10.0, Trait.INDIFFERENT.getScore());
		assertEquals(6.5, Trait.CHANGEABLE.getScore());
		assertEquals(7.0, Trait.EXPEDIENT.getScore());
	}
	
	@Test
	public void testAptitudeProfileTraits() throws IOException {
		ScoreAndCommentImporter importer = new ScoreAndCommentImporter();
		importer.getScoresAndCommentsFromFile(clientName);
		assertEquals(7.5, Trait.VERBAL_REASONING.getScore());
		assertEquals(7.0, Trait.NUMERICAL_REASONING.getScore());
		assertEquals(7.0, Trait.WORD_KNOWLEDGE.getScore());	
		assertEquals(10.0, Trait.SCANNING_ACCURACY.getScore());	
	}

	@Test
	public void testDecimalRegex() {
		String testString = "4.0";
		Pattern p = Pattern.compile("^-?\\d*\\.{0,1}\\d+$");
		Matcher m = p.matcher(testString);
		assertTrue(m.find());
	}

}
