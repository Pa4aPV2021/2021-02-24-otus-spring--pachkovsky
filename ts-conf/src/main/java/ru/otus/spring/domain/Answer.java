package ru.otus.spring.domain;

public class Answer {

	private int idQuestion;
	private String answerText;

	public Answer() {
	}

	public Answer(int idQuestion, String answerText) {
		super();
		this.idQuestion = idQuestion;
		this.answerText = answerText;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

}
