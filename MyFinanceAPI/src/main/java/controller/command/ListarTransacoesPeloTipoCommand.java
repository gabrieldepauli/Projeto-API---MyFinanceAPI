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

public class ListarTransacoesPeloTipoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();

        if (path == null || !path.startsWith("/tipo/") || path.length() <= "/tipo/".length()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tipo inválido na URL. Formato esperado: /tipo/{valor}");
            return;
        }

        String tipo = path.substring("/tipo/".length());

        int pagina = 0;
        String paginaParam = request.getParameter("page");
        if (paginaParam != null) {
            try {
                pagina = Integer.parseInt(paginaParam);
                if (pagina < 0) pagina = 0;
            } catch (NumberFormatException ignored) {}
        }

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.buscarPorTipo(tipo, pagina);
        int totalTransacoes = dao.contarPorTipo(tipo);
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
