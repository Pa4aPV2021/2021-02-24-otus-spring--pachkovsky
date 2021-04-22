package ru.otus.spring.shell.domain;

public class Question {
	private int id;
	private String questionText;
	private String trueAnswer;

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((questionText == null) ? 0 : questionText.hashCode());
		result = prime * result + ((trueAnswer == null) ? 0 : trueAnswer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		if (questionText == null) {
			if (other.questionText != null)
				return false;
		} else if (!questionText.equals(other.questionText))
			return false;
		if (trueAnswer == null) {
			if (other.trueAnswer != null)
				return false;
		} else if (!trueAnswer.equals(other.trueAnswer))
			return false;
		return true;
	}

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

}
