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

public class ListarTransacoesCommand implements Command {

	private static final int DEFAULT_PAGE = 0;
	
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pagina = 0;
        String paginaStr = request.getParameter("pagina");

        if (paginaStr != null && !paginaStr.isEmpty()) {
            try {
                pagina = Integer.parseInt(paginaStr);
                
                if (pagina < 0) { 
                    pagina = DEFAULT_PAGE;
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> transacoes = dao.listar(pagina);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(transacoes));
        out.flush();
    }
}
