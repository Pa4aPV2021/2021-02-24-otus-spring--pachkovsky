package ru.otus.spring.shell.domain;

public class Question {
	private int id;
	private String questionText;
	private String trueAnswer;

	public Question() {
	}

	public Question(int id, String questionText, String trueAnswer) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.trueAnswer = trueAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getTrueAnswer() {
		return trueAnswer;
	}

	public void setTrueAnswer(String trueAnswer) {
		this.trueAnswer = trueAnswer;
	}

	@Override
	public String toString() {
		return "QuestionsWithoutOptions [id=" + id + ", questionText=" + questionText + ", trueAnswer=" + trueAnswer
				+ "]";
	}

}
