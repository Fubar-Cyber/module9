package CenModule9;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Servlet implementation class ToDoListServlet
 */
@WebServlet("/ToDoListServlet")
public class ToDoListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ArrayList<String> items;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToDoListServlet() {
        super();
        items = new ArrayList<>();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");

        if (command != null && !command.isEmpty()) {
            if (command.equalsIgnoreCase("add")) {
                String item = request.getParameter("item");
                addItem(item);
            } else if (command.equalsIgnoreCase("delete")) {
                int index = Integer.parseInt(request.getParameter("index"));
                deleteItem(index - 1);
            }
        }

        // Set the items list as a request attribute
        request.setAttribute("items", items);

        // Forward the request to the index.html file
        request.getRequestDispatcher("index.html").forward(request, response);
    }

    /**
     * Adds a to-do item to the list.
     *
     * @param item The to-do item to be added.
     */
    public void addItem(String item) {
        items.add(item);
    }

    /**
     * Deletes a to-do item from the list based on its index number.
     *
     * @param index The index of the item to be deleted.
     */
    public void deleteItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
        }
    }
}
