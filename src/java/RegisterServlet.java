import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RegisterServlet extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String teacher_name=request.getParameter("teacher_name");
        String teacher_email=request.getParameter("teacher_email");
        String teacher_password=request.getParameter("teacher_password");
        String teacher_college_name=request.getParameter("teacher_college_name");
        
        String url="jdbc:mysql://localhost:3306/onlineresult";
        String sql="insert into teacher (teacher_name,teacher_email,teacher_password,teacher_college_name) values(?,?,?,?)";
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,"root","");
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,teacher_name);
            pst.setString(2,teacher_email);
            pst.setString(3,teacher_password);
            pst.setString(4,teacher_college_name);
            int val=pst.executeUpdate();
            if(val==1)
            {
                out.println("<center><h1>Record Saved into Database</h1></center");
            }
            else
            {
                out.println("<center><h1>Error In Saving The Record</h1></center>");
            }
        }
        catch(Exception e)
        {
            out.println(e);
        } 
    }
}