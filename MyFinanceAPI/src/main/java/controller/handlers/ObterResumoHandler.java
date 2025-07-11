package controller.handlers;

import controller.command.ObterResumoCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ObterResumoHandler extends AbstractHandler {

	@Override
    protected boolean canHandle(HttpServletRequest request) {
        return "GET".equals(request.getMethod())
                && "/resumo".equals(request.getPathInfo());
    }

    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new ObterResumoCommand().execute(request, response);
    }
	
}
