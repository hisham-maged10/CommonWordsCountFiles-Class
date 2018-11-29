/*
...@Author Hisham Maged
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
public class TestWordCount
{
	public static void main(String[] args)
	{
		testing();
	}
	public static void testing()
	{
		WordCount wc=new WordCount();
		//will print common words between files and their count and where they appeared
		//wc.printAll();
		System.out.println("Total unique words that occured in selected files are: "+wc.getTotalCommonWords());
		System.out.println("Total common unique words that occured in each of the 5 files are: "+wc.getNumberWordsCertainOccur(5));
		System.out.println("Total common unique Words between 3 files are: "+wc.getNumberWordsCertainOccur(3));
		//print the appearance and occurence files of a certain word
		wc.printCertainWord("tree");
		//print the appearance and occurence files of a certain word
		wc.printCertainWord("red");
		//will print max appearance and occurence of words in selected file(s)
		/*HashMap<String,HashSet<String>> maxWords=wc.getMaxWords();
		Iterator<String> i;
		for(String e: maxWords.keySet())
			{
				System.out.print(e+ " Appeared "+maxWords.get(e).size()+" times in files: ");
				i=maxWords.get(e).iterator();
				while(i.hasNext())
					System.out.print(i.next()+(i.hasNext()?", ":""));
				System.out.println();
			}
		*/
		
		
	}

}