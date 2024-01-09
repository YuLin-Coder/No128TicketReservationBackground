package cn.edu.csmzxy.s1832.n05.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import cn.edu.csmzxy.s1832.n05.javabean.db_conn;//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷匡拷锟斤拷
import cn.edu.csmzxy.s1832.n05.javabean.get_md5;//锟斤拷锟斤拷MD5锟斤拷希锟斤拷锟斤拷锟洁（锟斤拷锟斤拷为锟皆硷拷写锟侥ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷械锟斤拷啵�
public class check_login_reg extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("锟矫筹拷锟津不斤拷锟斤拷直锟接凤拷锟绞ｏ拷锟诫不要锟斤拷锟皆非凤拷锟斤拷锟斤拷");
		resp.setHeader("refresh", "2;url=index/login_reg.jsp");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		String log_name = req.getParameter("log_name");
		String log_pwd = req.getParameter("log_pwd");
		String reg_name = req.getParameter("reg_name");
		String reg_pwd1 = req.getParameter("reg_pwd1");
		String reg_pwd2 = req.getParameter("reg_pwd2");
		
		if(log_name!=null&&log_pwd!=null&&reg_name==null&&reg_pwd1==null&&reg_pwd2==null) {
			//锟斤拷锟矫碉拷录锟斤拷锟斤拷锟斤拷锟斤拷锟铰�
			go_login(log_name, log_pwd, req, resp);
			
		}
		else if(log_name==null&&log_pwd==null&&reg_name!=null&&reg_pwd1!=null&&reg_pwd2!=null&&reg_pwd1.equals(reg_pwd2)) {
			//锟斤拷锟斤拷注锟结方锟斤拷锟斤拷锟斤拷注锟斤拷
			go_reg(reg_name, reg_pwd1, req, resp);
			
		}//为什么锟斤拷锟斤拷写一锟斤拷锟叫讹拷注锟斤拷时reg_pwd1锟斤拷reg_pwd2锟角凤拷锟斤拷鹊锟斤拷锟斤拷锟截ｏ拷锟斤拷为锟斤拷前锟斤拷页锟斤拷锟斤拷锟窖撅拷锟斤拷js去锟斤拷锟叫讹拷锟剿ｏ拷
		//锟斤拷锟斤拷没锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟诫不一锟铰革拷锟斤拷锟斤拷锟斤拷锟斤拷锟结交锟斤拷锟捷碉拷锟斤拷servlet锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷js锟斤拷锟斤拷锟睫革拷锟斤拷js锟斤拷也锟斤拷锟斤拷锟斤拷锟剿非凤拷锟斤拷锟斤拷
		//锟斤拷耍锟街憋拷锟斤拷锟斤拷锟斤拷示锟角凤拷锟斤拷锟斤拷锟斤拷锟斤拷示锟斤拷息锟斤拷锟斤拷
		else {
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("锟诫不要锟斤拷锟皆非凤拷锟斤拷锟斤拷");
			/*****锟斤拷锟斤拷锟斤拷锟斤拷
			out.println("锟斤拷录锟剿号ｏ拷"+log_name+"\n锟斤拷录锟斤拷锟诫："+log_pwd+"\n注锟斤拷锟剿号ｏ拷"
			+reg_name+"\n注锟斤拷锟斤拷锟斤拷1锟斤拷"+reg_pwd1+"\n注锟斤拷锟斤拷锟斤拷2锟斤拷"+reg_pwd2);
			out.print("---log_name==null:"+(log_name==null));
			out.print("---log_pwd==null:"+(log_pwd==null));
			out.print("---reg_name==null:"+(reg_name==null));
			out.print("---reg_pwd1==null:"+(reg_pwd1==null));
			out.print("---reg_pwd2==null:"+(reg_pwd2==null));
			*/
					
			resp.setHeader("refresh", "2;url=index/login_reg.jsp");
			//锟斤拷锟剿碉拷录锟斤拷注锟斤拷锟斤拷锟斤拷锟斤拷峤伙拷锟斤拷锟斤拷锟斤拷菥锟轿拷欠锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟绞撅拷欠锟斤拷锟较�
		}
	}
	
	protected void go_login(String name, String pwd, HttpServletRequest req, HttpServletResponse resp) {
		//System.out.println("go_login锟斤拷锟斤拷锟斤拷锟斤拷");
		db_conn conn = new db_conn();//锟斤拷锟斤拷锟斤拷锟捷匡拷锟斤拷锟接讹拷锟斤拷
		get_md5 MD5 = new get_md5();
		pwd = MD5.getMD5(pwd);
		pwd = MD5.getMD5(pwd);
		//取锟斤拷cookie锟斤拷锟斤拷证锟角凤拷锟斤拷url锟斤拷锟斤拷锟斤拷锟絬rl锟斤拷锟斤拷锟斤拷转锟斤拷url锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟阶拷锟絠ndex.jsp
		//目锟斤拷锟斤拷锟斤拷转锟斤拷锟矫伙拷锟斤拷锟斤拷锟皆筹拷妫拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟�
		HttpSession session = req.getSession(); 
		/*
		 * 锟斤拷锟斤拷cookie锟斤拷转锟斤拷使锟斤拷session锟斤拷锟斤拷锟铰硷拷锟揭筹拷锟斤拷锟阶拷锟斤拷锟斤拷猓琧ookie貌锟斤拷锟睫凤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
		 * Cookie c = null; Cookie[] cookies = req.getCookies(); for(int
		 * i=0;i<cookies.length;i++) { System.out.println("锟叫讹拷cookie锟斤拷锟斤拷锟斤拷锟斤拷");
		 * if(cookies[i].getName().equals("url")) { c = cookies[i];
		 * System.out.println("url_cookie锟斤拷取锟斤拷"); } }
		 */
		String sql = "select * from common_user where user_name = '"+name+"'";
		ResultSet res = conn.executeQuery(sql);
		try {
			
			if(res.next()) {
				String user_pwd = res.getString(2);
				
				if(pwd.equals(user_pwd)) {
					//System.out.println("锟斤拷录锟斤拷证锟缴癸拷");
					session.setAttribute("user_id", name);
					
					if(session.getAttribute("url")!=null) {
						String url = session.getAttribute("url").toString();
						try{
							resp.sendRedirect(url);
							}
						catch (IOException e) {
							System.out.println("锟斤拷锟斤拷锟斤拷息锟斤拷锟铰ｏ拷"+e);
						}
					}else {
						try {
							resp.sendRedirect("user_center");
						}
						catch (IOException e) {
							System.out.println("锟斤拷锟斤拷锟斤拷息锟斤拷锟铰ｏ拷"+e);
						}
						
					}
				}else {
					//锟矫伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟�
					try {
						//System.out.println("锟矫伙拷锟斤拷锟斤拷锟斤拷锟�");
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("锟剿号伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷碌锟铰�");
						resp.setHeader("refresh", "2;url=index/login_reg.jsp");
					}catch (IOException e) {
						System.out.println("锟斤拷锟斤拷锟斤拷息锟斤拷锟铰ｏ拷"+e);
					}
					
				}				
			}else {
				//锟矫伙拷锟剿号达拷锟斤拷
				try {
					//System.out.println("锟矫伙拷锟剿伙拷锟斤拷锟斤拷");
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("锟剿号伙拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷碌锟铰�");
					resp.setHeader("refresh", "2;url=index/login_reg.jsp");
				}catch (IOException e) {
					System.out.println("锟斤拷锟斤拷锟斤拷息锟斤拷锟铰ｏ拷"+e);
				}
			}
			conn.closeDB();
		}
		catch (SQLException e) {
			System.out.println("锟斤拷锟斤拷锟斤拷息锟斤拷锟铰ｏ拷"+e);
		}
	}
	protected void go_reg(String name, String pwd1,HttpServletRequest req, HttpServletResponse resp) {
		//System.out.println("注锟结方锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷");
		resp.setContentType("text/html;charset=utf-8");
		
		
		db_conn conn = new db_conn();//锟斤拷锟斤拷锟斤拷锟捷匡拷锟斤拷锟接讹拷锟斤拷
		get_md5 MD5 = new get_md5();
		pwd1 = MD5.getMD5(pwd1);
		pwd1 = MD5.getMD5(pwd1);
		String sql = "select * from common_user where user_name = '"+name+"'";
		ResultSet res = conn.executeQuery(sql);
		try {
			PrintWriter out = resp.getWriter();
			try {
				if(res.next()) {			
					out.println("锟斤拷锟矫伙拷锟斤拷锟窖憋拷占锟矫ｏ拷锟斤拷使锟斤拷锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟斤拷注锟斤拷");
					resp.setHeader("refresh", "2;url=index/login_reg.jsp");			
				}else {
					sql = "insert into common_user (user_name,user_pwd) values('"+name+"','"+pwd1+"')";
					int in_res = conn.executeInsert(sql);
					//System.out.println(in_res);
					if(in_res==1) {
						out.println("锟斤拷喜锟斤拷注锟斤拷晒锟斤拷锟�2锟斤拷之锟斤拷锟斤拷转锟斤拷锟斤拷录页锟斤拷");
						resp.setHeader("refresh", "2;url=index/login_reg.jsp");	
					}else {
						out.println("锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷系锟斤拷锟斤拷锟斤拷员锟斤拷锟斤拷锟睫诧拷bug");
						resp.setHeader("refresh", "2;url=index/login_reg.jsp");
					}
				}
			}catch (Exception e) {
				System.out.print("锟斤拷锟斤拷锟斤拷息锟斤拷锟铰ｏ拷"+e);
			}
		}catch (IOException e) {
			System.out.println("锟斤拷锟斤拷锟斤拷"+e);
		}
		
		conn.closeDB();
	}
}
