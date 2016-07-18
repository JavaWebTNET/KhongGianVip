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

import dao.CheckNumberString;
import dao.DishDAO;
import dao.ImageDao;
import model.Dish;

/**
 * Servlet implementation class DishController
 */

@WebServlet("/admin/dish/*")
@MultipartConfig
public class DishControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DishControllerAdmin() {
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
		if (pathinfo == null && request.getQueryString()!= null && (request.getParameter("name_search")==null)) {
			errorPage(request, response, "Đường dẫn không đúng định dạng..!");
		} else if ((pathinfo == null) || pathinfo.equals("/")) {
			store(request, response);
		} else if (pathinfo.equals("/detail-dish")) {
			if (request.getQueryString() != null && request.getQueryString().split("=").length == 2
					&& CheckNumberString.CheckNumber(request.getQueryString().split("=")[1])) {
				String query[] = request.getQueryString().split("=");
				int id = Integer.parseInt(query[1]);
				detail(request, response, id);
			} else {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}
		} else if (pathinfo.equals("/delete")) {
			try {
				if (request.getQueryString() != null && ((request.getQueryString().split("=").length == 2
						&& request.getParameter("name_search")!=null) ||request.getQueryString().split("=").length == 3
						&& request.getParameter("name_search")!=null)
						&& CheckNumberString.CheckNumber(request.getParameter("id"))) {					
					int id = Integer.parseInt(request.getParameter("id"));
					String name_search=request.getParameter("name_search");
					delete(request, response, id,name_search);
				} else {
					errorPage(request, response, "Tham số truyền không đúng định dạng..!");
				}
			} catch (NullPointerException ex) {
				errorPage(request, response, "Tham số truyền không đúng định dạng..!");
			}
		} else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathinfo = request.getPathInfo();
		if (pathinfo.equals("/update")) {
			update(request, response);
		} else if (pathinfo.equals("/search-name")) {
			seach_name_ajax(request, response);
		} else if (pathinfo.equals("/tasty-checked")) {
			chedked_dish_tasty(request, response);
		} else if (pathinfo.equals("/tasty-unchecked")) {
			unchedked_dish_tasty(request, response);
		} else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}

	}

	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("layout", "dish");		
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);
	}

	protected void detail(HttpServletRequest request, HttpServletResponse response, int id_dish)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DishDAO dishDAO = new DishDAO();
		if (!dishDAO.CheckExist_Dish(id_dish)) {
			request.setAttribute("error", "Món ăn không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);
			return;
		}
		Dish dish = dishDAO.GetDishId(id_dish);
		request.setAttribute("dish", dish);
		request.setAttribute("layout", "detail_dish");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		DishDAO dishDAO = new DishDAO();

		if (request.getParameter("id_dish") == null
				|| !dishDAO.CheckExist_Dish(Integer.parseInt(request.getParameter("id_dish")))) {
			request.setAttribute("error", "Món ăn không tồn tại..!");
			request.setAttribute("layout", "error");
			RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
			re.forward(request, response);
			return;
		}
		int id_dish = Integer.parseInt(request.getParameter("id_dish"));
		Part part = request.getPart("file-image");
		String image_name = null;
		if (!part.getSubmittedFileName().equals("")) {
			image_name = ImageDao.imageUpload(request, Dish.uploadDir, part);
			if (image_name == null) {
				request.getSession().setAttribute("flash_error_dish", request.getSession().getAttribute("flash_error"));
				request.getSession().removeAttribute("flash_error");
				response.sendRedirect(request.getContextPath() + "/admin/dish/detail-dish?id=" + id_dish);
				return;
			}
		}
		if (dishDAO.CheckExist_Dish_Name(request.getParameter("name_dish"))
				&& dishDAO.GetIdFollowName(request.getParameter("name_dish")) != id_dish) {
			request.getSession().setAttribute("flash_error_dish", "Tên món ăn đã tồn tại trong danh sách..!");
			response.sendRedirect(request.getContextPath() + "/admin/dish/detail-dish?id=" + id_dish);
			return;
		}
		Dish dish = dishDAO.GetDishId(id_dish);
		dish.setId_dish(id_dish);
		dish.setName_dish(request.getParameter("name_dish"));
		dish.setPrice(request.getParameter("price_dish"));
		dish.setContent_dish(request.getParameter("conten_dish"));
		if (image_name != null) {
			dish.setImage_dish(image_name);
		}
		if (dishDAO.UpdateDish(dish)) {
			request.getSession().setAttribute("flash_success_dish", "Cập nhật thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Cập nhật thất bại..!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/dish/detail-dish?id=" + id_dish);

	}

	protected void seach_name_ajax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name_dish = request.getParameter("namedish");

		DishDAO dishDAO = new DishDAO();
		Vector<Dish> vtdish = dishDAO.Seach_Dish_FollowName(name_dish);
		request.setAttribute("layout", "result_search_dish");
		request.setAttribute("name_search_input", name_dish);
		request.setAttribute("dish_seach", vtdish);
		RequestDispatcher re = request.getRequestDispatcher("/View/Admin/result.jsp");
		re.forward(request, response);
	}
	
	protected void chedked_dish_tasty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int id_dish =Integer.parseInt(request.getParameter("id_dish"));
		DishDAO dishDAO = new DishDAO();
				
		if (!dishDAO.CheckExist_Dish(id_dish)) {
			response.setContentType("text/plain"); 
			response.getWriter().write("Món ăn không tồn tại..!");	
			return;
		}	
		String kq ="check thất bại..!"; 
		if(dishDAO.UpdateDish_CheckedTasty(id_dish)){			
			kq = "check thành công..!"; 						
		}
		response.setContentType("text/plain"); 
		response.getWriter().write(kq);		
	}
	protected void unchedked_dish_tasty(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int id_dish =Integer.parseInt(request.getParameter("id_dish"));
		DishDAO dishDAO = new DishDAO();
		if (!dishDAO.CheckExist_Dish(id_dish)) {
			response.setContentType("text/plain"); 
			response.getWriter().write("Món ăn không tồn tại..!");	
			return;
		}	
		String kq = "uncheck thất bại..!";
		if(dishDAO.UpdateDish_UnCheckedTasty(id_dish)){
			kq = "uncheck thành công..!"; 
		}
		response.setContentType("text/plain"); 
		response.getWriter().write(kq);			
	}
	protected void delete(HttpServletRequest request, HttpServletResponse response, int id_dish,String name_search)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DishDAO dishDAO = new DishDAO();
		if (!dishDAO.CheckExist_Dish(id_dish)) {
			request.getSession().setAttribute("flash_error_dish", "Món ăn không tồn tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/dish");
			return;
		}
		if (dishDAO.DeleteDish(id_dish)) {
			request.getSession().setAttribute("flash_success_dish", "Xóa thành công..!");
		} else {
			request.getSession().setAttribute("flash_error_dish", "Xóa thất bại..!");
		}
		response.sendRedirect(request.getContextPath() + "/admin/dish"+(name_search.length()>0?"?name_search="+name_search:""));
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
