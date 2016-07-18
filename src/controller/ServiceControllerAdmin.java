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
import dao.ServiceDAO;
import model.Service;

/**
 * Servlet implementation class ServiceControllerAdmin
 */
@WebServlet("/admin/service/*")
public class ServiceControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServiceControllerAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathinfo=request.getPathInfo();
		if(pathinfo==null || pathinfo.equals("")){
			store(request, response);
		}
		else if(pathinfo.equals("/delete")){
			if (request.getParameter("id_sv")!=null && CheckNumberString.CheckNumber(request.getParameter("id_sv"))) {
				int id =Integer.parseInt(request.getParameter("id_sv"));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected void store(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServiceDAO serviceDAO=new ServiceDAO();
		Vector<Service> vtservice=serviceDAO.All_Service();
		request.setAttribute("vtservice", vtservice);
		request.setAttribute("layout","service");
		RequestDispatcher re = request.getRequestDispatcher("/View/Shared/AdminViewStart.jsp");
		re.forward(request, response);
	}
	protected void create(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Service sv = new Service();
		if (request.getParameter("add_content").equals("")) {
			request.getSession().setAttribute("flash_error", "Nội dung không được trống..!");
			response.sendRedirect(request.getContextPath() + "/admin/service");
			return;
		}
		sv.setTitle_sv(request.getParameter("title_add_service"));
		sv.setContent_sv(request.getParameter("add_content"));

		ServiceDAO serviceDAO = new ServiceDAO();
		if (serviceDAO.Add_Service(sv)) {
			request.getSession().setAttribute("flash_success", "Thêm dịch vụ mới thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Thêm  không thành công..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/service");

	}
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ServiceDAO serviceDAO = new ServiceDAO();
		Service sv = new Service();
		sv.setId_sv(Integer.parseInt(request.getParameter("id_service")));
		if (request.getParameter("content_service").equals("")) {
			request.getSession().setAttribute("flash_error", "Nội dung không được trống..!");
			response.sendRedirect(request.getContextPath() + "/admin/service");
			return;
		}
		if (!serviceDAO.CheckExist_Service(Integer.parseInt(request.getParameter("id_service")))) {
			request.getSession().setAttribute("flash_error", "Dịch vụ không tồn tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/service");
			return;
		}
		sv.setTitle_sv(request.getParameter("title_service"));
		sv.setContent_sv(request.getParameter("content_service"));		
		if (serviceDAO.Update_Service(sv)) {
			request.getSession().setAttribute("flash_success", "Cập nhật thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Cập nhật không thành công..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/service");

	}
	protected void delete(HttpServletRequest request, HttpServletResponse response,int id_sv)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		
		ServiceDAO serviceDAO = new ServiceDAO();
		if (!serviceDAO.CheckExist_Service(id_sv)) {
			request.getSession().setAttribute("flash_error", "Dịch vụ không tồn tại..!");
			response.sendRedirect(request.getContextPath() + "/admin/service");
			return;
		}
		if (serviceDAO.Delete_Service(id_sv)) {
			request.getSession().setAttribute("flash_success", "Xóa thành công..!");
		} else {
			request.getSession().setAttribute("flash_error", "Xóa không thành công..!");
		}
		/* tương đướng với gọi hàm index ở trên */
		response.sendRedirect(request.getContextPath() + "/admin/service");

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
