package Client.Controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoryDAO;
import dao.CheckNumberString;
import dao.DishDAO;
import model.Category;
import model.Dish;

/**
 * Servlet implementation class DishController
 */
@WebServlet("/menu/*")
public class DishController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DishController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathinfo = request.getPathInfo();
		if (pathinfo == null || pathinfo.equals("/")) {

		} else if (pathinfo.endsWith("/dish-type")) {
			if (request.getParameter("category") != null
				&& CheckNumberString.CheckNumber(request.getParameter("category"))) {
				store_menu_dish(request, response, Integer.parseInt(request.getParameter("category")));
			}
			else{
				request.setAttribute("error","Đường dẫn không chính xác,bạn đã có sự tác động vào website.!");
				request.setAttribute("layout", "error");
				RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
				re.forward(request, response);	
				return;
			}
		}
		else if (pathinfo.equals("/detail")) {
			if (request.getParameter("dish") != null
				&& CheckNumberString.CheckNumber(request.getParameter("dish"))) {
				detail_dish(request, response, Integer.parseInt(request.getParameter("dish")));
			}
			else{
				request.setAttribute("error","Đường dẫn không chính xác,bạn đã có sự tác động vào website.!");
				request.setAttribute("layout", "error");
				RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
				re.forward(request, response);	
				return;
			}
		}
		else{
			error_page(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/* functions prosessin follow pathinfo */
	protected void store_menu_dish(HttpServletRequest request, HttpServletResponse response, int id_cate)
			throws ServletException, IOException {
		CategoryDAO cateDAO = new CategoryDAO();
		Vector<Category> vtcatemenu=new Vector<Category>();
		HashMap<Integer,Vector<Dish>> hmAllDM=new HashMap<Integer,Vector<Dish>>();
		DishDAO dishDAO=new DishDAO();
		if(!cateDAO.CheckExist_Category_Level_Two(id_cate)){			
			request.setAttribute("error","Danh mục không tồn tại,bạn đã có sự tác động vào website.!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
			re.forward(request, response);	
			return;
		}
		if(cateDAO.CheckExist_Category_Child(id_cate)){
			 vtcatemenu=cateDAO.AllCategorySp_id(id_cate);
			 hmAllDM=new HashMap<Integer,Vector<Dish>>();
			for(Category item:vtcatemenu){
				Vector<Dish> vtdish=dishDAO.AllFollow_Category(item.getId_dm());
				if(vtdish.size()>0){
				hmAllDM.put(item.getId_dm(),vtdish);
				}
			}
		}
		
		Vector<Dish> vtdish_dishtype=dishDAO.AllFollow_Category(id_cate);		
		request.setAttribute("name_dishtype",cateDAO.Name_Category(id_cate));
		request.setAttribute("content_dishtype",cateDAO.Content_Category_Parent(id_cate));
		request.setAttribute("menu", vtcatemenu);
		request.setAttribute("name_parent",cateDAO.Name_Category_Parent(id_cate));
		request.setAttribute("hm_dish_menu", hmAllDM);
		request.setAttribute("vt_dish_dishtype", vtdish_dishtype);
		request.setAttribute("layout", "menu_dish");
		RequestDispatcher re=request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request,response);
		
		
	}
	protected void detail_dish(HttpServletRequest request, HttpServletResponse response, int id_dish)
			throws ServletException, IOException {
		DishDAO dishDAO=new DishDAO();
		if(!dishDAO.CheckExist_Dish(id_dish)){			
			request.setAttribute("error","Món ăn không tồn tại,bạn đã có sự tác động vào website.!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
			re.forward(request, response);	
			return;
		}
		Dish dish=dishDAO.GetDishId(id_dish);		
		request.setAttribute("dish",dish);
		request.setAttribute("layout","dish_detail");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/ClientViewStart.jsp");
		re.forward(request, response);	
		
	}
	protected void error_page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		response.sendRedirect(request.getContextPath()+"/error-page");			
	}
}
