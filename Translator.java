import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

public class Translator
{

    public Translator() 
    {
		super();
		stock();
	}

	static TreeMap<String, ArrayList<String>> database = new TreeMap<>();    //byLanguage
    public TreeMap<String,ArrayList<String>> getDatabase()
    {
        return database;
    }
    public String[] getLanguages()
    {
    	int len = database.size();
    	String[] ret = new String[len];
    	
    	Iterator<String> i = database.keySet().iterator();
    	int index = 0;
    	while(i.hasNext())
    	{
    		ret[index] = i.next();
    		index++;
    	}
    	return ret;
    }

    public void stock()
    {
        Scanner scan = new Scanner(this.getClass().getResourceAsStream("BabelFish.txt"));
        String[] languages = scan.nextLine().split("\t");
        for (int i = 0; i < languages.length; i++)
        {
            if (!database.containsKey(languages[i]))
            {
                database.put(languages[i], new ArrayList<>());
            }
        }
        while(scan.hasNextLine())
        {
            String[] oneLine = scan.nextLine().split("\t");
            for(int i=0;i<languages.length;i++)
            {
                database.get(languages[i]).add(oneLine[i]);
            }
        }    

    }
    
    public static String doTranslation(String s, String f, String t)
    {
        String ret = "";
        ArrayList<String> from = database.get(f);
        ArrayList<String> to = database.get(t);
        String[] arraySentence = s.split("\t");
        for(int i=0;i<arraySentence.length;i++)
        {
            try
            {
                int index = from.indexOf(s);
                ret = to.get(index) + " ";
            }
            catch(IndexOutOfBoundsException e)
            {
                ret += arraySentence[i];
            }
        }
        return ret;
    }
}

