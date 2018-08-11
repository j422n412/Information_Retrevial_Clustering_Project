package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.elasticsearch.client.Response;

public class Indexer {
		
		// read one file and return String(json file already) for all the files
		public static String readContent(File file) throws IOException{
			
			Integer clusterId = Integer.parseInt(file.getName());
			
			String line = "";
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder text = new StringBuilder();
			String keyWords = "";
			
			line = br.readLine(); //read second
			while(line != null) {
				text.append(line);
				line = br.readLine();
			}
			keyWords = text.toString();
			XContentBuilder builder = XContentFactory.jsonBuilder() // convert to json
						    .startObject()
					        .field("docNo", clusterId)
					        .field("text", keyWords)
					    .endObject();
			    	String builderStr = builder.string();

			br.close();
			return builderStr;
	}
		
		// get docNo from json file and will be used as ID: 
		public static String getId(String json) throws JSONException {
			JSONObject jsons = new JSONObject(json);
			return jsons.getString("docNo");
		}
		
		// send data to elasticsearch 
		public static Response sendData(String jsonStr) throws JSONException, ParseException, IOException {
			
			// initialization：
			RestClient restClient = RestClient.builder(
			        new HttpHost("localhost", 9200, "http")).build();
			Response response = null;
			
			String _id = getId(jsonStr);
			
			try {
				
				Map<String, String> params = Collections.emptyMap();
		        HttpEntity httpEntity = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
		        
		        response = restClient.performRequest("POST", "/ap_dataset/ap/"+ _id, params, httpEntity); 
			}catch(IOException e) {
				e.printStackTrace();
			}
//	        String responseLine = EntityUtils.toString(response.getEntity());
//	        System.out.println(responseLine);
			restClient.close();
			return response;
		}
		
		public static void main(String[] args) throws IOException, JSONException {
//		// one way to write the path: 
//				String dir = System.getProperty("user.dir");
//				String folderPath = dir + File.separator + "AP_DATA" + File.separator + "ap89_collection";
//				File folder = new File(folderPath);
			
			File folder = new File("/Users/niujin/Desktop/CS6200/HW/Project/ClusterResult/clusterKeyWords/");
			File[] files = folder.listFiles();
			for(int i = 0; i < files.length; i++) {
	            try{
	            		System.out.println("Read： " + files[i].getName());
	                	String read = "";
	                	read = readContent(files[i]);
	                	sendData(read);	
	            } catch (IOException e) {
	            	e.printStackTrace();
	            }
			}
			System.out.println("finished reading! ");
		}
	}
