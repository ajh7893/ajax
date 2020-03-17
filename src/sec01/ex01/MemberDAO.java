package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private ResultSet rs;

	public MemberDAO() {
		try {
			
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			System.out.println("connection is success");
		
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("connection is fail");
			
		}
	}

	public boolean overlappedID(String id){
		boolean result = false;
		try {
			con = dataFactory.getConnection();
			String query = "select decode(count(*),1,'true','false') as result from t_member where id=?";
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result =Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public MemberVO searchID(String _id) {
		//initialize
		MemberVO memInfo = null;
		System.out.println("excute search");
		try {
			con = dataFactory.getConnection();
			String sql= "select * from t_member where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, _id);
			System.out.println("sql: "+sql+"  :"+_id);
			rs = pstmt.executeQuery();
			System.out.println("왜?");
//			if(rs!=null) {
//				System.out.println("why" );
//			}//rs의결과가 있다면 true
			if(rs.next()) {
				
				System.out.println("running search");
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate =rs.getDate("joinDate");
				
				memInfo = new MemberVO(id,pwd,name,email,joinDate);
				
			}else {
				memInfo = null;
				System.out.println("null");
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("running to search is fail");
		}
		return memInfo;
	}
	public String add(String _id, String _pwd, String _name ) {
		String result = "failed";
		try {
			con = dataFactory.getConnection();
			String sql = "merge into t_member t using(select ? as id, ? as pw, ? as name from dual) n on (t.id = n.id)" + 
					" when matched then" + 
					" update set pwd = n.pw, name = n.name" + 
					" when not matched then " + 
					"insert(id, pwd, name) values (n.id, n.pw, n.name)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, _id);
			pstmt.setString(2, _pwd);
			pstmt.setString(3, _name);
			System.out.println("sql: "+ sql);
			rs = pstmt.executeQuery();
			System.out.println("addd???????");
			if(rs.next()) {
				System.out.println("add--");
				result="success";
			System.out.println("wow!!!");
			}else {
				result = "failed";
				System.out.println("woooooooo");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("add 실패");
		}
		return result;
		
	}
	
}
