package controller.command;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Transacao;
import dao.TransacaoDAO;
import util.LocalDateAdapter;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDate;

public class AtualizarTransacaoCommand implements Command {

    @Override
    public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

        BufferedReader reader = request.getReader();
        Transacao transacao = gson.fromJson(reader, Transacao.class);

        String path = request.getPathInfo();
        int id = Integer.parseInt(path.substring(1));

        TransacaoDAO dao = new TransacaoDAO();
        boolean sucesso = dao.atualizar(id, transacao);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if (sucesso) {
            out.print(gson.toJson(transacao));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print("{\"erro\":\"Transação não encontrada para atualização.\"}");
        }
        out.flush();
    }
    
}
