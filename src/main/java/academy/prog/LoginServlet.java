package academy.prog;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	static final String TEMPLATE = "<html>" +
			"<head><title>Prog.kiev.ua</title></head>" +
			"<body><h1>%s</h1></body></html>";
	
	static final Map<String, String> cred = new HashMap<String, String>();
	
	static {
		// hardcode login credentials
		cred.put("user", "qwerty");
		cred.put("admin", "qazwsx");
	}

	/*

	POST /login HTTP/1.1
	....
	login=111&pass=1111

	 */

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String login = req.getParameter("login");
		String pass = req.getParameter("password");
		String testAge = req.getParameter("age");
		String msg;
		
		String temp = cred.get(login);

		Boolean passLength= false;
		if (pass.length()<4 || pass.length()>18){
			passLength = true;
		}

		int age = Integer.parseInt(testAge);
		if (pass.equals(temp)&&age>=18&&passLength==false)
			msg = "Success";
		else if(age<18){
			msg = "You are too young";
		}
		else if (passLength==true){

			msg = "Password must be more than 3 up to 18 digits length!  ";
		}

		else
			msg = "Denied";
		
        resp.getWriter().println(String.format(TEMPLATE, msg));
	}
}
