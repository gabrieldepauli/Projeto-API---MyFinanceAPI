package controller.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.TransacaoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transacao;
import util.LocalDateAdapter;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

public class ListarTransacoesPelaCategoriaCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();

        if (path == null || !path.startsWith("/categoria/") || path.length() <= "/categoria/".length()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Categoria inválida na URL. Formato esperado: /categoria/{valor}");
            return;
        }

        String categoria = path.substring("/categoria/".length());

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.buscarPorCategoria(categoria);

        if (transacoes == null || transacoes.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nenhuma transação com essa categoria.");
            return;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(transacoes));
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
