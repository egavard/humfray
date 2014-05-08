package dto;

import java.util.Map;
/**
 * @version 0.1
 * @author boubou
 *
 */
public class QuestionReponse {

	private String questionValue;
	private Integer id;
	private Map<String,String> listeReponse;
	
	public QuestionReponse(String questionValue,Integer id,Map<String,String> listeReponse)
	{
		this.questionValue = questionValue;
		this.id =id;
		this.listeReponse = listeReponse;
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
	public Map<String, String> getListeReponse() {
		return listeReponse;
	}
	public void setListeReponse(Map<String, String> listeReponse) {
		this.listeReponse = listeReponse;
	}
}
