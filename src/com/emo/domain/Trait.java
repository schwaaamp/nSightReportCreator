package com.emo.domain;

import java.util.ArrayList;
import java.util.List;

public enum Trait {

	CANDID("Candid", "Distorted", "Candor",
			"How straightforward, sincere, and forthright an individual has answered assessment questions, as well the level of validity of subsequent answers."),
	HIGH_SELF_ESTEEM("High Self-Esteem", "Low Self-Esteem", "Self Esteem",
			"How an individual views themselves in comparison to others."),
	LOW_SELF_CONCEPT("Low Self-Concept", "High Self-Concept", "Self Concept",
			"How an individual views themselves alone and away from others' influences."),
	EMOTIONAL("Emotional", "Cognitive", "Decision making style: Emotional vs Cognitive",
			"The degree to which one makes emotional/intuitive or cognitive/intellectual decisions when solving problems."),
	ANALYTICAL("Analytical", "Instinctive", "Analytical vs Instinctive Thinking",
			"The degree to which one analyzes details or goes with gut instinct as well as the speed of decision-making."),
	LOGICAL("Logical", "Imaginative", "Logical vs Imaginative Thinking",
			"The degree to which one tends toward conventional or creative solutions."),
	PRACTICAL("Practical", "Sensitive", "Practical vs Sensitive",
			"The degree to which one accommodates the feelings and experiences of others."),
	SECURITY_ORIENTED("Security-oriented", "Recognition-oriented", "Security vs Recognition oriented",
			"The manner in which one seeks rewards for efforts."),
	COOPERATIVE("Cooperative", "Competitive", "Cooperative vs Competitive",
			"How one is oriented towards others in shared endeavors."),
	RULE_BOUND("Rule-bound", "Risk-taking", "Rule Bound vs Risk Taker",
			"The degree to which one seeks or avoids risk, structure, and ambiguity."),
	ANXIOUS("Anxious", "Steady", "Anxious or Steady",
			"How one experiences and interprets challenge, frustration, and ambiguity."),
	TOLERANT("Tolerant", "Intolerant", "Tolerant vs Intolerant",
			"How one holds oneself and one's needs with respect to the needs and styles of others."),
	APPREHENSIVE("Apprehensive", "Confident", "Apprehensive vs Confident",
			"The degree to which one experiences worry, stress, and powerlessness."),
	SERIOUS("Serious", "Enthusiastic", "Serious vs Enthusiastic",
			"The degree of seriousness or levity one sees and prefers in situations and interactions."),
	RESERVED("Reserved", "Self-Disclosing", "Reserved vs Self Disclosing",
			"The amount of social interaction one prefers."),
	FORTHRIGHT("Forthright", "Political", "Forthright vs Political",
			"The degree of directness or diplomacy one prefers."),
	PASSIVE("Passive", "Assertive", "Passive vs Assertive", "Preference for and orientation to conflict."),
	SUBMISSIVE("Submissive", "Aggressive", "Submissive vs Aggressive",
			"One's preference for and orientation to power."),
	SUSPICIOUS("Suspicious", "Trusting", "Suspicious vs Trusting",
			"Reflects one's degree of trust in others and in organizations."),
	INDIFFERENT("Indifferent", "Committed", "Indifferent vs Committed",
			"Degree of commitment to relationships and organizations."),
	CHANGEABLE("Changeable", "Persistent", "Changeable vs Persistent",
			"Degree of flexibility within or commitment to completion of projects and goals."),
	EXPEDIENT("Expedient", "Conscientious", "Expedient vs Conscientiousness",
			"Degree of commitment to quality or speed in work output."),
	ABSTRACT("Abstract", "Concrete", "Concrete vs Abstract Reasoning",
			"Overall reasoning style and comfort with directed versus autonomous problem solving and learning."),
	VERBAL_REASONING("Verbal Reasoning", "Concrete vs Abstract",
			"Overall reasoning style and comfort with directed versus autonomous problem solving and learning."),
	NUMERICAL_REASONING("Numerical Reasoning", "Numerical Reasoning",
			"Comfort, experience, and capability with number and symbol-based problem solving."),
	WORD_KNOWLEDGE("Word Knowledge", "Vocabulary",
			"Word knowledge indicates comfort and capability with word recognition, written communication, and verbal learning."),
	SCANNING_ACCURACY("Scanning Accuracy", "Scanning Accuracy",
			"Reflects capability and speed in symbolic pattern recognition.");

	private String name = "";
	private String pairName = "";
	private String title = "";
	private String description = "";
	private double score = 0.0;
	private String supervisorComments = "";
	private String challenge;

	Trait(String name) {
		this.name = name;
	}

	Trait(String name, String pair) {
		this.name = name;
		this.pairName = pair;
	}

	Trait(String name, String title, String description) {
		this.name = name;
		this.title = title;
		this.description = description;
	}

	Trait(String name, String pairName, String title, String description) {
		this.name = name;
		this.pairName = pairName;
		this.title = title;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPairName() {
		return pairName;
	}

	public void setPairName(String pairName) {
		this.pairName = pairName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getSupervisorComments() {
		return supervisorComments;
	}

	public void setSupervisorComments(String comments) {
		this.supervisorComments = comments;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public static List<String> getNamesAndPairs() {
		List<String> names = new ArrayList<String>();
		for (Trait t : Trait.values()) {
			names.add(t.getName());
			names.add(t.getPairName());
		}
		return names;
	}

	public static List<Category> assignTraitsToCategory() {
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

	public static boolean isTraitMemberOfCategory(Trait t, Category c) {
		List<Category> categories = assignTraitsToCategory();
		
		if(c.getCategoryTraits().contains(t)) {
			return true;
		}
		return false;
		
		
//		for(Category cat : categories) {
//			if(cat.equals(c.APTITUDE_PROFILE)) {
//				if(cat.getCategoryTraits().contains(c)) {
//					return true;
//				}
//			}
//		}
//		return false;
	}

}
