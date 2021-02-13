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
public class ShowResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            String seatno=request.getParameter("student_seatno");
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/onlineresult";
            Connection con=DriverManager.getConnection(url, "root", "");
            Statement st = con.createStatement();
            PreparedStatement ps=con.prepareStatement("select * from student where student_seatno=?");
            ps.setString(1, seatno);
            ResultSet rs=ps.executeQuery();
            out.println("<style>html{text-align:center;font-family:arial;font-size: 20px;}");
            out.println("td {border: 1px solid #726E6D;padding: 15px;text-align:center;}");
            out.println("thead{font-weight:bold;text-align:center;background: #625D5D;color:white;}");
            out.println("table {border-collapse: collapse;width:70%;}");
            out.println(".footer {text-align:right;font-weight:bold;}");
            out.println("tbody >tr:nth-child(odd) {background:#D1D0CE;}</style>");                
            
            if(rs.isBeforeFirst()){
                while(rs.next()){
                out.println("<h1>Student Report Card</h1>");
                out.println("<table align=center><thead>");
                out.println("<tr><td colspan=20>College Name </td></tr>");
                out.println("<tr><td colspan=6>Rollno </td><td colspan=4> Class </td><td colspan=5> "+rs.getString("student_seatno")+" </td></tr>");
                out.println("<tr><td colspan=6>T17A01 </td><td colspan=4> BScIT </td><td colspan=5> "+rs.getString("student_name")+" </td></tr><br></thead>");
                out.println("<tbody><tr><td colspan=10>Subject Name</td><td colspan=4>Out Of</td><td colspan=6> Marks Gained </td></tr>");
                out.println("<tr><td colspan=10>Subject 1</td><td colspan=4> 100</td><td colspan=6> "+rs.getInt("subject_1_marks")+" </td></tr>");
                out.println("<tr><td colspan=10>Subject 2</td><td colspan=4> 100</td><td colspan=6> "+rs.getInt("subject_2_marks")+" </td></tr>");
                out.println("<tr><td colspan=10>Subject 3</td><td colspan=4> 100</td><td colspan=6> "+rs.getInt("subject_3_marks")+" </td></tr>");
                out.println("<tr><td colspan=10>Subject 4</td><td colspan=4> 100</td><td colspan=6> "+rs.getInt("subject_4_marks")+" </td></tr>");
                out.println("<tr><td colspan=10>Subject 5</td><td colspan=4> 100</td><td colspan=6> "+rs.getInt("subject_5_marks")+" </td></tr></tbody>");
                float total=rs.getInt("subject_1_marks")+rs.getInt("subject_2_marks")+rs.getInt("subject_3_marks")+rs.getInt("subject_4_marks")+rs.getInt("subject_5_marks");
                float percentage=total/500;
                
                out.println("<tfoot><tr><td colspan=14 class=footer>Total</td><td colspan=6>"+total+"</td></tr>");
                out.println("<tr><td colspan=14 class=footer>Percentage</td><td colspan=6>"+(percentage*100)+"%</td></tr>");
                if(rs.getInt("subject_1_marks")<40 && rs.getInt("subject_2_marks")<40 && rs.getInt("subject_3_marks")<40 && rs.getInt("subject_4_marks")<40 && rs.getInt("subject_5_marks")<40)
                {
                    out.println("<tr><td colspan=14 class=footer>Remark</td><td colspan=6>Failed</td></tr></tfoot></table>");
                    out.println("<p>You have failed because you have scored less than 40 marks in one or more subjects.</p>");
                }
                else{
                    out.println("<tr><td colspan=14 class=footer>Remark</td><td colspan=6>Pass</td></tr></tfoot></table>");
                }
                }
            }
            else{
                out.println("<table align=center style='margin-top:200px;'><thead><tr><td colspan=20>No Student With This Seat Number Exists in This Database</td></tr>");
                out.println("<tbody><tr><td colspan=20><a href='show_result.html' style='text-decoration:none;color:;'>Search For Another Seat No</a></td></tr></tbody></table>");
            }
        }
        catch(Exception e){
            out.println(e);
        }
    }
}