package service.interf;

import dto.QuestionReponse;
import dto.FinalListeDto;

/**
 * @version 0.1
 * @author boubou
 *
 */

public interface IListFinderService{

	/**
	 * retourne la premiere question 
	 * @return QuestionReponse
	 */
	public QuestionReponse getFirstQuestion();
	
	/**
	 * retourne la question suivante avec les deux parametres donnés, null sinon
	 * @param idQuestion
	 * @param idReponse
	 * @return QuestionReponse suivante ou null
	 */
	public QuestionReponse getNextQuestion(Integer idQuestion,Integer idReponse);
	
	/**
	 * retourne la liste finale avec les parametres donnés
	 * @param idQuestion
	 * @param idReponse
	 * @return finalListeDto
	 */
	public FinalListeDto getFinalListe(Integer idQuestion,Integer idReponse);
}
