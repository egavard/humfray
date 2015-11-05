package dto;

import java.util.Map;
/**
 * @version 0.1
 * @author boubou
 */
public class QuestionAnswer {

	private String questionValue;
	private Integer id;
	private Map<String,String> answerList;
	
	public QuestionAnswer(String questionValue,Integer id,Map<String,String> answerList)
	{
		this.questionValue = questionValue;
		this.id =id;
		this.answerList = answerList;
	}

	public String getQuestionValue() {
		return questionValue;
	}

	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(Map<String, String> answerList) {
		this.answerList = answerList;
	}

}
