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
import javax.servlet.http.HttpSession;
public class AddStudent extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        String tid = (String) session.getAttribute("teacher_id") ; 
        int teacher_id=Integer.parseInt(tid);    
        try 
        {
            String student_seatno = request.getParameter("student_seatno");
            String student_name = request.getParameter("student_name");
            int subject_1_marks = Integer.parseInt( request.getParameter("subject_1_marks") );
            int subject_2_marks = Integer.parseInt( request.getParameter("subject_2_marks") );
            int subject_3_marks = Integer.parseInt( request.getParameter("subject_3_marks") );
            int subject_4_marks = Integer.parseInt( request.getParameter("subject_4_marks") );
            int subject_5_marks = Integer.parseInt( request.getParameter("subject_5_marks") );
            String url="jdbc:mysql://localhost:3306/onlineresult";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,"root","");
            Statement st=con.createStatement();
            PreparedStatement ps = con.prepareStatement("insert into student values (?,?,?,?,?,?,?,?)");
            ps.setString(1, student_seatno);
            ps.setString(2, student_name);
            ps.setInt(3, teacher_id);
            ps.setInt(4, subject_1_marks);
            ps.setInt(5, subject_2_marks);
            ps.setInt(6, subject_3_marks);
            ps.setInt(7, subject_4_marks);
            ps.setInt(8, subject_5_marks);
            int result = ps.executeUpdate();
            if(result==1){
                response.sendRedirect("AdminServlet");
            }else {
                out.println("Student Didn't Got Saved Into Database");
            }
        }
        catch (Exception e){
            out.println(e);
        }
    }
}