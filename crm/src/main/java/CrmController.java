import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"/"})
public class CrmController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/editUser":
                    this.editUserForm(request, response);
                    break;
                case "/AllCompletedCall":
                    this.showAllCompletedCall(request, response);
                    break;
                case "/AllCall":
                    this.showAllCall(request, response);
                    break;
                case "/deleteCall":
                    this.deleteCall(request, response);
                    break;
                case "/updateChangeCrm":
                    this.updateChangeCrm(request, response);
                    break;
                case "/changeCrm":
                    this.showCrmChangeForm(request, response);
                    break;
                case "/noCrmNeeded":
                    this.confirmationForm(request, response);
                    break;
                case "/UpdateUser":
                    this.updateUser(request, response);
                    break;
                case "/cancelCrm":
                    this.cancelCrm(request, response);
                    break;
                case "/userHome":
                    this.showIssueForUser(request, response);
                    break;
                case "/AllCrm":
                    this.showAllCrm(request, response);
                    break;
                case "/showAllIssuesResolved":
                    this.showAllIssuesResolved(request, response);
                    break;
                case "/admin":
                    this.showAllIssuesForAdmin(request, response);
                    break;
                case "/AllUsers":
                    this.showAllUsers(request, response);
                    break;
                case "/editIssuesforUser":
                    this.editIssuesForUser(request, response);
                    break;
                case "/updateIssue":
                    this.updateIssue(request, response);
                    break;
                case "/updateCall":
                    this.updateCall(request, response);
                    break;
                case "/editCall":
                    this.editCallForm(request, response);
                    break;
                case "/showCall":
                    this.showCallList(request, response);
                    break;
                case "/showIssuesResolved":
                    this.showResolvedIssuesToCrm(request, response);
                    break;
                case "/crmUsers":
                    this.crmUsers(request, response);
                    break;
                case "/crmList":
                    this.showCrmList(request, response);
                    break;
                case "/showCompletedCallForUser":
                    this.showCompletedCallForUser(request, response);
                    break;
                case "/loginAuth":
                    this.loginAuth(request, response);
                    break;
                case "/saveCall":
                    this.saveCall(request, response);
                    break;
                case "/saveIssue":
                    this.saveIssue(request, response);
                    break;
                case "/SaveCrm":
                    this.saveCrm(request, response);
                    break;
                case "/SaveUser":
                    this.saveUser(request, response);
                    break;
                case "/deleteIssues":
                    this.deleteIssue(request, response);
                    break;
                case "/edit":
                    this.showEditForm(request, response);
                    break;
                case "/editIssueForUser":
                    this.updateIssueForUser(request, response);
                    break;
                case "/registerCrm":
                    this.registerCrm(request, response);
                    break;
                case "/registerUser":
                    this.registerUser(request, response);
                    break;
                case "/registerIssues":
                    this.registerIssues(request, response);
                    break;
                case "/registerCall":
                    this.registerCall(request, response);
                    break;
                case "/logout":
                    this.logout(request, response);
                    break;

            }

        } catch (SQLException var6) {
            throw new ServletException(var6);
        }
    }

    private void editUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", DaoCrm.selectUser(Integer.parseInt(request.getParameter("id"))));
        RequestDispatcher dispatcher = request.getRequestDispatcher("update-user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String mobileNo = request.getParameter("mobileNo");
        int id = Integer.parseInt(request.getParameter("id"));
        User user = DaoCrm.selectUser(id);
        user.setMobileNo(mobileNo);
        user.setEmail(email);
        user.setName(name);
        DaoCrm.updateUser(user);
        HttpSession session=request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        if(login.getOwner().equals("admin"))
        showAllUsers(request, response);
        else if (login.getOwner().equals("CRM")) {
            crmUsers(request,response);
        } else if (login.getOwner().equals("user")) {
            showIssueForUser(request, response);
        }
    }

    private void showAllCompletedCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CompletedCall> completedCallList = DaoCrm.selectAllCompletedCall();
        request.setAttribute("callList", completedCallList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("all-completed-call.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = DaoCrm.selectAllCall();
        request.setAttribute("callList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-call-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCompletedCallForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        List<CompletedCall> completedCallList = DaoCrm.selectCompletedCall(login.getId());
        request.setAttribute("completedCallList", completedCallList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("completed-call-of-crm.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = DaoCrm.selectUser(userId);
        DaoCrm.deleteCall(user);
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        if (login.getOwner().equals("CRM"))
            showCallList(request, response);
        else
            showAllCall(request, response);
    }

    private void updateChangeCrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int crmId = Integer.parseInt(request.getParameter("crmId"));
        Crm crm = DaoCrm.selectCrm(crmId);
        User user = DaoCrm.selectUser(userId);
        user.setCrm(crm);
        DaoCrm.updateUser(user);
        showAllUsers(request, response);
    }

    private void showCrmChangeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = DaoCrm.selectUser(id);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("change-crm-form.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmationForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
        dispatcher.forward(request, response);
    }

    private void cancelCrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        DaoCrm.cancelCrm(login.getId());
        showIssueForUser(request, response);
    }

    private void showAllCrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Crm> crmList = DaoCrm.allCrm();
        request.setAttribute("crmList", crmList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("all-crm.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllIssuesResolved(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<IssuesResolved> issuesResolvedList = DaoCrm.allResolvedIssues();
        request.setAttribute("issuesResolvedList", issuesResolvedList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("all-resolved-issues.jsp");
        dispatcher.forward(request, response);
    }

    private void showAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = DaoCrm.selectAllUser();
        request.setAttribute("userList", userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("all-user.jsp");
        dispatcher.forward(request, response);
    }

    private void updateIssueForUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Issues issue = DaoCrm.selectIssue(id);
        issue.setDescription(request.getParameter("description"));
        DaoCrm.updateIssue(issue);
        showIssueForUser(request, response);
    }

    private void showIssueForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        List<Issues> listTodo = DaoCrm.selectIssuesForUser(login.getId());
        request.setAttribute("user",DaoCrm.selectUser(login.getId()) );
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("issue-list.jsp");
        dispatcher.forward(request, response);
    }

    private void editIssuesForUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Issues issue = DaoCrm.selectIssue(id);
        request.setAttribute("issue", issue);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-issue-for-user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showResolvedIssuesToCrm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        List<IssuesResolved> issuesResolvedList = DaoCrm.resolvedIssuesToCrmList(login.getId());
        request.setAttribute("issuesResolvedList", issuesResolvedList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crm-list-resolved-issues.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String time = request.getParameter("time");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        int id = Integer.parseInt(request.getParameter("id"));
        Calls call = new Calls();
        call.setId(id);
        call.setDate(date);
        call.setTime(time);
        DaoCrm.updateCall(call);
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        if (login.getOwner().equals("CRM"))
            showCallList(request, response);
        else
            showAllCall(request, response);
    }

    private void editCallForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Calls call = DaoCrm.selectCallById(id);
        System.out.println(call.getId());
        request.setAttribute("Calls", call);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editCallForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showCallList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        List<User> listTodo = DaoCrm.selectCall(login.getId());
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("call-list.jsp");
        dispatcher.forward(request, response);
    }

    private void registerCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerCallForm.jsp");
        dispatcher.forward(request, response);
    }

    private void saveCall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String time = request.getParameter("time");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = DaoCrm.selectUser(userId);
        Calls call = new Calls();
        call.setDate(date);
        call.setTime(time);
        call.setUser(user);
        DaoCrm.createCall(call, user);
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        if (login.getOwner().equals("CRM"))
            showCallList(request, response);
        else
            showAllCall(request, response);
    }

    private void showCrmList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login3 = (Login) session.getAttribute("login");
        List<Issues> listTodo = DaoCrm.selectUserForCrm(login3.getId());
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crm-list.jsp");
        dispatcher.forward(request, response);
    }

    private void crmUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        List<User> listTodo = DaoCrm.selectOnlyUserFromCrm(login.getId());
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("crm-user.jsp");
        dispatcher.forward(request, response);
    }

    private void loginAuth(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Login> list = DaoCrm.loginCheck(request, response);
        if (list.get(0).getOwner().equals("admin")) {
            List<Issues> listTodo = DaoCrm.selectAllIssues();
            request.setAttribute("listTodo", listTodo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
            dispatcher.forward(request, response);
        } else if (list.get(0).getOwner().equals("CRM")) {
            List<Issues> listTodo = DaoCrm.selectUserForCrm(list.get(0).getId());
            request.setAttribute("listTodo", listTodo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("crm-list.jsp");
            dispatcher.forward(request, response);
        } else if (list.get(0).getOwner().equals("user")) {
            List<Issues> listTodo = DaoCrm.selectIssuesForUser(list.get(0).getId());
            request.setAttribute("user",DaoCrm.selectUser(list.get(0).getId()) );
            request.setAttribute("listTodo", listTodo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("issue-list.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    private void registerCrm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerCrmForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registerUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("registerUserForm.jsp");
        dispatcher.forward(request, response);
    }

    private void registerIssues(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerIssueForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Issues existingUser = DaoCrm.selectIssue(id);
        request.setAttribute("issue", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editIssuesForm.jsp");
        dispatcher.forward(request, response);
    }

    private void saveCrm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        Login login1 = new Login();
        login1.setEmail(name);
        login1.setPassword(password);
        login1.setOwner("CRM");
        Crm crm = new Crm();
        crm.setName(name);
        Login login2 = DaoCrm.insertLogin(login1);
        crm.setId(login2.getId());
        DaoCrm.createCrm(crm);
        showAllIssuesForAdmin(request, response);
    }

    private void showAllIssuesForAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Issues> listTodo = DaoCrm.selectAllIssues();
        request.setAttribute("listTodo", listTodo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void saveIssue(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        System.out.println("save Issue");
        String description = request.getParameter("description");
        String status = "Yet To Start";
        HttpSession session = request.getSession(false);
        Issues issues = new Issues();
        issues.setDescription(description);
        issues.setStatus(status);
        issues.setTargetDate(LocalDate.now().plusDays(12));
        issues.setCurrentDate(LocalDate.now());
        Login login1 = (Login) session.getAttribute("login");
        User user = DaoCrm.selectUserById(login1.getId());
        issues.setUser(user);
        DaoCrm.insertIssue(issues, request);
        showIssueForUser(request, response);
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        String mobileNo = request.getParameter("mobileNo");
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        int crmId = login.getId();
        Login login1 = new Login();
        login1.setEmail(email);
        login1.setPassword(password);
        login1.setOwner("user");
        User user = new User();
        user.setMobileNo(mobileNo);
        user.setName(name);
        user.setEmail(email);
        Login login2 = DaoCrm.insertLogin(login1);
        user.setId(login2.getId());
        DaoCrm.insertUser(user, request, crmId);
        if (login.getOwner().equals("CRM"))
            showCrmList(request, response);
        else
            showAllIssuesForAdmin(request, response);
    }

    private void updateIssue(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        LocalDate date = LocalDate.parse(request.getParameter("date"));
        String status = request.getParameter("isDone");
        Issues issues = DaoCrm.selectIssue(id);
        issues.setDescription(description);
        issues.setTargetDate(date);
        issues.setStatus(status);
        if (status.equals("Complete")) {
            DaoCrm.issueSolved(issues);
        } else {
            DaoCrm.updateIssue(issues);
        }
        HttpSession session = request.getSession(false);
        Login login = (Login) session.getAttribute("login");
        if (login.getOwner().equals("CRM"))
            showCrmList(request, response);
        else
            showAllIssuesForAdmin(request, response);
    }

    private void deleteIssue(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        DaoCrm.deleteIssue(id);
        showIssueForUser(request, response);
    }
}