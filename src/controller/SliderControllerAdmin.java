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
import dao.ImageDao;
import dao.SliderDAO;
import model.Slider;

/**
 * Servlet implementation class SliderControllerAdmin
 */
@WebServlet("/admin/slider/*")
@MultipartConfig
public class SliderControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SliderControllerAdmin() {
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
		/* layout page infomation.jsp = 2 */
		SliderDAO sliderDAO = new SliderDAO();
		Vector<Slider> allslider = sliderDAO.AllSlider();
		request.setAttribute("allslider", allslider);
		request.setAttribute("layout", "slider");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);

	}

	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Part part = request.getPart("file-image");
		String image_name = null;
		if (!part.getSubmittedFileName().equals("")) {
			image_name = ImageDao.imageUpload(request, Slider.uploadDir, part);
			if (image_name == null) {
				/* tương đướng với gọi hàm index ở trên */
				response.sendRedirect(request.getContextPath() + "/admin/slider");
				return;
			}
			Slider sl = new Slider();
			sl.setTitle(request.getParameter("title_image"));
			sl.setName_image(image_name);
			SliderDAO sliderDAO = new SliderDAO();
			if (sliderDAO.AddSlider(sl)) {

				request.getSession().setAttribute("flash_success", "Thêm ảnh thành công..!");
			} else {

				request.getSession().setAttribute("flash_error", "Thêm ảnh  không thành công..!");
			}
			/* tương đướng với gọi hàm index ở trên */
			response.sendRedirect(request.getContextPath() + "/admin/slider");
		} else {
			request.getSession().setAttribute("flash_error", "Upload ảnh không thành công,ảnh chưa được chọn.!");
			response.sendRedirect(request.getContextPath() + "/admin/slider");
		}

	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Part part = request.getPart("file-image");
		String image_name = null;
		if (!part.getSubmittedFileName().equals("")) {
			image_name = ImageDao.imageUpload(request, Slider.uploadDir, part);
			if (image_name == null) {
				/* tương đướng với gọi hàm index ở trên */
				response.sendRedirect(request.getContextPath() + "/admin/slider");
				return;
			}
		}
		SliderDAO sliderDAO = new SliderDAO();
		if (sliderDAO.SliderFollowId(Integer.parseInt(request.getParameter("id_slider"))) == null) {
			request.getSession().setAttribute("flash_error", "Slider không tồn tại.!");
			response.sendRedirect(request.getContextPath() + "/admin/slider");
			return;
		}
		Slider sl =sliderDAO.SliderFollowId(Integer.parseInt(request.getParameter("id_slider")));
		sl.setTitle(request.getParameter("title_slider"));
		if(image_name!=null){
			sl.setName_image(image_name);
		}	
		if (sliderDAO.UpdateSlider(sl)) {

			request.getSession().setAttribute("flash_success", "Sửa  thành công..!");
		} else {

			request.getSession().setAttribute("flash_error", "Sửa không thành công..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/slider");

	}

	protected void show(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("show " + id);
	}

	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("store");
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("edit " + id);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		SliderDAO sliderDAO = new SliderDAO();
		if (sliderDAO.DeleteSlider(id)) {
			request.getSession().setAttribute("flash_success", "Xóa thành công..!");
		} else {

			request.getSession().setAttribute("flash_error", "Xóa ảnh  không thành công..!");
		}
		/* tương đướng với gọi vào hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/slider");

	}

	protected void errorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("errorPage");
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
