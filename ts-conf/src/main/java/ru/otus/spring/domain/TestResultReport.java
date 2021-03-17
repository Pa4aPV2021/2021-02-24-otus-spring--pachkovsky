package ru.otus.spring.domain;

public class TestResultReport {
	private int numberRequiredAnswers;
	private int number—orrectAnswers;
	private boolean isTestPassed;

	public int getNumberRequiredAnswers() {
		return numberRequiredAnswers;
	}

	public void setNumberRequiredAnswers(int numberRequiredAnswers) {
		this.numberRequiredAnswers = numberRequiredAnswers;
	}

	public int getNumber—orrectAnswers() {
		return number—orrectAnswers;
	}

	public void setNumber—orrectAnswers(int number—orrectAnswers) {
		this.number—orrectAnswers = number—orrectAnswers;
	}

	public boolean isTestPassed() {
		return isTestPassed;
	}

	public void setTestPassed(boolean isTestPassed) {
		this.isTestPassed = isTestPassed;
	}

}
