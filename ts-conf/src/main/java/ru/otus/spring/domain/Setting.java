package ru.otus.spring.domain;

public class Setting {
	private int id;
	private String levelName;
	private int requiredNumberResponses;
	private String reward;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getRequiredNumberResponses() {
		return requiredNumberResponses;
	}

	public void setRequiredNumberResponses(int requiredNumberResponses) {
		this.requiredNumberResponses = requiredNumberResponses;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		return "Setting [id=" + id + ", levelName=" + levelName + ", requiredNumberResponses=" + requiredNumberResponses
				+ ", reward=" + reward + "]";
	}

}
