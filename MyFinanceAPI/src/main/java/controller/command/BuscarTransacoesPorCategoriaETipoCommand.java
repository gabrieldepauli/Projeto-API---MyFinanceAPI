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

public class BuscarTransacoesPorCategoriaETipoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();

        if (path == null || !path.startsWith("/categoriaetipo/") || !path.contains("+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                "Formato de URL inválido. Esperado: /categoriaetipo/{categoria}+{tipo}");
            return;
        }

        String params = path.substring("/categoriaetipo/".length());
        String[] parts = params.split("\\+");

        if (parts.length != 2 || parts[0].isEmpty() || parts[1].isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                "Parâmetros de categoria e/ou tipo inválidos. Esperado: /categoriaetipo/{categoria}+{tipo}");
            return;
        }

        String categoria = parts[0];
        String tipo = parts[1];

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.buscarPorTipoECategoria(tipo, categoria);

        if (transacoes == null || transacoes.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                "Nenhuma transação encontrada para a categoria '" + categoria + "' e tipo '" + tipo + "'.");
            return;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(transacoes));
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
