package servlet;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
	private String cluster;
	private List<Document> lstDoc;
	
	public Cluster(String cluster, List<Document> lstDoc) {
		this.cluster = cluster;
		this.lstDoc = lstDoc;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public List<Document> getLstDoc() {
		return lstDoc;
	}

	public void setLstDoc(List<Document> lstDoc) {
		this.lstDoc = lstDoc;
	}
}

