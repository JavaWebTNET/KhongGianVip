package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import dao.CategoryDAO;
import dao.DishDAO;
import dao.SliderDAO;
import model.Category;
import model.Dish;
import model.Slider;

/**
 * Servlet Filter implementation class HomeFilter
 */
@WebFilter("/*")
public class HomeFilter implements Filter {

    /**
     * Default constructor. 
     */
    public HomeFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		CategoryDAO categoryDAO=new CategoryDAO();
		DishDAO dishDAO=new DishDAO();
		SliderDAO sliderDAO= new SliderDAO();
		Vector<Slider> slidertop=sliderDAO.AllSlider();
		Vector<Dish> dishtasty=dishDAO.All_Dish_Follow_Tasty();
		HashMap<Integer,ArrayList<Category>> hmCategoryMenu=categoryDAO.Category_Two_Level();
		request.setAttribute("hmcategorymenu",hmCategoryMenu);
		request.setAttribute("dishtasty",dishtasty);
		request.setAttribute("slidertop",slidertop);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
