package ru.otus.spring.shell.domain;

public class TestResultReport {
	private int numberRequiredAnswers;
	private int numberСorrectAnswers;
	private boolean isTestPassed;

	public int getNumberRequiredAnswers() {
		return numberRequiredAnswers;
	}

	public void setNumberRequiredAnswers(int numberRequiredAnswers) {
		this.numberRequiredAnswers = numberRequiredAnswers;
	}

	public int getNumberСorrectAnswers() {
		return numberСorrectAnswers;
	}

	public void setNumberСorrectAnswers(int numberСorrectAnswers) {
		this.numberСorrectAnswers = numberСorrectAnswers;
	}

	public boolean isTestPassed() {
		return isTestPassed;
	}

	public void setTestPassed(boolean isTestPassed) {
		this.isTestPassed = isTestPassed;
	}

}
