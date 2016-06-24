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
import dao.ImageStoreDAO;

import model.ImageStore;


/**
 * Servlet implementation class ImageControllerAdmin
 */
@WebServlet("/admin/Imagestore/*")
@MultipartConfig
public class ImageControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageControllerAdmin() {
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

		}
		else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathinfo = request.getPathInfo();
		if (pathinfo.equals("/create")) {
			create(request, response);
		}
		else {
			errorPage(request, response, "đường dẫn không tồn tại..!");
		}
	}

	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* layout page infomation.jsp = 2 */
		ImageStoreDAO imDAO = new ImageStoreDAO();
		Vector<ImageStore> im = imDAO.AllImageStore();
		request.setAttribute("imagestore", im);
		request.setAttribute("layout", "imagestoreddmin");
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
			image_name = ImageDao.imageUpload(request, ImageStore.uploadDir, part);
			if (image_name == null) {
				/* tương đướng với gọi hàm index ở trên */
				response.sendRedirect(request.getContextPath() + "/admin/Imagestore");

				return;
			}

			ImageStore image = new ImageStore();
			image.setTitle(request.getParameter("title_image"));
			image.setName_image(image_name);
			ImageStoreDAO imagestDAO = new ImageStoreDAO();
			if (imagestDAO.AddImageStore(image)) {

				request.getSession().setAttribute("flash_success", "Thêm ảnh thành công..!");
			} else {

				request.getSession().setAttribute("flash_error", "Thêm ảnh  không thành công..!");
			}
			/* tương đướng với gọi hàm index ở trên */
			response.sendRedirect(request.getContextPath() + "/admin/Imagestore");
		} else {
			request.getSession().setAttribute("flash_error", "Upload ảnh không thành công,ảnh chưa được chọn.!");
			response.sendRedirect(request.getContextPath() + "/admin/Imagestore");
		}

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response, int id)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ImageStoreDAO imageDAO = new ImageStoreDAO();
		if (imageDAO.DeletemageStore(id)) {
			request.getSession().setAttribute("flash_success", "Xóa thành công..!");
		} else {

			request.getSession().setAttribute("flash_error", "Xóa ảnh  không thành công..!");
		}
		/* tương đướng với gọi vào hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/Imagestore");

		
	}

	protected void errorPage(HttpServletRequest request, HttpServletResponse response, String error)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("error",error);
		request.setAttribute("layout","error");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request,response);
		
		
	}
}
