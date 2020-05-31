import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Scanner;


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
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;

import java.nio.file.Path;
import java.nio.file.Paths;

//import org.apache.lucene.queryparser.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;



public class IndexData {
    public static void main(String[] args) {
        // Gather data from text files and pass it to writer class, to index the data in the 
        //form of a "document"
       // String file = "/Users/rheaprashanth/Documents/VScode/TwitterIndexing/test.txt";
       int filenum = 0;
      
       BufferedReader buffread = null;
        try {
            File file = new File("/Users/rheaprashanth/Documents/VScode/PythonRandomShit/" + "tweet" + filenum +".txt");
            buffread = new BufferedReader(new FileReader(file));
            while(filenum < 2){
                file = new File("/Users/rheaprashanth/Documents/VScode/PythonRandomShit/" + "tweet" + filenum +".txt");
                buffread = new BufferedReader(new FileReader(file));
                String l;
                while ((l = buffread.readLine()) != null) {
                   JSONObject obj = new JSONObject(l);  // JSON object class 
                   // lets you transform a JSON string (as we have in our text files), into
                   //an object in JSONObject class. This class has method(s) "get String" to let you 
                   // get specific values for keys like "Title" or "Text"
                
                   String title =obj.getString("Title");
                   String text =obj.getString("text");  //good
                   String screen_name =obj.getJSONObject("user").getString("screen_name"); //good
            //   String location =obj.getJSONObject("user").getString("location");
                   String url =obj.getJSONObject("place").getString("url");
                   String date =obj.getString("created_at"); //good

               /*    System.out.println(title); // make sure this is getting printed for each doc
                   System.out.println("^TITLE");
                   System.out.println(screen_name);
                   System.out.println("^SCREEN_NAME");
                   System.out.println(text);
                   System.out.println("^LOCATION");
                   System.out.println(location);
                   System.out.println("NEXT TWEEEEET");
                   System.out.println(url);
                   //System.out.println("URL^");
                   System.out.println(filenum);
                   */
                   LuceneIndexing(title, text, screen_name, date, url); //send each tweet one at a time to LuceneIndexing to
                   // this function, which will create a document that will be passed to indexWriter
                   // to be indexed.
                }
                buffread.close();
                filenum = filenum + 1;
               // System.out.println(filenum);
                
               
            }     

        }
        catch(Exception e) {
            System.out.println("EXCEPTION");
            e.printStackTrace();
            
        }
        finally {
            try {
                buffread.close();
                    
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
    
    } 
}
        

    


    public static void LuceneIndexing(String title, String text, String screen_name, String date, String url ) {
      try{

        Analyzer analyzer = new StandardAnalyzer();
       
       IndexWriterConfig indexConfig = new IndexWriterConfig(analyzer);
      Path p = Paths.get("/Users/rheaprashanth/Documents/VScode/PythonRandomShit/index");
      //System.out.println("before exception");
      IndexWriter writer = new IndexWriter(FSDirectory.open(p), indexConfig); 
        Document d = new Document();
      

    d.add(new TextField("text", text, Field.Store.YES));
    d.add(new TextField("title", title, Field.Store.YES));
    d.add(new TextField("screenName", screen_name, Field.Store.YES));
    d.add(new TextField("location", date, Field.Store.YES));
    d.add(new TextField("url", url, Field.Store.YES));
    String q = " probably sensible, just in case";
    int n = 10;
  

//	luceneDoc.setBoost((float)2.0);
     writer.addDocument(d);
    System.out.println("Indexing");
    searchTheIndex(q, n);


      // writer.commit(); // what does this do...
       writer.close();
     

      
      }
      catch (Exception ex) {
        ex.printStackTrace();
    }
   
    



        
        
    }


    public final static String[] searchTheIndex(String queryResult, int numRes)  throws Exception, IOException{
        Analyzer a = new StandardAnalyzer();
        Path pg = Paths.get("/Users/rheaprashanth/Documents/VScode/PythonRandomShit/index");
        DirectoryReader ireader = DirectoryReader.open(FSDirectory.open(pg));
        IndexSearcher searcher = new IndexSearcher(ireader);
   
        try{ 
    

        QueryParser qp = new QueryParser("title",a);
        Query q = qp.parse(queryResult);
        TopDocs docs = searcher.search(q, numRes);
        ScoreDoc[] results = docs.scoreDocs;
        String[] Tweets = new String[results.length];
        System.out.println(results.length);
        for(int i =0; i < results.length; ++i) {
           // System.out.println("in for loop");
            Document hitDoc = searcher.doc(results[i].doc);
            String retString = "Tweet by @:" + hitDoc.get("screenName") + "" + "tweet: " + hitDoc.get("text");
            Tweets[i] = retString;
            System.out.println(Tweets[i]);
            
           
          }

          return Tweets;
        }
        catch (Exception e) {
			e.printStackTrace();
        }   
        finally {
		ireader.close();
		}
		return null;
    }

}


