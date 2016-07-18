package Client.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Vector;

import dao.CheckNumberString;
import dao.DishDAO;
import dao.InformationDAO;
import dao.ServiceDAO;
import model.Dish;
import model.Information;
import model.Service;

/**
 * Servlet implementation class HomeController
 */
@WebServlet({"","/recruitment","/introduce","/connected","/map","/service","/detail-service"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String pathinfo = request.getPathInfo();
		String serverPath= request.getServletPath();		
		if(serverPath.equals("")){
			index(request, response);
		}
		else if(serverPath.equals("/introduce")){
			introduce(request, response);
		}
		else if(serverPath.equals("/recruitment")){
			recruitment(request, response);
		}
		else if(serverPath.equals("/connected")){
			connected(request, response);
		}
		else if(serverPath.equals("/map")){
			map(request, response);
		}
		else if(serverPath.equals("/service")){
			service_store(request, response);
		}
		else if(serverPath.equals("/detail-service")){
			if(request.getQueryString()!=null && CheckNumberString.CheckNumber(request.getParameter("sv"))){
				int id=Integer.parseInt(request.getParameter("sv"));
				service_detail(request, response,id);
			}
			else{
				error_page(request, response);
			}
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
		doGet(request, response);
	}
	
	/* functions prosessin follow pathinfo */
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		DishDAO dishDAO=new DishDAO();
	//	Vector<Dish> vtdish=dishDAO.All_Dish_Follow_Image();
		int number_records=dishDAO.Number_Records();
		int totalPage=number_records/DishDAO.limit;
		if(number_records%DishDAO.limit>0) totalPage++;
		request.setAttribute("totalPage", totalPage);
		int pageno = 0;
		try {
			pageno = Integer.parseInt(request.getParameter("page"));
			if(pageno<1) pageno=1;
			if(pageno>totalPage) pageno=totalPage;
		} catch (Exception ex) {
			
		}
		request.setAttribute("pageno", pageno);
		Vector<Dish> vtdishhome=dishDAO.Dish_Follow_Limit(pageno);		
		request.setAttribute("dishhome",vtdishhome);
		request.setAttribute("layout","home");	
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void introduce(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		InformationDAO infoDAO=new InformationDAO();
		Information info=infoDAO.AllInformation();
		request.setAttribute("conten_introduce",info.getGioithieu());
		request.setAttribute("layout","introduce");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void connected(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		InformationDAO infoDAO=new InformationDAO();
		Information info=infoDAO.AllInformation();
		request.setAttribute("conten_connected",info.getLienhe());
		request.setAttribute("layout","connected");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void recruitment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		InformationDAO infoDAO=new InformationDAO();
		Information info=infoDAO.AllInformation();
		request.setAttribute("conten_recruitment",info.getTuyendung());
		request.setAttribute("layout","recruitment");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void service_store(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		ServiceDAO serviceDAO=new ServiceDAO();
		Vector<Service> vtservice=serviceDAO.All_Service();
		request.setAttribute("allservice", vtservice);
		request.setAttribute("layout","service");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void service_detail(HttpServletRequest request, HttpServletResponse response,int id)throws ServletException, IOException {	
		ServiceDAO serviceDAO=new ServiceDAO();
		if(!serviceDAO.CheckExist_Service(id)){			
			request.setAttribute("error","Dịch vụ không tồn tại,bạn đã có sự tác động vào website.!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
			re.forward(request, response);	
			return;
		}
		Service service=serviceDAO.Get_Service_ID(id);
		request.setAttribute("content_service", service);
		request.setAttribute("layout","service_detail");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void map(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
		request.setAttribute("layout","map");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
	}
	protected void error_page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.sendRedirect(request.getContextPath()+"/error-page");			
	}

}
