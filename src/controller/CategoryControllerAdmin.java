package controller;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.CategoryDAO;
import dao.CheckNumberString;
import dao.DishDAO;
import dao.Dish_Cate_DAO;
import dao.ImageDao;
import model.Category;
import model.Dish;


/**
 * Servlet implementation class CategoryControllerAdmin
 */
@WebServlet("/admin/category/*")
@MultipartConfig
public class CategoryControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryControllerAdmin() {
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
		if ((pathinfo == null && request.getQueryString() != null)) {
			errorPage(request, response, "Đường dẫn không đúng định dạng..!");
		} else if ((pathinfo == null) || pathinfo.equals("/")) {
			index(request, response);
		} else if (pathinfo.equals("/delete") || pathinfo.equals("/dish-types/delete")
				|| pathinfo.equals("/dish-types-menu/delete")) {
			try {// catch while id || sp_id not exist,error in CheckNumberString.java
				if (request.getQueryString() != null
						&& (request.getQueryString().split("=").length == 2
						|| request.getQueryString().split("=").length == 3)
						&& CheckNumberString.CheckNumber(request.getParameter("id"))
						&& (request.getQueryString().split("=").length == 2
								|| CheckNumberString.CheckNumber(request.getParameter("sp_id")))) {
					String sp_id = "";
					int id = Integer.parseInt(request.getParameter("id"));
					if (request.getParameter("sp_id") != null) {
						sp_id = request.getParameter("sp_id");
					}
					delete(request, response, id, sp_id);
				} else {
					errorPage(request, response, "Tham số truyền không đúng định dạng..!");
				}
			} catch (NullPointerException ex) {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}
		} else if (pathinfo.equals("/dish-types")) {

			if (request.getQueryString() != null && request.getQueryString().split("=").length == 2
					&& CheckNumberString.CheckNumber(request.getQueryString().split("=")[1])) {
				String query[] = request.getQueryString().split("=");
				int id = Integer.parseInt(query[1]);
				store_dish_type(request, response, id);
			} else {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}
		} else if (pathinfo.equals("/dish-types-menu")) {
			if (request.getQueryString() != null && request.getQueryString().split("=").length == 2
					&& CheckNumberString.CheckNumber(request.getQueryString().split("=")[1])) {
				String query[] = request.getQueryString().split("=");
				int id = Integer.parseInt(query[1]);
				store_dishtype_menu(request, response, id);
			} else {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}
		} else if (pathinfo.equals("/dish-menu")) {
			if (request.getQueryString() != null && request.getQueryString().split("=").length == 2
					&& CheckNumberString.CheckNumber(request.getQueryString().split("=")[1])) {
				String query[] = request.getQueryString().split("=");
				int id = Integer.parseInt(query[1]);
				store_dish_menu(request, response, id);
			} else {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}
		}else if (pathinfo.equals("/dish-types-menu/delete-dish") || pathinfo.equals("/dish-menu/delete")) {
			if (request.getQueryString() != null
			|| request.getQueryString().split("=").length == 3
			&& CheckNumberString.CheckNumber(request.getParameter("id_dish"))
			&& CheckNumberString.CheckNumber(request.getParameter("id_cate"))) {
				int id_dish = Integer.parseInt(request.getParameter("id_dish"));
				int id_cate = Integer.parseInt(request.getParameter("id_cate"));
				delete_dish_dishtype_menu(request,response,id_dish,id_cate);
				
			} else {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}		
		}		
	else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}
	}

	/** dish-types-menu/delete-dish
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub 
		String pathinfo = request.getPathInfo();
		if (pathinfo.equals("/create")) {
			create(request, response);
		} else if (pathinfo.equals("/update")) {
			update(request, response);
		} else if (pathinfo.equals("/create-dishtype")) {
			create_dishtype(request, response);
		} else if (pathinfo.equals("/update-dishtype")) {
			update_dishtype(request, response);
		} else if (pathinfo.equals("/create-menu-dishtype")) {
			create_menu_dishtype(request, response);
		} else if (pathinfo.equals("/update-menu-dishtype")) {
			update_menu_dishtype(request, response);
		} else if (pathinfo.equals("/create-dish-dishtype")) {
			create_dish_dishtype(request, response);
		} else if (pathinfo.equals("/create-dish-dishtype-select")) {
			create_dish_dishtype_select(request, response);
		} else if (pathinfo.equals("/create-dish-menu")) {
			create_dish_menu(request, response);
		}
		  else if (pathinfo.equals("/create-dish-menu-select")) {
			create_dish_menu_select(request, response);			
		} else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}
	}

	/* functions prosessin follow pathinfo */
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CategoryDAO cateDAO = new CategoryDAO();
		Vector<Category> vtcate = cateDAO.Category(0);
		request.setAttribute("layout", "cate_parent");
		request.setAttribute("cateparent", vtcate);
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);
	}

	/* add category parent have sp_id=0, this is category parent */
	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Category cate = new Category();
		cate.setTitle(request.getParameter("name_category_add"));
		cate.setContent_dm(request.getParameter("conten_category_add"));
		cate.setSp_id(0);
		CategoryDAO cateDAO = new CategoryDAO();
		if (cateDAO.AddCategory(cate)) {
			request.getSession().setAttribute("flash_success", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Thêm thất bại..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Category cate = new Category();
		cate.setId_dm(Integer.parseInt(request.getParameter("id_category")));
		cate.setSp_id(Integer.parseInt(request.getParameter("sp_id")));
		cate.setTitle(request.getParameter("name_category_edit"));
		cate.setContent_dm(request.getParameter("conten_category_edit"));
		cate.setSp_id(0);
		CategoryDAO cateDAO = new CategoryDAO();
		if (cateDAO.UpdateCategory(cate)) {
			request.getSession().setAttribute("flash_success", "Cập nhật thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Cập nhật thất bại..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/category");
	}

	/* add in category with sp_id=id(category have sp_id=0), this is dishtype */
	protected void create_dishtype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Category cate = new Category();
		cate.setSp_id(Integer.parseInt(request.getParameter("sp_id")));
		cate.setTitle(request.getParameter("name_dishtype_add"));
		cate.setContent_dm(request.getParameter("conten_dishtype_add"));
		CategoryDAO cateDAO = new CategoryDAO();
		if (cateDAO.AddCategory(cate)) {
			request.getSession().setAttribute("flash_success", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Thêm thất bại..!");
		}
		// tương đướng với gọi hàm index ở trên
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-types?id=" + cate.getSp_id());
	}

	protected void update_dishtype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Category cate = new Category();
		cate.setId_dm(Integer.parseInt(request.getParameter("id_dishtype")));
		cate.setSp_id(Integer.parseInt(request.getParameter("sp_id")));
		cate.setTitle(request.getParameter("name_dishtype_edit"));
		cate.setContent_dm(request.getParameter("conten_dishtype_edit"));
		CategoryDAO cateDAO = new CategoryDAO();
		if (cateDAO.UpdateCategory(cate)) {
			request.getSession().setAttribute("flash_success", "Cập nhật thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Cập nhật thất bại..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-types?id=" + cate.getSp_id());
	}

	protected void store_dish_type(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		CategoryDAO cateDAO = new CategoryDAO();
		if(!cateDAO.CheckExist_Category(id)){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
		}
		Vector<Category> vtcate = cateDAO.Category(id);
		request.setAttribute("name-typecustomer",cateDAO.Name_Category(id));
		request.setAttribute("id_dm", id);
		request.setAttribute("layout", "dish_type");
		request.setAttribute("catedishtype", vtcate);
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);
	}

	/*
	 * show all category have sp_id=id(is dish type),this is menu of dish type
	 */
	protected void store_dishtype_menu(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		
		CategoryDAO cateDAO = new CategoryDAO();
		if(!cateDAO.CheckExist_Category(id)){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
		}
		DishDAO dishdao=new DishDAO();
		Vector<Dish> vtdish=dishdao.AllFollow_Category(id);
		Vector<Category> vtcate = cateDAO.Category(id);
		Vector<Dish> vtalldish=dishdao.AllDish();
		request.setAttribute("name-typedish",cateDAO.Name_Category(id));
		request.setAttribute("id_dm", id);
		request.setAttribute("layout", "dishtype_menu");
		request.setAttribute("alldish", vtalldish);
		request.setAttribute("dish-dishtype", vtdish);
		request.setAttribute("menu-dishtype", vtcate);
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);

	}

	protected void create_menu_dishtype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Category cate = new Category();
		cate.setSp_id(Integer.parseInt(request.getParameter("sp_id")));
		cate.setTitle(request.getParameter("name_menu_dishtype_add"));
		cate.setContent_dm(request.getParameter("conten_menu_dishtype_add"));
		CategoryDAO cateDAO = new CategoryDAO();
		if (cateDAO.AddCategory(cate)) {
			request.getSession().setAttribute("flash_success", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Thêm thất bại..!");
		}
		// tương đướng với gọi hàm index ở trên
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id=" + cate.getSp_id());

	}	

	protected void update_menu_dishtype(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Category cate = new Category();
		cate.setId_dm(Integer.parseInt(request.getParameter("id_menu_dishtype")));
		cate.setSp_id(Integer.parseInt(request.getParameter("sp_id")));
		cate.setTitle(request.getParameter("name_menu_dishtype_edit"));
		cate.setContent_dm(request.getParameter("conten_menu_dishtype_edit"));

		CategoryDAO cateDAO = new CategoryDAO();
		if (cateDAO.UpdateCategory(cate)) {
			request.getSession().setAttribute("flash_success", "Cập nhật thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Cập nhật thất bại..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id=" + cate.getSp_id());

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response, int id, String sp_id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		CategoryDAO sliderDAO = new CategoryDAO();
		if (sliderDAO.DeleteCategory(id)) {
			request.getSession().setAttribute("flash_success", "Xóa thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Xóa  không thành công..!");
		}
		// tương đướng với gọi vào hàm index ở trên
		String pathinfo[] = request.getPathInfo().split("/");
		response.sendRedirect(request.getContextPath() + "/admin/category/"
				+ (pathinfo.length == 3 ? pathinfo[1] + "?sp_id=" + sp_id : ""));
	}
	protected void create_dish_dishtype(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		CategoryDAO cateDAO=new CategoryDAO();
		Dish dish=new Dish();
		DishDAO dishDAO= new DishDAO();		
		if(request.getParameter("id_cate")==null || !cateDAO.CheckExist_Category(Integer.parseInt(request.getParameter("id_cate")))){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
			}
		int id_cate=Integer.parseInt(request.getParameter("id_cate"));
		if(dishDAO.CheckExist_Dish_Name(request.getParameter("name_dish_dishtype"))){
			request.getSession().setAttribute("flash_error_dish","Món ăn đã tồn tại trong danh sách,vui lòng chọn thêm món từ danh sách");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);	
			return;
		}
		Part part = request.getPart("file-image");
		String image_name = null;
		if (!part.getSubmittedFileName().equals("")) {
			image_name = ImageDao.imageUpload(request, Dish.uploadDir, part);
			if (image_name == null) {	
				request.getSession().setAttribute("flash_error_dish", request.getSession().getAttribute("flash_error"));
				request.getSession().removeAttribute("flash_error");
				response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);	
				return;
			}
		}		
		dish.setName_dish(request.getParameter("name_dish_dishtype"));
		dish.setPrice(request.getParameter("price_dish_dishtype"));
		dish.setContent_dish(request.getParameter("conten_dish_dishtype_add"));
		if(image_name!=null){
			dish.setImage_dish(image_name);
		}			
		if (dishDAO.AddDish_Cate(dish, id_cate)) {
			request.getSession().setAttribute("flash_success_dish", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Thêm thất bại..!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);	
	}
	
protected void create_dish_menu(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		CategoryDAO cateDAO=new CategoryDAO();
		Dish dish=new Dish();
		DishDAO dishDAO= new DishDAO();	
			
		if(request.getParameter("id_cate")==null || !cateDAO.CheckExist_Category(Integer.parseInt(request.getParameter("id_cate")))){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
			}
		int id_cate=Integer.parseInt(request.getParameter("id_cate"));
		if(dishDAO.CheckExist_Dish_Name(request.getParameter("name_dish_dishtype"))){
			request.getSession().setAttribute("flash_error_dish","Món ăn đã tồn tại trong danh sách,vui lòng chọn thêm món từ danh sách");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-menu?id="+id_cate);	
			return;
		}
			
		Part part = request.getPart("file-image");
		String image_name = null;
		if (!part.getSubmittedFileName().equals("")) {
			image_name = ImageDao.imageUpload(request, Dish.uploadDir, part);
			if (image_name == null) {	
				request.getSession().setAttribute("flash_error_dish", request.getSession().getAttribute("flash_error"));
				request.getSession().removeAttribute("flash_error");
				response.sendRedirect(request.getContextPath() + "/admin/category/dish-menu?id="+id_cate);	
				return;
			}
		}		
		dish.setName_dish(request.getParameter("name_dish_dishtype"));
		dish.setPrice(request.getParameter("price_dish_dishtype"));
		dish.setContent_dish(request.getParameter("conten_dish_dishtype_add"));
		if(image_name!=null){
			dish.setImage_dish(image_name);
		}			
		if (dishDAO.AddDish_Cate(dish, id_cate)) {
			request.getSession().setAttribute("flash_success_dish", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Thêm thất bại..!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-menu?id="+id_cate);	
	}
	
	protected void create_dish_dishtype_select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		CategoryDAO cateDAO=new CategoryDAO();	
		DishDAO dishDAO=new DishDAO();
		if(request.getParameter("id_cate")==null || !CheckNumberString.CheckNumber(request.getParameter("id_cate")) || !cateDAO.CheckExist_Category(Integer.parseInt(request.getParameter("id_cate")))){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
			}	
		int id_cate=Integer.parseInt(request.getParameter("id_cate"));
		if(request.getParameter("id_dish")==null || !CheckNumberString.CheckNumber(request.getParameter("id_dish")) || !dishDAO.CheckExist_Dish(Integer.parseInt(request.getParameter("id_dish")))){			
			request.getSession().setAttribute("flash_error_dish", "Món ăn không tồn tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);
			return;
			}
		int id_dish=Integer.parseInt(request.getParameter("id_dish"));
		Dish_Cate_DAO dish_cate_dao=new Dish_Cate_DAO();
		if(dish_cate_dao.CheckExist_Dish_Cate(id_dish, id_cate)){			
			request.getSession().setAttribute("flash_error_dish", "Món ăn đã tồn tại trong danh mục hiện tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);
			return;
			}				
		if (dish_cate_dao.AddDish_Cate(id_dish, id_cate)) {
			request.getSession().setAttribute("flash_success_dish", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Thêm thất bại..!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);
	}
	protected void create_dish_menu_select(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		Dish_Cate_DAO dish_cate_dao=new Dish_Cate_DAO();
		CategoryDAO cateDAO=new CategoryDAO();
		DishDAO dishDAO=new DishDAO();
		if(request.getParameter("id_cate")==null || !CheckNumberString.CheckNumber(request.getParameter("id_cate")) || !cateDAO.CheckExist_Category(Integer.parseInt(request.getParameter("id_cate")))){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
			}	
		int id_cate=Integer.parseInt(request.getParameter("id_cate"));
		if(request.getParameter("id_dish")==null || !CheckNumberString.CheckNumber(request.getParameter("id_dish")) || !dishDAO.CheckExist_Dish(Integer.parseInt(request.getParameter("id_dish")))){			
			request.getSession().setAttribute("flash_error_dish", "Món ăn không tồn tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-menu?id="+id_cate);
			return;
			}
		int id_dish=Integer.parseInt(request.getParameter("id_dish"));
		
		if(dish_cate_dao.CheckExist_Dish_Cate(id_dish, id_cate)){			
			request.getSession().setAttribute("flash_error_dish", "Món ăn đã tồn tại trong danh mục hiện tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-menu?id="+id_cate);
			return;
			}				
		if (dish_cate_dao.AddDish_Cate(id_dish, id_cate)) {
			request.getSession().setAttribute("flash_success_dish", "Thêm thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Thêm thất bại..!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/category/dish-menu?id="+id_cate);
	}
	/*delete a dish in category dish_type*/
	protected void delete_dish_dishtype_menu(HttpServletRequest request, HttpServletResponse response,int id_dish,int id_cate)throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		CategoryDAO cateDAO=new CategoryDAO();	
		if(!cateDAO.CheckExist_Category(id_cate)){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
			}		
		Dish_Cate_DAO dish_cate_dao=new Dish_Cate_DAO();
		if(!dish_cate_dao.CheckExist_Dish_Cate(id_dish, id_cate)){			
			request.getSession().setAttribute("flash_error_dish", "Bản ghi không tồn tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/category/dish-types-menu?id="+id_cate);
			return;
			}				
		if (dish_cate_dao.Delete_Dish_Cate(id_dish, id_cate)) {
			request.getSession().setAttribute("flash_success_dish", "Xóa thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Xóa thất bại..!");
		}
		String pathinfo[]=request.getPathInfo().split("/");
		response.sendRedirect(request.getContextPath() + "/admin/category/"+pathinfo[1]+"?id="+id_cate);
	}
	protected void store_dish_menu(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		
		CategoryDAO cateDAO = new CategoryDAO();
		if(!cateDAO.CheckExist_Category(id)){			
			request.setAttribute("error","Danh mục không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);	
			return;
		}
		DishDAO dishdao=new DishDAO();
		Vector<Dish> vtdish=dishdao.AllFollow_Category(id);		
		Vector<Dish> vtalldish=dishdao.AllDish();
		request.setAttribute("name-menu",cateDAO.Name_Category(id));
		request.setAttribute("id_dm", id);
		request.setAttribute("layout", "dish_menu");
		request.setAttribute("alldish", vtalldish);
		request.setAttribute("dish-menu", vtdish);		
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);
	}	
	protected void errorPage(HttpServletRequest request, HttpServletResponse response, String error)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("error", error);
		request.setAttribute("layout", "error");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);

	}

}
