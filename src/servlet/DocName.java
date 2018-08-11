package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocName {
	
	public static final String baseLocalVectorsFile = "/Users/niujin/Desktop/CS6200/HW/Project/vectorsAll/vectorsAll.txt";
	public static final String baseLocalNameLst = "/Users/niujin/Desktop/CS6200/HW/Project/vectorsAll/nameList.txt";

	public List<String> getDocNames(File file) throws IOException, ClassNotFoundException {
		
		InputStream iMap = new FileInputStream(file);
		ObjectInputStream objInputMap = new ObjectInputStream(iMap);
		Map<String, HashMap<Integer, Double>> allVectors = (Map<String, HashMap<Integer, Double>>) objInputMap.readObject();
		objInputMap.close();
		
		List<String> docNames = new ArrayList<>(allVectors.keySet());
		return docNames;
	}
	
public void saveDocNames() throws IOException, ClassNotFoundException {
		File file = new File(baseLocalVectorsFile);
		InputStream iMap = new FileInputStream(file);
		ObjectInputStream objInputMap = new ObjectInputStream(iMap);
		Map<String, HashMap<Integer, Double>> allVectors = (Map<String, HashMap<Integer, Double>>) objInputMap.readObject();
		objInputMap.close();
		
		List<String> docNames = new ArrayList<>(allVectors.keySet());
		
		File fileOut = new File(baseLocalNameLst);
		FileOutputStream fst = new FileOutputStream(fileOut);
		ObjectOutputStream ost= new ObjectOutputStream(fst);
		ost.writeObject(docNames);	
		ost.flush();
		ost.close();
		fst.close();
		System.out.println(docNames.size());
	}

	public Map<String, List<String[]>> getDocName(String query) { // 50
		  Map<String, List<String[]>> map = new HashMap<>();
		  String[] str = new String[3];
		  String[] str3 = new String[3];
		  List<String[]> lst = new ArrayList<>();
		  str[0] = "hello";
		  str[1] = "https://stackoverflow.com/questions/13395114/how-to-initialize-liststring-object-in-java";
		  str[2] = "title";
		  str3[0] = "hello3";
		  str3[1] = "www.google.com";
		  str3[2] = "title3";
		  lst.add(str);
		  lst.add(str3);
		  map.put("01", lst);
		  return map; //key:01, val: list(doc1:url...,doc2:url...)
	}
	
	

public static void main(String[] args) throws ClassNotFoundException, IOException {
//	DocName doc = new DocName();
//	doc.saveDocNames();
}
	
}
