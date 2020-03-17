package sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;
	
	public void init() {
		memberDAO = new MemberDAO();
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//path
		String action = request.getPathInfo();
		System.out.println("action: "+action);
		//this is from ajax in jsp
		
		
		//
		PrintWriter writer = response.getWriter();
		
		//action
		if(action==null||action.equals("/search")) {
			String id = (String)request.getParameter("id");
			MemberDAO memberDAO = new MemberDAO();
			MemberVO memberVO = memberDAO.searchID(id); 
			JSONObject allObject = new JSONObject();
			//if memberVO has datas..
			if(memberVO != null) {
				//access to memberVO and getId
				String memId = memberVO.getId();
				String memPwd = memberVO.getPwd();
				String memName = memberVO.getName();
				String memEmail = memberVO.getEmail();
				SimpleDateFormat transFormat = new SimpleDateFormat("yy-MM-dd");
				String memJoinDate = transFormat.format(memberVO.getJoinDate());
				
				//put into json
				allObject.put("id", memId);
				allObject.put("pwd", memPwd);
				allObject.put("name", memName);
				allObject.put("email", memEmail);
				allObject.put("joinDate", memJoinDate);
				
				String jsonInfo = allObject.toJSONString();
				
				//to send data to ajax in jsp
				writer.print(jsonInfo);
			}else {//if memberVO has no datas,.
				writer.print("no_result");
			}
		}else if(action.contentEquals("/add")) {
			String result = "";
			String id = (String)request.getParameter("id");
			String pwd = (String)request.getParameter("pwd");
			String name =(String)request.getParameter("name");
			MemberDAO memberDAO = new MemberDAO();
			result = memberDAO.add(id,pwd,name);
			writer.print(result);
		}
		
	}

}
