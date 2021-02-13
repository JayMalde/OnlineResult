import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            String url = "jdbc:mysql://localhost:3306/onlineresult";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url,"root","");
            Statement st = conn.createStatement();
            PreparedStatement p1 = conn.prepareStatement("select * from teacher where teacher_name=? and teacher_password=?");
            String name=request.getParameter("teacher_name");
            String password=request.getParameter("teacher_password");
            p1.setString(1,name);
            p1.setString(2,password);
            ResultSet result = p1.executeQuery();
            if(result.next()){
                HttpSession session=request.getSession();
                session.setAttribute("name",result.getString("teacher_name"));
                session.setAttribute("teacher_id",result.getString("teacher_id"));
                response.sendRedirect("AdminServlet");
            }
            else{
//                out.println("Username or Password Did Not Matched");
                request.getRequestDispatcher("login.html").include(request, response);
            }
        }
        catch(Exception e)
        {
            out.println(e);
        }
    }
}