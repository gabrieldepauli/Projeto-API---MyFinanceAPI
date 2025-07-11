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

public class InserirTransacaoCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        BufferedReader reader = request.getReader();
        Transacao transacao = gson.fromJson(reader, Transacao.class);

        TransacaoDAO dao = new TransacaoDAO();
        dao.inserir(transacao);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(transacao));
        out.flush();

        response.setStatus(HttpServletResponse.SC_CREATED);
    }
    
}
