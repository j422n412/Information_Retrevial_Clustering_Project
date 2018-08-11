package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.json.JSONException;

public class GetResult {
	public static final String baseClusterK500 =  "/Users/niujin/Desktop/CS6200/HW/Project/ClusterResult/clusterKey500.text";
	public static final String baseClusterStroted = "/Users/niujin/Desktop/CS6200/HW/Project/ClusterResult/cluster500Sorted.text";
	public static final String baseDocumentInf = "/Users/niujin/Desktop/CS6200/HW/Project/ClusterResult/docId_url_title.txt";
	public static Map<String, List<Document>> getNews(String query) throws IOException, ClassNotFoundException, ParseException, JSONException {
		Query queryInput = new Query();
		File file = new File(baseClusterK500);
		List<String>[] keyList = new List[500];
		InputStream iMap = new FileInputStream(file);
		ObjectInputStream objInputMap = new ObjectInputStream(iMap);
		keyList = (List<String>[]) objInputMap.readObject(); // index represent clusterId
		//now we get list of clusterId, we can then get all the news from each cluster.
		List<Integer> clutserLst= queryInput.queryExecute(query); //clusterId get from queryExecut
		List<Document>[] clusterNews = new List[500];
		clusterNews = getClusterDocument();
		Map<String, List<Document>> clusterOutput = new HashMap<>(); //list of clusters with each have different doc
		for(int i = 0; i < clutserLst.size(); i++) {
			List<String> subList = new ArrayList<>();
			String description = "";
			subList = keyList[clutserLst.get(i)];
			List<Document> lstDoc =clusterNews[clutserLst.get(i)];
			for(int j = 0; j < subList.size(); j++) {
				if(j >= 10) {
					break;
				}
				description += subList.get(j) + " ";
			}
			
			clusterOutput.put(description, lstDoc);//clutserLst.get(i) is the clusterId, while keyList use index to represent id
			System.out.println(description);
			System.out.println(lstDoc.size());
		}
		System.out.println(clusterOutput.size());
		
		return clusterOutput;
	}
	
//	public static List<Cluster> getAll(String query) throws ClassNotFoundException, ParseException, IOException, JSONException {
//		List<Cluster> lstC = new ArrayList<>();
//		Map<String, List<Document>> getN = new HashMap<>();
//		getN = getNews(query);
//		for(Map.Entry<String, List<Document>> entry : getN.entrySet()) {
//			Cluster cst = new Cluster(entry.getKey(), entry.getValue());
//			lstC.add(cst);
//		}
//		return lstC;
//	}
	
	//cluster -> document Document[]
//	each document: <docId, <url, title>> HashMap<String, String[]>
	public static List<Document>[] getClusterDocument() throws IOException, ClassNotFoundException {
		File file = new File(baseClusterStroted);
		List<String>[] keyList = new List[500]; //500个cluster
		InputStream iMap = new FileInputStream(file);
		ObjectInputStream objInputMap = new ObjectInputStream(iMap);
		keyList = (List<String>[]) objInputMap.readObject(); // index represent clusterId
		//now we get list of clusterId, we can then get all the news from each cluster.
		
		File file2 = new File(baseDocumentInf);
		HashMap<String, String[]> documentInf = new HashMap<>();
		InputStream iMap2 = new FileInputStream(file2);
		ObjectInputStream objInputMap2 = new ObjectInputStream(iMap2);
		documentInf = (HashMap<String, String[]>) objInputMap2.readObject(); 
		List<Document>[] clusterNews = new List[500];
		for(int i = 0; i < keyList.length; i++) {
			List<Document> sublst = new ArrayList<>();
			for(int j = 0; j < keyList[i].size(); j++) {
				if(documentInf.containsKey(keyList[i].get(j))) { //有这个new Id
					String[] str = new String[2];
					str = documentInf.get(keyList[i].get(j));
					Document doc = new Document(keyList[i].get(j), str[0], str[1]);
					sublst.add(doc);
				}
			}
			clusterNews[i] = sublst;
		}
		return clusterNews;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, ParseException, IOException, JSONException {
		getNews("education");
	}
}
