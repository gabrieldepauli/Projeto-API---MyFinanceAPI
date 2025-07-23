package controller.handlers;

import controller.command.ListarCategoriasCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetCategoriasHandler extends AbstractHandler {

	@Override
	protected boolean canHandle(HttpServletRequest request) {
	    System.out.println("GetCategoriasHandler canHandle chamado. Method: " + request.getMethod() + " PathInfo: " + request.getPathInfo());
	    return request.getMethod().equals("GET")
	            && request.getPathInfo() != null
	            && request.getPathInfo().equals("/categorias");
	}


    @Override
    protected void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        new ListarCategoriasCommand().execute(request, response);
    }
    
}
