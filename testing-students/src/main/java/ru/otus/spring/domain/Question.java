package ru.otus.spring.domain;

public class Question {
	private Integer id;
	private String question_text;
	private String true_answer;

	public Question() {
	}

	public Question(Integer id, String question_text, String true_answer) {
		super();
		this.id = id;
		this.question_text = question_text;
		this.true_answer = true_answer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public String getTrue_answer() {
		return true_answer;
	}

	public void setTrue_answer(String true_answer) {
		this.true_answer = true_answer;
	}

	@Override
	public String toString() {
		return "QuestionsWithoutOptions [id=" + id + ", question_text=" + question_text + ", true_answer=" + true_answer
				+ "]";
	}

}
