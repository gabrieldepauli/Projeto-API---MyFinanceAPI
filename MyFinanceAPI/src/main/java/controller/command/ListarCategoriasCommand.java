package controller.command;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TransacaoDAO;

import java.io.PrintWriter;
import java.util.List;

public class ListarCategoriasCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TransacaoDAO dao = new TransacaoDAO();
        List<String> categorias = dao.listarCategorias();

        if (categorias == null || categorias.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nenhuma categoria encontrada.");
            return;
        }

        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(categorias));
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
