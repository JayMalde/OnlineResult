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
public class UpdateStudent2 extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session=request.getSession();
        String tid = (String) session.getAttribute("teacher_id") ; 
        int teacher_id=Integer.parseInt(tid);    
        PrintWriter out=response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        try 
        {
            out.println("Welcome, "+teacher_id);
            String student_seatno = request.getParameter("student_seatno");
            out.print(student_seatno);
            String student_name = request.getParameter("student_name");
            out.print(student_name);
            int subject_1_marks = Integer.parseInt( request.getParameter("subject_1_marks") );
            out.print(subject_1_marks);
            int subject_2_marks = Integer.parseInt( request.getParameter("subject_2_marks") );
            out.print(subject_2_marks);
            int subject_3_marks = Integer.parseInt( request.getParameter("subject_3_marks") );
            out.print(subject_3_marks);
            int subject_4_marks = Integer.parseInt( request.getParameter("subject_4_marks") );
            out.print(subject_4_marks);
            int subject_5_marks = Integer.parseInt( request.getParameter("subject_5_marks") );
            out.print(subject_5_marks);
            String url="jdbc:mysql://localhost:3306/onlineresult";
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(url,"root","");
            Statement st=con.createStatement();
            out.println("<br>"+student_seatno);
            PreparedStatement ps = con.prepareStatement("update student set student_name=?,subject_1_marks=?,subject_2_marks=?,subject_3_marks=?,subject_4_marks=?,subject_5_marks=? where student_seatno=?");
            ps.setString(1, student_name);
//            ps.setInt(3, teacher_id);
            ps.setInt(2, subject_1_marks);
            ps.setInt(3, subject_2_marks);
            ps.setInt(4, subject_3_marks);
            ps.setInt(5, subject_4_marks);
            ps.setInt(6, subject_5_marks);
            ps.setString(7, student_seatno);
            int result = ps.executeUpdate();
            if(result==1){
                out.println("Student Data Updated Into Database");
                response.sendRedirect("AdminServlet");
            }else {
                out.println("Updation Failed Into The Database");
            }
        } 
        catch (Exception e){
            out.println(e);
        }
    }
}
