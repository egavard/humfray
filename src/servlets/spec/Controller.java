package servlets.spec;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cette interface spécifie les méthodes obligatoire dans un Controller
 * @author egavard
 */
public interface Controller {
	/**
	 * Cette méthode permet l'interprétation des requête HTTP de type POST et de type GET
	 * @param req un HTTPServletRequest représentant la requête de l'utlisateur
	 * @param res un HTTPServletResponse représentant la reponse que l'on va renvoyer à l'utilisateur
	 */
	public void service(HttpServletRequest req,HttpServletResponse res);
}
