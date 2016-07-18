package Client.Controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CheckNumberString;
import dao.NewsDAO;
import model.News;

/**
 * Servlet implementation class NewsController
 */
@WebServlet("/news")
public class NewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathinfo = request.getPathInfo();
		if (pathinfo == null || pathinfo.equals("/")) {
			int id=0;
			if(request.getParameter("ns")!=null && CheckNumberString.CheckNumber(request.getParameter("ns"))){
				id=Integer.parseInt(request.getParameter("ns"));
			}			
			index(request, response,id);
		}
		else{
			error_page(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		error_page(request, response);
	}

	protected void index(HttpServletRequest request, HttpServletResponse response,int id)
			throws ServletException, IOException {
		NewsDAO newsDAO =new NewsDAO();
		News news=new News();
		if(id!=0 && !newsDAO.CheckExist_News(id)){			
			response.sendRedirect(request.getContextPath()+"/news");
			return;
		}
		if(id==0){
			news=newsDAO.NewsLimit1();
		}
		else{
			news=newsDAO.News_Follow_ID(id);
		}
		Vector<News> vtnews=newsDAO.AllNews();
		request.setAttribute("allnews",vtnews);
		request.setAttribute("content_news",news);
		request.setAttribute("layout","news");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request, response);	
		return;
	}
	
	protected void error_page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.sendRedirect(request.getContextPath()+"/error-page");			
	}
}
