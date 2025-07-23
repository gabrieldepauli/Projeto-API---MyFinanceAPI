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

public class ListarTransacoesPelaCategoriaCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = request.getPathInfo();

        if (path == null || !path.startsWith("/categoria/") || path.length() <= "/categoria/".length()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Categoria inválida na URL. Formato esperado: /categoria/{valor}");
            return;
        }

        String categoria = path.substring("/categoria/".length());
        int pagina = 0;

        String paginaParam = request.getParameter("page");
        if (paginaParam != null) {
            try {
                pagina = Integer.parseInt(paginaParam);
                if (pagina < 0) pagina = 0;
            } catch (NumberFormatException ignored) {}
        }

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.buscarPorCategoria(categoria, pagina);
        int totalTransacoes = dao.contarPorCategoria(categoria);
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
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        out.print(gson.toJson(resposta));
        out.flush();
    }
}

