import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DeleteStudent extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try  
        {
            String seatno = request.getParameter("seatno");
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/onlineresult";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement st=con.createStatement();
            PreparedStatement ps=con.prepareStatement("delete from student where student_seatno=?");
            ps.setString(1, seatno);
            int result=ps.executeUpdate();
            if(result==1){
                out.println("Student Deleted From Database");
                response.sendRedirect("AdminServlet");
            }else{
                out.println("Student Deletion Failed");
            }
        }
        catch(Exception e){
            out.println(e);
        }
    }
}
