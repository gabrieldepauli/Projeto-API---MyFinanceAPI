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

public class BuscarTransacaoPorIdCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();

        if (path == null || !path.matches("^/\\d+$")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"erro\":\"ID inválido.\"}");
            return;
        }

        int id = Integer.parseInt(path.substring(1));
        TransacaoDAO dao = new TransacaoDAO();
        Transacao transacao = dao.buscarPorId(id);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        if (transacao != null) {
        	response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(transacao));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"erro\":\"Transação não encontrada.\"}");
        }

        out.flush();
    }
}
