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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        int pagina = 0;
        String paginaParam = request.getParameter("page");
        if (paginaParam != null) {
            try {
                pagina = Integer.parseInt(paginaParam);
                if (pagina < 0) pagina = 0;
            } catch (NumberFormatException ignored) {}
        }

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.buscarPorTipoECategoria(tipo, categoria, pagina);
        int totalTransacoes = dao.contarPorTipoECategoria(tipo, categoria);
        int totalPaginas = (int) Math.ceil((double) totalTransacoes / 10);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("transacoes", transacoes);
        resposta.put("paginaAtual", pagina);
        resposta.put("totalPaginas", totalPaginas);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(resposta));
        out.flush();
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
