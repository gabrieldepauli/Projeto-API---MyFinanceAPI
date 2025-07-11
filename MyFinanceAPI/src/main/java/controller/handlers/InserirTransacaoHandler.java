package controller.handlers;

import controller.command.InserirTransacaoCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InserirTransacaoHandler extends AbstractHandler {

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("POST")
				&& (request.getPathInfo() == null || request.getPathInfo().equals("/"));
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new InserirTransacaoCommand().execute(request, response);
	}
	
}
