package controller;

import java.io.IOException;

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
import dao.InformationDAO;
import model.Information;

/**
 * Servlet implementation class InformationControllerAdmin
 */
@WebServlet("/admin/information/*")
@MultipartConfig
public class InformationControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InformationControllerAdmin() {
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
		}
		else{
			errorPage(request,response,"đường dẫn không tồn tại..!");
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
		}
		else{
			errorPage(request,response,"đường dẫn không tồn tại..!");
		}
	}

	/* functions prosessin follow pathinfo */
	protected void index(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* layout page infomation.jsp = 2 */
		InformationDAO infoDAO = new InformationDAO();
		Information info = infoDAO.AllInformation();
		request.setAttribute("AllInfo", info);
		request.setAttribute("layout","information");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);

	}

	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("da vao create");

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

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Part part = request.getPart("file-image");
		String image_infor = null;
		if (!part.getSubmittedFileName().equals("")) {
			image_infor = ImageDao.imageUpload(request, Information.uploadDir, part);
			if (image_infor == null) {
				/*tương đướng với gọi hàm index ở trên*/
				response.sendRedirect(request.getContextPath() + "/admin/information");

				return;
			}
		}
		String sdt=request.getParameter("tell");	
		String sdtcut=sdt.replace(".","");
		sdtcut=sdtcut.replace("-","");
		sdtcut=sdtcut.replace(" ","");
		if(!CheckNumberString.CheckNumber(sdtcut) || sdtcut.length()<10 || sdtcut.length()>11 ){
			String error = "Số điện thoại không đúng định dạng.SĐT phải là số,nhỏ hơn bằng 11 và lớn bằng 10 số (không tính ký tự '.' or '-')..!";
			request.getSession().setAttribute("flash_error",error);
			response.sendRedirect(request.getContextPath() + "/admin/information");
			return;
		} 		
		InformationDAO infoDAO = new InformationDAO();
		Information info =infoDAO.AllInformation();
		info.setName_res(request.getParameter("name_res"));
		info.setEmail(request.getParameter("email"));
		info.setAdd_res(request.getParameter("add_res"));
		info.setTell(sdt);
		info.setGioithieu(request.getParameter("gioithieu"));
		info.setTuyendung(request.getParameter("tuyendung"));
		if (image_infor != null) {
			info.setLogo(image_infor);
		}
		
	
		if (infoDAO.UpdateInfo(info)) {

			request.getSession().setAttribute("flash_success", "Cập nhật thông tin thành công..!");
		} else {

			request.getSession().setAttribute("flash_error", "Cập nhật không thành công..!");
		}
		/*tương đướng với gọi hàm index ở trên*/
		response.sendRedirect(request.getContextPath() + "/admin/information");

	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void errorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("errorPage");
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
