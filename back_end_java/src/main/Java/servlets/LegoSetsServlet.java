package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import daos.LegoSetDAO;
import daos.LegoSetDAOImpl;
import models.ErrorMsg;
import models.LegoSet;
import services.UrlParserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@WebServlet(urlPatterns = "/lego-set/*")
public class LegoSetsServlet extends HttpServlet {

    LegoSetDAO dao = new LegoSetDAOImpl();
    ObjectMapper mapper = new ObjectMapper();
    UrlParserService urlParser = new UrlParserService();

    // Find sets:
    // If there is an id, returns the wanted set.
    // If there is no id, returns all sets.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = urlParser.extractIdFromURL(req.getPathInfo());
            LegoSet legoSet = dao.findSetByID(id);
            if(legoSet != null) {
                resp.setContentType("application/json");
                resp.getWriter().print(mapper.writeValueAsString(legoSet));
            } else {
                resp.setStatus(404);
                resp.getWriter().print(mapper.writeValueAsString(new ErrorMsg("Please provide a valid id")));
            }

        } catch( Exception e) {
            List<LegoSet> legoSets = dao.findAllSets();
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(legoSets));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream reqBody = req.getInputStream();
        LegoSet newSet = mapper.readValue(reqBody, LegoSet.class);
        newSet = dao.createSet(newSet);
        if(newSet != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(newSet));
            resp.setStatus(201);
        } else {
            resp.setStatus(400);
            resp.getWriter().print(mapper.writeValueAsString(new ErrorMsg("Set could not be saved")));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = urlParser.extractIdFromURL(req.getPathInfo());
        dao.deleteSet(id);
        resp.setContentType("application/json");
        resp.setStatus(201);

    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = urlParser.extractIdFromURL(req.getPathInfo());
        InputStream reqBody = req.getInputStream();
        LegoSet updatedSet = mapper.readValue(reqBody, LegoSet.class);
        updatedSet = dao.updateSet(updatedSet, id);
        if(updatedSet != null) {
            resp.setContentType("application/json");
            resp.getWriter().print(mapper.writeValueAsString(updatedSet));
            resp.setStatus(201);
        } else {
            resp.setStatus(404);
            resp.getWriter().print(mapper.writeValueAsString(new ErrorMsg("Please provide a valid id")));
        }
    }
}
