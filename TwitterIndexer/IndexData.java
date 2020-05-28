import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
//import org.apache.lucene.queryparser.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;



public class IndexData {
    public static void main(String[] args) {
        // Gather data from text files and pass it to writer class, to index the data in the 
        //form of a "document"
       // String file = "/Users/rheaprashanth/Documents/VScode/TwitterIndexing/test.txt";
       int filenum = 0;
     
        try {
            File file = new File("/Users/rheaprashanth/Documents/VScode/TwitterIndexing/" + "tweet" + filenum +".txt");
            BufferedReader buffread = new BufferedReader(new FileReader(file));
            while(file.exists()){
                file = new File("/Users/rheaprashanth/Documents/VScode/TwitterIndexing/" + "tweet" + filenum +".txt");
                buffread = new BufferedReader(new FileReader(file));
                String l;
                while ((l = buffread.readLine()) != null) {
                   JSONObject obj = new JSONObject(l);  // JSON object class 
                   // lets you transform a JSON string (as we have in our text files), into
                   //an object in JSONObject class. This class has method(s) "get String" to let you 
                   // get specific values for keys like "Title" or "Text"
                
                   String title =obj.getString("Title");
                   String text =obj.getString("text");
                 //  String screen_name =obj.getString("screen_name");
                  //  String location =obj.getString("location");
                //    String url =obj.getString("url");
             //   String coordinates =obj.getString("coordinates");

                   System.out.println(title); // make sure this is getting printed for each doc
                   System.out.println("^TITLE");
                   System.out.println(text);
                   System.out.println("NEXT TWEEEEET");
                   System.out.println(filenum);
                   LuceneIndexing(title, text); //send each tweet one at a time to LuceneIndexing to
                   // this function, which will create a document that will be passed to indexWriter
                   // to be indexed.
                }
                buffread.close();
                filenum = filenum + 1;
                System.out.println(filenum);
                
               
            }     

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        

    }

    public static void LuceneIndexing(String title, String text) {
        System.out.println("Hey!");
    }

}
