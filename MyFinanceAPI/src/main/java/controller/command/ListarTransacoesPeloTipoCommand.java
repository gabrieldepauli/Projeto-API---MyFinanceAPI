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

public class ListarTransacoesPeloTipoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();

        if (path == null || !path.startsWith("/tipo/") || path.length() <= "/tipo/".length()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo inválido na URL. Formato esperado: /tipo/{valor}");
            return;
        }

        String tipo = path.substring("/tipo/".length());

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.buscarPorTipo(tipo);

        if (transacoes == null || transacoes.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Nenhuma transação com esse tipo.");
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
