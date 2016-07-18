package controller;

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
 * Servlet implementation class NewsControllerAdmin
 */
@WebServlet("/admin/news/*")
public class NewsControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsControllerAdmin() {
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
			index(request, response);
		} else if (pathinfo.equals("/delete")) {
			String query[] = request.getQueryString().split("=");
			if (query.length == 2 && CheckNumberString.CheckNumber(query[1])) {
				int id = Integer.parseInt(query[1]);
				delete(request, response, id);
			} else {
				errorPage(request, response, "Tham số truyền vào không đúng định dạng..!");
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
		if (pathinfo.equals("/create")) {
			create(request, response);
		} else if (pathinfo.equals("/update")) {
			update(request, response);
		} else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}
	}

	/* functions prosessin follow pathinfo */
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		NewsDAO newdao = new NewsDAO();
		Vector<News> vtnews = newdao.AllNews();
		request.setAttribute("allnews", vtnews);
		request.setAttribute("layout", "news");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		NewsDAO newsDAO = new NewsDAO();
		if (newsDAO.DeleteNews(id)) {
			request.getSession().setAttribute("flash_success", "Xóa thành công..!");
		} else {

			request.getSession().setAttribute("flash_error", "Xóa  không thành công..!");
		}
		// tươnggọi vào hàm index ở trên
		response.sendRedirect(request.getContextPath() + "/admin/news");

	}

	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		News ns = new News();
		if (request.getParameter("add_content").equals("")) {
			request.getSession().setAttribute("flash_error", "Nội dung không được trống..!");
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		ns.setTitle(request.getParameter("title_add_news"));
		ns.setContent(request.getParameter("add_content"));

		NewsDAO newsDAO = new NewsDAO();
		if (newsDAO.AddNews(ns)) {
			request.getSession().setAttribute("flash_success", "Thêm tim mới thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Thêm  không thành công..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/news");

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		News ns = new News();
		ns.setId(Integer.parseInt(request.getParameter("id_news")));
		if (request.getParameter("content_news").equals("")) {
			request.getSession().setAttribute("flash_error", "Nội dung không được trống..!");
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		ns.setTitle(request.getParameter("title_news"));
		ns.setContent(request.getParameter("content_news"));

		NewsDAO newsDAO = new NewsDAO();
		if (newsDAO.UpdateNews(ns)) {
			request.getSession().setAttribute("flash_success", "Cập nhật thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Cập nhật không thành công..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/news");

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
