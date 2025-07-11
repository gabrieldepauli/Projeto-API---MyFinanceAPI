package controller.handlers;

import controller.command.ListarTransacoesCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetTransacoesHandler extends AbstractHandler {

	@Override
	protected boolean canHandle(HttpServletRequest request) {
		return request.getMethod().equals("GET") 
				&& request.getPathInfo() != null && request.getPathInfo().equals("/paginated");
	}

	@Override
	protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new ListarTransacoesCommand().execute(request, response);
	}
	
}
