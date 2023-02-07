package servlets;

import edu.javacourse.tomcat.business.Cart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();
        Cart cart=(Cart)session.getAttribute("cart");
        String name=request.getParameter("name");
        Integer quantity= Integer.valueOf(request.getParameter("quantity"));
        if(cart==null) {
            cart=new Cart();
            cart.setName(name);
            cart.setQuantity(quantity);
        }else{

        }
        session.setAttribute("cart",cart);
        getServletContext().getRequestDispatcher("/show_cart.jsp").forward(request,response);
//        PrintWriter printWriter=response.getWriter();
//        printWriter.println("<html>");
//        printWriter.println("<h1>Your count is: "+count+"</h1>");
//        printWriter.println("<h1> Hello, "+name+" "+surName+"</h1>");
//        printWriter.println("<html>");
    }

}
