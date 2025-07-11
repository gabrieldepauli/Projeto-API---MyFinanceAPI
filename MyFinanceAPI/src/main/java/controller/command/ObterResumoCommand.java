package controller.command;

import java.io.PrintWriter;

import com.google.gson.Gson;

import dao.TransacaoDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ResumoFinanceiro;

public class ObterResumoCommand implements Command {

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        TransacaoDAO dao = new TransacaoDAO();
        ResumoFinanceiro resumo = dao.obterResumo();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter out = response.getWriter()) {
            out.print(new Gson().toJson(resumo));
            out.flush();
        }
    }
	
}
