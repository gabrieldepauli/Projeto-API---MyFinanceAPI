package controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.TransacaoDAO;

public class DeletarTransacaoCommand implements Command {

	@Override
	public void execute (HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String path = request.getPathInfo();

	    if (path == null || !path.matches("^/\\d+$")) {
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        response.setContentType("application/json");
	        response.getWriter().print("{\"erro\":\"ID da Transação inválido.\"}");
	        response.getWriter().flush();
	        
	        return;
	    }

	    int id = Integer.parseInt(path.substring(1));
	    TransacaoDAO dao = new TransacaoDAO();
	    boolean sucesso = dao.deletar(id);

	    response.setContentType("application/json");

	    if (sucesso) {
	        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	    } else {
	        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        response.getWriter().print("{\"erro\":\"A transação não foi encontrada.\"}");
	        response.getWriter().flush();
	    }
	}

}
