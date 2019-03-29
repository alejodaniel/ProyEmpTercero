package com.mycompany.filter;

import com.mycompany.bean.AccesoBean;
import com.mycompany.bean.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //Obtengo el bean que representa al usuario donde el scoped session
        AccesoBean accesoBean = (AccesoBean) req.getSession().getAttribute("accesoBean");

        //aqui se procesa la URL que esta refieriendo al cliente 
        String urlStr = req.getRequestURL().toString().toLowerCase();
        boolean noProteger = noProteger(urlStr);

        //Si no requiere la proteccion se continua normalmente
        if (noProteger(urlStr)) {
            chain.doFilter(request, response);
            return;
        }
        //el usuario no esta logueado
        if (accesoBean == null || !accesoBean.isLogeado()) {
            res.sendRedirect(req.getContextPath() + "/admin.xhtml");
            return;
        }
        //El recurso requiere proteccion , pero el usuario ya esta logueado
        chain.doFilter(request, response);
    }

    private boolean noProteger(String urlStr) {
        /*Aqui se coloca todas las rutas que no queramos que esten sin looguearse*/
        if (urlStr.indexOf("/admin.xhtml") != -1) 
            return true;
        

        if (urlStr.indexOf("/javax.faces.resource/") != -1) 
            return true;
        
        return false;
    }

    @Override
    public void destroy() {

    }
}
