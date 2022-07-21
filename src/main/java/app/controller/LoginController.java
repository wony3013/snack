package app.controller;

import java.io.IOException;
import java.io.PrintWriter;

import app.http.HttpRequest;
import app.http.HttpResponse;
import app.model.User;
import app.model.Users;

public class LoginController extends AbstractController {
    @Override
    public void doPost(HttpRequest request, HttpResponse response) {
        /** TODO response add Header "Set-Cookie" "logined=true"
         request Cookie logined key value
         Cookie key jsessionid=*/
        User loginUser = Users.getUser(request.getParameter("id"));

        try (PrintWriter script = response.getWriter()) {
            if (loginUser != null) {
                if (loginUser.login(request.getParameter("password"))) {
                    response.addHeader("Set-cookie", "logined=true");
                    response.addHeader("Set-cookie", "SESSIONID=loginedkey");
                } else {
                    script.println("<script>");
                    script.println("alert('비밀번호가 틀립니다.')");
                    script.println("history.back()");
                    script.println("</script>");
                }
            } else {
                script.println("<script>");
                script.println("alert('존재하지 않는 아이디입니다.')");
                script.println("history.back()");
                script.println("</script>");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            response.sendRedirect("/index.html");
        }
    }
}

