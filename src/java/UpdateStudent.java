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
public class UpdateStudent extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            out.println("<style>\n" +
"            body { \n" +
"                width: 100%;\n" +
"                height:100%;\n" +
"                font-family: 'Open Sans', sans-serif;\n" +
"                background: #092756;\n" +
"                font-size: 18px;\n" +
"                text-align:center;\n" +
"                letter-spacing:1.2px;\n" +
"                background:linear-gradient(#4ca1af,#c4e0e5);\n" +
"                overflow:hidden;\n" +
"                color: white;\n" +
"            }\n" +
"            .login { \n" +
"                position: absolute;\n" +
"                top: 30%;\n" +
"                left: 50%;\n" +
"                margin: -150px 0 0 -150px;\n" +
"                width:400px;\n" +
"                height:400px;\n" +
"            }\n" +
"                    a:link, a:visited {"+
"                width:60%;"+
"                background-color: #0D865D;"+
"                color: white;"+
"                padding: 10;"+
"                text-align: center;"+
"                text-decoration: none;"+
"                display: inline-block;"+
"                border-radius: 6px;"+
"            }"+
"            input[type=submit]{ background-color: #4a77d4; }\n" +
"            .login h1 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }\n" +
"            input { \n" +
"                width: 100%; \n" +
"                margin-bottom: 10px; \n" +
"                background: rgba(0,0,0,0.3);\n" +
"                border: none;\n" +
"                outline: none;\n" +
"                padding: 10px;\n" +
"                font-size: 13px;\n" +
"                color: #fff;\n" +
"                /*text-shadow: 1px 1px 1px rgba(0,0,0,0.3);*/\n" +
"                border: 1px solid rgba(0,0,0,0.3);\n" +
"                border-radius: 4px;\n" +
"            }\n" +
"            ::placeholder{ color:white };\n" +
"        </style>");
            String seatno = request.getParameter("seatno");        
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineresult", "root", "");  
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from student where student_seatno='"+seatno+"'");  
            while(rs.next()){
                out.println("<div class='login'>");
                out.println("<h1>Update Student Details</h1>");
                out.print("<form action='UpdateStudent2' method='post'>");  
                out.print("<table align=center>");  
                out.print("<tr><td>Student Roll No:</td><td><input type='text' name='student_seatno' readonly value='"+rs.getString("student_seatno")+"'/></td></tr>");  
                out.print("<tr><td>Student Name:</td><td><input type='text' name='student_name' value='"+rs.getString("student_name")+"'/></td></tr>");  
                out.print("<tr><td>Subject 1 Marks :</td><td><input type='number' name='subject_1_marks' value='"+rs.getInt("subject_1_marks")+"'/></td></tr>");  
                out.print("<tr><td>Subject 2 Marks :</td><td><input type='number' name='subject_2_marks' value='"+rs.getInt("subject_2_marks")+"'/></td></tr>");  
                out.print("<tr><td>Subject 3 Marks :</td><td><input type='number' name='subject_3_marks' value='"+rs.getInt("subject_3_marks")+"'/></td></tr>");  
                out.print("<tr><td>Subject 4 Marks :</td><td><input type='number' name='subject_4_marks' value='"+rs.getInt("subject_4_marks")+"'/></td></tr>");  
                out.print("<tr><td>Subject 5 Marks :</td><td><input type='number' name='subject_5_marks' value='"+rs.getInt("subject_5_marks")+"'/></td></tr>");  
                out.print("<tr><td colspan='2'><input type='submit' value='Update'/></td></tr>");  
                out.print("</table>");  
                out.print("</form>");  
                out.print("<a href='AdminServlet'>Go Back</a>");  
                out.println("</div>");
            }        
        }
        catch (Exception e){
            out.println(e);
        }
    }
}
