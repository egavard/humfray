package tools;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author tbouyenval
 *
 */
public class xmlTool {
	
	/**
	 * enumére les formats d'affichage
	 */
	public static final Format CompactFormat = Format.getCompactFormat();
	public static final Format PrettyFormat = Format.getPrettyFormat();
	public static final Format RawFormat = Format.getRawFormat();
	
	private org.jdom2.Document File;
	private SAXBuilder sxb;
	private org.jdom2.Element root;
	private static final Logger LOG = LoggerFactory.getLogger(xmlTool.class);
	
	public xmlTool()
	{
		sxb = new SAXBuilder();
	}
	/**
	 * recupere le path du fichier xml et l'initialise ainsi que la racine
	 * @param link
	 */
	public void initFile(String link)
	{
		try {
			File = sxb.build(link);
			root = File.getRootElement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			LOG.debug("fail to initialise the xml file with the given path");
		}
	}
	
	/**
	 * @return valeur de la racine
	 */
	public String getRootName()
	{
		String value = "";
		try
		{
		 value = root.getName();
		}
		catch(Exception e)
		{
			LOG.debug("root is not instantiate");
		}
		return value;
	}
	
	
	
	/**
	 * donne une représentation du xml
	 */
	public void affiche(Format f)
	{
		try
		   {
		      //On utilise ici un affichage classique avec getPrettyFormat()
		      XMLOutputter sortie = new XMLOutputter(f);
		      sortie.output(File, System.out);
		   }
		   catch (java.io.IOException e)
		   {
			   LOG.debug("fail to read the file");
		   }
	}
	
	
	public static void main(String[] args) {
		xmlTool t = new xmlTool();
		t.initFile("./ressources/test.xml");
		t.affiche(xmlTool.RawFormat);
		System.out.println(t.getRootName());
	}

}
