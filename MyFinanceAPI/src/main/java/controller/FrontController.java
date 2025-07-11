package controller;

import java.io.IOException;

import controller.handlers.Handler;
import controller.handlers.HandlerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/transacoes/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Handler chain;

	@Override
	public void init() {
		try {
			chain = HandlerFactory.criarCadeia();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao inicializar a cadeia de Handlers", e);
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			chain.handle(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
}
