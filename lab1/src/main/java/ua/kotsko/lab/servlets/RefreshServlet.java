package ua.kotsko.lab.servlets;

import ua.kotsko.lab.services.AdminService;
import ua.kotsko.lab.services.AuthorizationService;
import ua.kotsko.lab.exceptions.HttpException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/refresh")
public class RefreshServlet extends HttpServlet {
    private final AdminService adminService = new AdminService();
    private final AuthorizationService authorizationService = new AuthorizationService();

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) {
        if (!authorizationService.hasAuthority(request, "admin")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
            adminService.refresh(request);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (HttpException e) {
            response.setStatus(e.getHttpCode());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
