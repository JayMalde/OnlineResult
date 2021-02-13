import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class AdminServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            HttpSession session=request.getSession(false);
            if(session!=null){
                String name = (String)session.getAttribute("name");
                out.print("<script>function confirmDelete(){var agree = confirm(\"Are you sure you want to delete the student?\");if(agree == true){return true;}else{return false;}}</script>");
                out.println("<style>body{margin: 0;}th{font-size: 30px;}td{font-size: 24px;}");
                out.println(".topnav {overflow: hidden;background-color: #696969;}");
                out.println(".topnav .nav-item {float: left;color: #f2f2f2;text-align: center;padding: 18px 24px;text-decoration: none;font-size: 20px;}");
                out.println(".button {color: white;text-align: center;text-decoration: none;display: inline-block;border-radius: 5px;}");
                out.println(".topnav .nav-item:hover {background-color: #A9A9A9;color: black;}");
                out.println("</style>");
                
                out.println("<div class=topnav>");
                out.println("<a class=nav-item href=AdminServlet>Admin</a>");
                out.println("<a class=nav-item href=add_student.html>Add Student</a>");
                out.println("<a class=nav-item href=#>Hello, "+name+"</a>");
                out.println("<a style='float:right;' class=nav-item href=LogoutServlet>Logout</a>");
                out.println("</div>");
                try 
                {  
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineresult", "root", "");  
                    Statement stmt = con.createStatement();  
                    ResultSet rs = stmt.executeQuery("select * from student");  
                    out.print("<table border='1' width='96%' style='margin-top:20px;margin-left:2%;'>");
                    out.print("<tr><th colspan=11 style=background-color:purple;color:white;font-size:36px;>Showing All The Students</th></tr>");
                    out.print("<tr><th>Seat No</th><th>Name</th><th>Subject 1</th><th>Subject 2</th><th>Subject 3</th><th>Subject 4</th><th>Subject 5</th><th>Total</th><th>Percentage</th><th>Update</th><th>Delete</th></tr>");
                    while (rs.next()) 
                    {  
                        String student_seatno = rs.getString("student_seatno");  
                        String student_name = rs.getString("student_name");  
                        int subject_1_marks = rs.getInt("subject_1_marks");  
                        int subject_2_marks = rs.getInt("subject_2_marks");  
                        int subject_3_marks = rs.getInt("subject_3_marks");  
                        int subject_4_marks = rs.getInt("subject_4_marks");  
                        int subject_5_marks = rs.getInt("subject_5_marks");     
                        out.println("<tr><td>" + student_seatno + "</td><td>" + student_name + "</td><td>" + subject_1_marks +"</td><td>" + subject_2_marks +"</td><td>" + subject_3_marks +"</td><td>" + subject_4_marks +"</td><td>" + subject_5_marks +"</td>");
                        float total=subject_1_marks+subject_2_marks+subject_3_marks+subject_4_marks+subject_5_marks;
                        float percentage=total/500;
                        out.print("<td>"+total+"</td><td>"+percentage*100+"%</td>");     
                        out.print("<td><a class='button' style='background-color:#FFBF00;padding:8px 12px;' href='UpdateStudent?seatno="+ student_seatno + "'>Edit Student</a></td>");
                        out.print("<td><a class='button' style='background-color:#B90E0A;padding:8px 12px;' onClick = 'return confirmDelete();' href='DeleteStudent?seatno="+ student_seatno + "'>Delete Student</a></td></tr>");
                    }  
                    out.println("</table>"); 
                 }  
                catch (Exception e) 
                {  
                    out.println(e);
                }
            }
            else{
                response.sendRedirect("login.html");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}