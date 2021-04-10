package ru.otus.spring.domain;

public class Answer {

	private Question question;
	private String answerText;

	public Answer() {
	}

	public Answer(Question question, String answerText) {
		this.question = question;
		this.answerText = answerText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
