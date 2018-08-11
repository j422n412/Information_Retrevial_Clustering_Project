package servlet;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map.Entry;

public class Query {

		public static Client client;

//		/* read query Line and store them in a list*/
//		 public static List<String> queryLine(File file) throws IOException  {
//			 List<String> queryLine = new ArrayList<>();
//			 BufferedReader br = null;
//			 try {
//					String line = "";
//					br = new BufferedReader(new FileReader(file));
//					line = br.readLine();
//					while(line != null) {
//						queryLine.add(line);
//						line = br.readLine();
//					}
//			 } catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			 br.close();
//			 return queryLine;
//		 }	 
	
	//query execution for one query, use map to store the result, docNo as the key, scores as the value
	public static List<Integer> queryExecute(String queryLine) throws JSONException, ParseException, IOException {
		
		RestClient restClient = null;
		restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();
    	
		QueryBuilder query= QueryBuilders.queryStringQuery(queryLine); //query String query

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(query);
		sourceBuilder.from(0); 
//		sourceBuilder.size(50); // the number of search hits to return
		
		Response indexResponse = null;
        
        try {
        	//full text query: json version 
        String json = "{\n" +
                "  \"query\" : {\n" + 
                "  \"query_string\" : {\n" +
                "  \"default_field\" :" + "\"text\"" +", \n" + 
                "  \"query\" :" + "\"" + queryLine +"\"" + "\n" + 
                "} \n" + 
                "} \n" + 
                "}";

        HttpEntity entity = new NStringEntity(sourceBuilder.toString(), ContentType.APPLICATION_JSON);
        Map<String, String> params = Collections.emptyMap();
        indexResponse = restClient.performRequest("GET", "/ap_dataset/ap/_search", params, entity);
//        System.out.println("here: " + EntityUtils.toString(indexResponse.getEntity()));
        } catch (Exception e) {
            System.out.println("Error DocStats...");
        } 
        
        JSONObject source = null;
        int clusterId;
        double score;
        
        List<Integer> hitsLst = new ArrayList<>();
        String responseLine = EntityUtils.toString(indexResponse.getEntity());
//         parse JSON response 	
        JSONObject json = new JSONObject(responseLine);
        JSONObject hits = new JSONObject(json.getJSONObject("hits").toString());
        JSONArray hitsArray = hits.getJSONArray("hits");
     		for (int i=0; i<hitsArray.length(); i++) {
	       		JSONObject h = hitsArray.getJSONObject(i);
	       		source = h.getJSONObject("_source");
	       		clusterId = (Integer)source.get("docNo"); //docNo
	       		score = h.getDouble("_score"); //score
	       		System.out.println(i+1 +". " + "docNo: " + clusterId + "       " + "score: " + score);
	       		hitsLst.add(clusterId);
     		}
     	return hitsLst;
	}
	
    public static void main(String[] args) throws IOException, ParseException, JSONException {
    	String query = "google";
    	queryExecute(query);
//    	
//		File[] files = folder.listFiles();
//		List<String> read = readQueryLine(file2);
    	
//		List<String> query = finalQuery(file2, file1); // input stoplist file and query file 
//    	
//    	List<String> originalQuery = queryLine(file2);
    	
//    	for(int i = 0; i <query.size(); i++) {
//    		System.out.println("Results from the query: \n" + originalQuery.get(i));
//    		queryExecute(query.get(i));
//    	}
//    	PrintStream out = new PrintStream(new FileOutputStream("HW1 output.txt"));
//    	System.setOut(out);
    	
    	// write the results from console to an output.txt file.
//		PrintStream console = System.out;
//
//		File file = new File("outputHW1.txt");
//		FileOutputStream fos = new FileOutputStream(file);
//		PrintStream ps = new PrintStream(fos);
//		System.setOut(ps);
//		
//		for(int i = 0; i <query.size(); i++) {
//    		System.out.println("Results from the query: \n" + originalQuery.get(i));
//    		queryExecute(query.get(i));
//    	}
//
//		System.setOut(console);
//		System.out.println("This also goes to the console");    	
    	
    }	
}
