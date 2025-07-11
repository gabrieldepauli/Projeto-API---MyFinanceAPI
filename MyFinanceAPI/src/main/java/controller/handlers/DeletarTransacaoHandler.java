package controller.handlers;

import controller.command.DeletarTransacaoCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeletarTransacaoHandler extends AbstractHandler{
	
	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("DELETE") 
				&& request.getPathInfo() != null && request.getPathInfo().matches("^/\\d+$");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DeletarTransacaoCommand().execute(request, response);
	}
	
}
