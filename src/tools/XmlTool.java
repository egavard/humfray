package tools;

import java.io.IOException;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author tbouyenval
 *
 */
public class XmlTool {
	
	/**
	 * enumere les formats d'affichage
	 */
	public static final Format COMPACTFORMAT = Format.getCompactFormat();
	public static final Format PRETTYFORMAT = Format.getPrettyFormat();
	public static final Format RAWFORMAT = Format.getRawFormat();
	
	private org.jdom2.Document file;
	private SAXBuilder sxb;
	private org.jdom2.Element root;
	private static final Logger LOG = LoggerFactory.getLogger(XmlTool.class);
	
	public XmlTool()
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
		    this.file = sxb.build(XmlTool.class.getClassLoader().getResourceAsStream(link));
			this.root = this.file.getRootElement();
		} catch (Exception e) {
			LOG.error("fail to initialise the xml file with the given path",e);
		}
	}
	
	public org.jdom2.Element getRoot() {
		return root;
	}
	public void setRoot(org.jdom2.Element root) {
		this.root = root;
	}
	/**
	 * retourne les premiers fils
	 * @return List<Element>
	 */
	public Element getChildren(String cname)
	{
		if(cname == null)
			return null;
		else
			return root.getChild(cname);
			
	}
	
	public List<Element> getListChildren(Element e,Element actuel)
	{
		Element fin = actuel;
		while(!fin.equals(e))
		{
			for(Element t : fin.getChildren())
			{
				if(t.equals(e))
				{
					return t.getChildren();
				}
				else
				{
					this.getListChildren(e,t);
				}
			}
		}
		return null;
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
			LOG.debug("root is not instantiate",e);
		}
		return value;
	}
	
	
	
	/**
	 * donne une representation du xml
	 */
	public void affiche(Format f)
	{
		try
		   {
		      //On utilise ici un affichage classique avec getPrettyFormat()
		      XMLOutputter sortie = new XMLOutputter(f);
		      sortie.output(this.file, System.out);
		   }
		   catch (IOException e)
		   {
			   LOG.debug("fail to read the file",e);
		   }
	}
	
	/**
	 * 
	 * @param e l'element de depart, root si null
	 * @return list contenant les fils de e
	 */
	public List<Element> getNextListOfChlid(Element e)
	{
		List<Element> retour = null;		
		try
		{
			if(e == null)
				retour = root.getChildren();
			else
				retour = e.getChildren();
		}
		catch(Exception exc)
		{
			LOG.error("probleme avec l element de getnextlistofChlid ",exc);
		}
		return  retour;
	}

}
