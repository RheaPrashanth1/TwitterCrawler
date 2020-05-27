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
//import org.apache.lucene.queryparser.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;


public class IndexData {
    public static void main(String[] args) {
        // Gather data from text files and pass it to writer class, to index the data in the 
        //form of a "document"
        String file = "/Users/rheaprashanth/Documents/VScode/TwitterIndexing/test.txt";
        try (BufferedReader buffread = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = buffread.readLine()) != null) {
              // process the line, then repeat while loop which gets new line in file
              //until file is done. Open next file and repeat process until we reach
              //file number 200
              System.out.println(line);

            }       
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }
}
