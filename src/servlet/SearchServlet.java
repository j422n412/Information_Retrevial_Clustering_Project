package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.ParseException;
import org.json.JSONException;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//        
       String query = request.getParameter("query");
       
////		
//		if (query == null || query.trim().isEmpty()) {
//            System.out.println("?");
//        } 
//		System.out.println(query);
		//else {
//        	// Retrieve Users, and store as a message.
//        		searcher.queryExecute(query, client);
//        		tweets = searcher.getTweetResult();
//        		users = searcher.getTopFollowerUsers();
//        		messages.put("success", "Displaying results for " + query);
//        		// Save the previous search term, so it can be used as the default
//        		// in the input box when rendering FindUsers.jsp.
//        		messages.put("previousUserName", query);
//        }
//        req.setAttribute("tweets", tweets);
//        req.setAttribute("users", users);

//		  DocName doc = new DocName();
//		  Map<String, List<String[]>> map = new HashMap<>();
//		  
//		  map = doc.getDocName(query);	 
//		  HttpSession session = request.getSession();	  
//		  session.setAttribute("cluster", map);
//		  System.out.println(map.size());
		  
//		    
		GetResult res = new GetResult();
		try {
			
			Map<String, List<Document>> getClusterNews = new HashMap<>();
			getClusterNews = res.getNews(query);
			HttpSession session = request.getSession();
			session.setAttribute("cluster", getClusterNews);
			request.getRequestDispatcher("/frontpage.jsp").forward(request, response);
			System.out.println(getClusterNews.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
	


