import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Queue;

public class DaoCrm {
    static Configuration configuration = new Configuration().configure();
    static SessionFactory sessionFactory = configuration.buildSessionFactory();
    public static void insertIssue(Issues issues,HttpServletRequest request) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            HttpSession httpSession = request.getSession(false);
            transaction = session.beginTransaction();
            session.save(issues);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static Login insertLogin(Login login) throws SQLException {
        Transaction transaction = null;
        Login login1= null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(login);
            // commit transaction
            transaction.commit();
            login1 = (Login) session.createQuery("from Login where email ='"+login.getEmail()+"'").list().get(0);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return login1;
    }
    public static void insertUser(User user,HttpServletRequest request,int crmId) throws SQLException {
        HttpSession httpSession = request.getSession(false);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the User object
            Login login = (Login) httpSession.getAttribute("login");
            Crm crm=null;
            if(login.getOwner().equals("CRM")){
                crm = session.get(Crm.class,crmId);
            }
            user.setCrm(crm);
            session.save(user);
            // commit transaction
            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static void createCrm(Crm crm) throws SQLException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(crm);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static Issues selectIssue(int Id) {
        Transaction transaction = null;
        Issues issues = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an issues object
            issues = session.get(Issues.class, Id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return issues;
    }
    public static User selectIssueOfUser(int Id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an issues object
            user = session.get(User.class, Id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
    public static List<Issues> selectAllIssues() {
        List<Issues> issues = null;
        try (Session session = sessionFactory.openSession()) {
            issues = session.createQuery("from Issues").list();
            System.out.println(issues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issues;
    }
    public static Crm selectCrm(int id) {

        Transaction transaction = null;
        Crm crm = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            crm = session.get(Crm.class,id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return crm;
    }
    public static User selectUser(int id) {

        Transaction transaction = null;
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            user = session.get(User.class,id);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }
    public static boolean deleteIssue(int id) throws SQLException {
        Transaction transaction = null;
        Session session = null;
        Issues issues = null;
        try {
            session = sessionFactory.openSession();
            // start a transaction
            transaction = session.beginTransaction();
            System.out.println("transaction started "+id);

            issues = session.get(Issues.class, id);
            System.out.println("transaction session build");
            if (issues != null) {
                session.delete(issues);
            }
            // commit transaction
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateIssue(Issues issues) throws SQLException {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the issues object
            session.saveOrUpdate(issues);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static List<Login> loginCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = sessionFactory.openSession();

        System.out.println(request.getParameter("name")+" "+request.getParameter("pass"));
        Query query = session.createQuery("from Login where email ='"+request.getParameter("name")+"' and password = '"+request.getParameter("pass")+"'");
        List<Login> list=query.list();
        if(list.size()!=0){
            HttpSession session1 = request.getSession();
            System.out.println("login done");
            session1.setAttribute("userId",list.get(0).getId());
            session1.setAttribute("owner",list.get(0).getOwner());
            session1.setAttribute("email",list.get(0).getEmail());
            session1.setAttribute("login",list.get(0));
            System.out.println("session created");
            return list;
        }
        else{
            PrintWriter out = response.getWriter();

            out.println("Wrong Id or Wrong Password");
            request.getRequestDispatcher("login.jsp").include(request,response);
        }
        session.close();
        return list;
    }

    public static User selectUserById(int id) {
        Transaction transaction = null;
        User user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object

            user = (User) session.get(User.class,id);

            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public static List<Issues> selectUserForCrm(int id) {
        Transaction transaction = null;
        List<Issues> user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.createQuery("from Issues where user.crm.id = "+id+"").list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public static List<Issues> selectIssuesForUser(int id) {
        Transaction transaction = null;
        List<Issues> user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.createQuery("from Issues where user.id = "+id+"").list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> selectOnlyUserFromCrm(int id) {
        Transaction transaction = null;
        List<User> user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.createQuery("from User where crm.id = "+id+"").list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    public static void createCall(Calls call,User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student object
            session.save(call);
            user.setCall(call);
            session.update(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<User> selectCall(int id) {
        Transaction transaction = null;
        List<User> user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            user = session.createQuery("from User where crm.id = "+id+" and call.id is not null").list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    public static Calls selectCallById(int id) {
        Transaction transaction = null;
        Calls calls=null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            calls = session.get(Calls.class,id);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return calls;
    }

    public static void updateCall(Calls call) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // update the call object
            session.update(call);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void issueSolved(Issues issues) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            IssuesResolved issuesResolved = new IssuesResolved();
            issuesResolved.setId(issues.getId());
            issuesResolved.setDescription(issues.getDescription());
            issuesResolved.setUser(issues.getUser());
            issuesResolved.setStatus(issues.getStatus());
            issuesResolved.setTargetDate(issues.getTargetDate());
            issuesResolved.setCurrentDate(issues.getCurrentDate());
            issuesResolved.setResolvedDate(LocalDate.now());
            session.save(issuesResolved);
            session.delete(issues);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static List<IssuesResolved> resolvedIssuesToCrmList(int id) {
        Transaction transaction = null;
        List<IssuesResolved> issuesResolvedList = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            issuesResolvedList = session.createQuery("from IssuesResolved where user.crm.id = "+id+"").list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return issuesResolvedList;
    }

    public static List<User> selectAllUser() {
        Transaction transaction = null;
        List<User> user = null;
        try (Session session = sessionFactory.openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get all user object
            user = session.createQuery("from User").list();
            // commit transaction
            transaction.commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

    public static List<IssuesResolved> allResolvedIssues() {
        List<IssuesResolved> issuesResolvedList = null;
        try (Session session = sessionFactory.openSession()) {
            issuesResolvedList = session.createQuery("from IssuesResolved ").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issuesResolvedList;
    }

    public static List<Crm> allCrm() {
        List<Crm> crmList = null;
        try (Session session = sessionFactory.openSession()) {
            crmList = session.createQuery("from Crm ").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return crmList;
    }

    public static void cancelCrm(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class,id);
            user.setCrm(null);
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteCall(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Calls call=user.getCall();
            user.setCall(null);
            session.update(user);
            CompletedCall completedCall = new CompletedCall();
            completedCall.setId(call.getId());
            completedCall.setDate(call.getDate());
            completedCall.setTime(call.getTime());
            completedCall.setUser(user);
            session.save(completedCall);
            session.delete(call);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<CompletedCall> selectCompletedCall(int id) {
        List<CompletedCall> completedCallList = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            completedCallList = session.createQuery("from CompletedCall where user.crm.id = "+id+"").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return completedCallList;
    }

    public static List<User> selectAllCall() {
        List<User> userList = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("from User where call.id IS NOT NULL").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public static List<CompletedCall> selectAllCompletedCall() {
        List<CompletedCall> completedCallList = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            completedCallList = session.createQuery("from CompletedCall").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return completedCallList;
    }
}
