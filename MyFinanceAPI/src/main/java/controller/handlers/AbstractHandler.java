package controller.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class AbstractHandler implements Handler {
	protected Handler next;

	@Override
	public void setNext(Handler next) {
		this.next = next;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " handle chamado. PathInfo: " + request.getPathInfo());
		
		if (canHandle(request)) {
			process(request, response);
		} else if (next != null) {
			next.handle(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "O endpoint não foi encontrado.");
		}
	}

	protected abstract boolean canHandle(HttpServletRequest request);

	protected abstract void process(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
