package webtest.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webtest.models.List;

/**
 * Servlet implementation class homepage
 */
@WebServlet(urlPatterns="/kanban", loadOnStartup = 1)
public class kanban extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public kanban() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		int id = 1;
		
		ArrayList<List> todos = new ArrayList<>();
		todos.add(new List(id++, "Apply for Internship", "Submit my application for an internship at WD."));
		todos.add(new List(id++, "Get industry experience", "Work in the industry to gain relevent experience."));
		
		ArrayList<List> progresses = new ArrayList<>();
		ArrayList<List> dones = new ArrayList<>();
		
		getServletContext().setAttribute("todos", todos);
		getServletContext().setAttribute("progresses", progresses);
		getServletContext().setAttribute("dones", dones);
		
		getServletContext().setAttribute("id", id);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		@SuppressWarnings("unchecked")
		ArrayList<List> todos = (ArrayList<List>) request.getServletContext().getAttribute("todos");

		@SuppressWarnings("unchecked")
		ArrayList<List> progresses = (ArrayList<List>) request.getServletContext().getAttribute("progresses");
		
		@SuppressWarnings("unchecked")
		ArrayList<List> dones = (ArrayList<List>) request.getServletContext().getAttribute("dones");
		
		out.println("<!DOCTYPE html>\n" + 
				"<html lang=\"en\">\n" + 
				"<head>\n" + 
				"    <meta charset=\"UTF-8\">\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
				"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" + 
				"    <title>Kanban</title>\n" + 
				"    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n" + 
				"	<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" + 
				"	<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>\n" + 
				"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>\n" + 
				"	<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.2/css/all.css\" integrity=\"sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr\" crossorigin=\"anonymous\">\n" + 
				"</head>\n" + 
				"<body >\n" + 
				"    <header class=\"jumbotron m-3\">\n" + 
				"        <h2>Kanban</h2>\n" + 
				"    </header>\n" + 
				"    <main class=\"m-3\">\n" + 
				"        <div class=\"row\">\n" + 
				"            <div class=\"col-12 col-md-4 my-2\">\n" + 
				"                <h3>To-Do</h3><hr>\n"); 
								 for (List todo: todos) {
									 out.println("<div class=\"card my-1\"> \n" + 
													"<div class=\"card-header bg-warning\" style='color:white'>\n" + 
													" " + todo.getTitle() 
														+ "<div class='float-right'>"
														+ "<form action='kanban' method='POST'>"
														+	"<input type='hidden' name='id' value='" + todo.getId() + "'/>"
														+ 	"<button class='btn btn-outline-light' style='border:0px solid white; "
																	+ "color:black' type='submit' name='type' value='upTodo'>"
																	+ "<i class=\"fas fa-caret-up\"></i></button>\n"
														+ 	"<button class='btn btn-outline-light' style='border:0px solid white;'"
																	+ "type='submit' name='type' value='downTodo' >"
																	+ "<i class=\"fas fa-caret-down\"></i></button>\n"
														+ "</form>"
														+ "</div>" 
													+
													"</div>\n" + 
													"<div class=\"card-body\">\n" + 
													" " + todo.getDesc() +
													"</div>\n" + 
													"<div class=\"card-footer\">\n" + 
													"	<div class=\"float-left\">\n"  
													+ 		"<form action ='kanban' method='POST'>"
													+			"<input type='hidden' name='type' value='deleteTodo'/>"
													+			"<input type='hidden' name='id' value='" + todo.getId() + "'/>"
													+ 			"<button class='btn btn-light' type='submit' style='color:black'><i class='fas fa-trash'></i></button>\n" 
													+ 		"</form>" +  
													"   </div>\n" + 
													"   <div class=\"float-right\">\n" + 
													"		<form action='kanban' method='POST'>"
													+			"<input type='hidden' name='type' value='progress'/>"
													+			"<input type='hidden' name='id' value='" + todo.getId() + "'/>"
													+ 			"<button class=\"btn btn-link\" type='submit' name='delete'>Move to In-Progress <i class=\"fas fa-angle-right\"></i></button>"
													+ "		</form>\n" + 
													"	</div>\n" + 
													"</div>\n" + 
												"</div>\n");
												
								 }
				
								
				out.println("</div>\n" +
				"            <div class=\"col-12 col-md-4 my-2\">\n" + 
				"                <h3>In-Progress</h3><hr>\n");
								 for (List progress: progresses) {
									 out.println("<div class=\"card my-1\"> \n" + 
													"<div class=\"card-header bg-primary\" style='color:white'>\n" + 
													" " + progress.getTitle() 
													+ "<div class='float-right'>"
													+ "<form action='kanban' method='POST'>"
													+	"<input type='hidden' name='id' value='" + progress.getId() + "'/>"
													+ 	"<button class='btn btn-outline-light' style='border:0px solid white; "
																+ "color:black' type='submit' name='type' value='upProgress'>"
																+ "<i class=\"fas fa-caret-up\"></i></button>\n"
													+ 	"<button class='btn btn-outline-light' style='border:0px solid white;'"
																+ "type='submit' name='type' value='downProgress' >"
																+ "<i class=\"fas fa-caret-down\"></i></button>\n"
													+ "</form>"
													+ "</div>"  +
													"</div>\n" + 
													"<div class=\"card-body\">\n" +  
													" " + progress.getDesc() +
													"</div>\n" + 
													"<div class=\"card-footer\">\n" + 
													"	<div class='float-left'>\n" 
													+ 		"<form action ='kanban' method='POST'>"
													+			"<input type='hidden' name='type' value='deleteProgress'/>"
													+			"<input type='hidden' name='id' value='" + progress.getId() + "'/>"
													+ 			"<button class='btn btn-light' type='submit'><i class='fas fa-trash'></i></button>\n" 
													+ 		"</form>" + 
 
													"   </div>\n" + 
													"   <div class=\"float-right\">\n" + 
													"		<form action='kanban' method='POST'>"
													+			"<input type='hidden' name='type' value='done'/>"
													+			"<input type='hidden' name='id' value='" + progress.getId() + "'/>"
													+ 			"<button class=\"btn btn-link\" type='submit'>Move to Done <i class=\"fas fa-angle-right\"></i></button>"
													+ "		</form>\n" + 
													"	</div>\n" +
													"</div>\n" + 
												"</div>\n");
												
								 }
				
				out.println("</div>\n" + 
				"            <div class=\"col-12 col-md-4 my-2\">\n" + 
				"                <h3>Done</h3><hr>\n"); 
								 for (List done: dones) {
									 out.println("<div class=\"card my-1\"> \n" + 
													"<div class=\"card-header bg-success\" style='color:white'>\n" + 
													" " + done.getTitle() +
													"</div>\n" + 
													"<div class=\"card-body\">\n" + 
													" " + done.getDesc() + 
													"</div>\n" + 
												"</div>\n");
												
								 }
				out.println("</div>\n" + 
				"        <div>\n" + 
				"        \n" + 
				"    </main>\n" + 
				"    \n" + 
				"    <article class=\"m-3\">\n" + 
				"        <hr>\n" + 
				"        <div class=\"row\">\n" + 
				"            <div class=\"col-12\" align=\"center\"> <h2>Add a New Card</h2></div>\n" + 
				"        </div>\n" + 
				"\n" + 
				"        <div class=\"row\">\n" + 
				"            <div class=\"col-12\">\n" + 
				"                <form action='kanban' method='POST'>\n" + 
				"                    <div class=\"form-group\">\n" + 
				"                        <label for=\"title\">Title</label>\n" + 
				"                        <input type=\"text\" class=\"form-control\" id=\"title\" name='title' aria-describedby=\"title\" placeholder=\"Enter a Title\">\n" + 
				"                    </div>\n" + 
				"                    <div class=\"form-group\">\n" + 
				"                        <label for=\"desc\">Description</label>\n" + 
				"                        <textarea class=\"form-control\" id=\"description\" rows=\"3\" name='desc' placeholder=\"Enter a description\"></textarea>\n" + 
				"                    </div>\n" + 
				"                    <input type=\"submit\" class=\"btn btn-primary\" value=\"Add Card\"></input>\n" + 
				"                    </form>\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"\n" + 
				"       \n" + 
				"\n" + 
				"    </article>\n" + 
				"    <footer class=\"m-3\">\n" + 
				"        <hr>\n" + 
				"        <div class=\"row\">\n" + 
				"            <div class=\"col-12\">\n" + 
				"                &copy; CSULA 2019. All Rights Reserved.\n" + 
				"            </div>\n" + 
				"        </div>\n" + 
				"    </footer>\n" + 
				"</body>\n" + 
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		ArrayList<List> todos = (ArrayList<List>) request.getServletContext().getAttribute("todos");
		
		@SuppressWarnings("unchecked")
		ArrayList<List> progresses = (ArrayList<List>) request.getServletContext().getAttribute("progresses");
		
		@SuppressWarnings("unchecked")
		ArrayList<List> dones = (ArrayList<List>) request.getServletContext().getAttribute("dones");
		
		
		if (request.getParameter("type") != null && !request.getParameter("type").trim().equals(""))
		{
			if (request.getParameter("type").equals("progress")) {
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					progresses.add(todos.get(List.getIndex(todos, id)));
					todos.remove(List.getIndex(todos, id));
				}
			}
			else  if (request.getParameter("type").equals("done")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					dones.add(progresses.get(List.getIndex(progresses, id)));
					progresses.remove(List.getIndex(progresses, id));
				}
			}
			
			else  if (request.getParameter("type").equals("deleteTodo")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					todos.remove(List.getIndex(todos, id));
					
				}
			}
			
			else  if (request.getParameter("type").equals("deleteProgress")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					progresses.remove(List.getIndex(progresses, id));
				}
			}
			
			else  if (request.getParameter("type").equals("upTodo")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					if (List.getIndex(todos, id) > 0) {
						int index = List.getIndex(todos, id);
						List temp = todos.get(index - 1);
						todos.set(index -1 , todos.get(index));
						todos.set(index, temp);
					}
				}
			}
			
			else  if (request.getParameter("type").equals("downTodo")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					if (List.getIndex(todos, id) < todos.size() - 1) {
						int index = List.getIndex(todos, id);
						List temp = todos.get(index);
						todos.set(index, todos.get(index + 1));
						todos.set(index + 1, temp);
					}
				}
			}
			
			else  if (request.getParameter("type").equals("upProgress")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					if (List.getIndex(progresses, id) > 0) {
						int index = List.getIndex(progresses, id);
						List temp = progresses.get(index - 1);
						progresses.set(index -1 , progresses.get(index));
						progresses.set(index, temp);
					}
				}
			}
			
			else  if (request.getParameter("type").equals("downProgress")){
				if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
					int id = Integer.parseInt(request.getParameter("id"));
					if (List.getIndex(progresses, id) < progresses.size() - 1) {
						int index = List.getIndex(progresses, id);
						List temp = progresses.get(index);
						progresses.set(index, progresses.get(index + 1));
						progresses.set(index + 1, temp);
					}
				}
			}
		}
		else {
			if (request.getParameter("title") != null && !request.getParameter("title").trim().equals("")
					&& request.getParameter("desc") != null && !request.getParameter("desc").trim().equals("") )
			{
				int id = (int) request.getServletContext().getAttribute("id");
				String title = request.getParameter("title");
				String desc = request.getParameter("desc");
				todos.add(new List(id++, title, desc));
				getServletContext().setAttribute("id", id);
			}
		}
		
		doGet(request, response);
	}

}
