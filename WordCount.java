/*
...@Author Hisham Maged
*/
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
public class WordCount
{
	private HashMap<String,HashSet<String>> wordsMap;
	private File[] files;
	public WordCount()
	{
		this(null);
	}
	public WordCount(File[] files)
	{
		this.files=(files==null)?getFiles():files;
		buildMap();
	}
	private File[] getFiles()
	{
		JFileChooser chooser=new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files","txt","TXT");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(true);
		chooser.setCurrentDirectory(new File("."));
		try{
		do
		{
			System.out.println("Please select one or more files!");
			chooser.showOpenDialog(null);
		}while(chooser.getSelectedFiles()==null);
		}catch(NullPointerException ex){System.out.println("Incorrect Response!"); return getFiles();}
		
		return chooser.getSelectedFiles();
	}
	private void buildMap()
	{
		wordsMap=new HashMap<String,HashSet<String>>();
		for(File e:this.files)
			getContent(e);
	}
	private void getContent(File file)
	{
		String line;
		String[] tokens;
		try(BufferedReader input=new BufferedReader(new FileReader(file)))
		{
			while((line=input.readLine())!=null)
			{
				fillMap((tokens=modifyLine(line.trim().split(" "))),file.getName());
			}
		}catch(IOException ex){ex.printStackTrace();}
	}
	private void fillMap(String[] tokens,String filename)
	{
		for(String e : tokens)
		{
			if(!wordsMap.containsKey(e))
			{
				HashSet<String> wordSet=new HashSet<String>(Arrays.asList(filename));
				wordsMap.put(e,wordSet);
			}
			else
			{
				wordsMap.get(e).add(filename);
			}
					
		}
	}
	private String[] modifyLine(String[] tokens)
	{
		return removeWhiteSpaces(tokens);
	}
	private String[] removeWhiteSpaces(String[] tokens)
	{
		ArrayList<String> arr=new ArrayList<String>();
		for(String e:tokens)
			if(e.equals("") || e.equals(" ") || e.equals(System.getProperty("line.separator")) || !containLetters(e)) continue;
			else arr.add(e);
		return arr.toArray(new String[arr.size()]);
	}
	private boolean containLetters(String e)
	{
		char[] chArr=e.toCharArray();
		for(char c:chArr)
		{
			if(Character.isLetter(c))
				return true;
		}
		return false;	
	}
	private String[] removeNonLetters(String[] str)
	{
		int j=0;
		for(String e:str)
			str[j++]=removePunctuation(e.trim());
		return str;
	}
	private String removePunctuation(String str)
	{
		StringBuilder sb=new StringBuilder();
		for(int i=0,n=str.length();i<n;i++)
			if(Character.isLetter(str.charAt(i)))
				sb.append(str.charAt(i));
			else 
				continue;
		return sb.toString();
	}
	private int getMaxSizeHashSet()
	{
		int maxSize=0;
		for(HashSet<String> e: wordsMap.values())
			if(e.size()>maxSize)
				maxSize=e.size();
		return maxSize;	
	}
	public void printAll()
	{
		Iterator<String> i;
		for(String e:wordsMap.keySet())
		{
			System.out.print(e+ " Appeared "+wordsMap.get(e).size()+" times in files: ");
				i=wordsMap.get(e).iterator();
				while(i.hasNext())
					System.out.print(i.next()+(i.hasNext()?", ":""));
				System.out.println();
			
		}
	}
	public int getTotalCommonWords()
	{
		return wordsMap.size();
	}
	public void printCertainWord(String str)
	{
		Iterator<String> i;
		for(String e:wordsMap.keySet())
		{
			if(!e.equalsIgnoreCase(str))continue;
			System.out.print(e+ " Appeared "+wordsMap.get(e).size()+" times in files: ");
				i=wordsMap.get(e).iterator();
				while(i.hasNext())
					System.out.print(i.next()+(i.hasNext()?", ":""));
				System.out.println();
			
		}
	}
	public HashMap<String,HashSet<String>> getMaxWords()
	{
		int max=getMaxSizeHashSet();
		HashMap<String,HashSet<String>> maxWords=new HashMap<String,HashSet<String>>();
		for(String e:wordsMap.keySet())
			if(max==wordsMap.get(e).size())
				maxWords.put(e,wordsMap.get(e));
		return maxWords;
	}
	public int getNumberWordsCertainOccur(int num)
	{
		HashMap<String,HashSet<String>> certainWords=new HashMap<String,HashSet<String>>();
		for(String e:wordsMap.keySet())
			if(num==wordsMap.get(e).size())
				certainWords.put(e,wordsMap.get(e));
		
		return certainWords.size();
	}
}