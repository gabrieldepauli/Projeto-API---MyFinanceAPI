/*package controller;

import java.util.HashMap;
import java.util.Map;

import controller.command.*;
import jakarta.servlet.http.HttpServletRequest;


// NÃO ESTÁ SENDO UTILIZADA
public class TransacoesDispatcher {

    private final Map<String, Command> rotas = new HashMap<>();

    public TransacoesDispatcher() {
        registrarRotas();
    }

    private void registrarRotas() {
        rotas.put(chave("GET", "/paginated"), new ListarTransacoesCommand());
        rotas.put(chave("POST", "/"), new InserirTransacaoCommand());
        rotas.put(chave("GET", "/id"), new BuscarTransacaoPorIdCommand());
        rotas.put(chave("PUT", "/id"), new AtualizarTransacaoCommand());
        rotas.put(chave("DELETE", "/id"), new DeletarTransacaoCommand());
        rotas.put(chave("GET", "/tipo"), new ListarTransacoesPeloTipoCommand());
        rotas.put(chave("GET", "/categoria"), new ListarTransacoesPelaCategoriaCommand());
        rotas.put(chave("GET", "/categoriaetipo"), new BuscarTransacoesPorCategoriaETipoCommand());
        rotas.put(chave("GET", "/resumo"), new ObterResumoCommand());
    }

    private String chave(String metodo, String caminho) {
        return metodo + ":" + caminho;
    }

    public Command despachar(HttpServletRequest request) {
        String metodo = request.getMethod();
        String path = request.getPathInfo();

        if (path == null || path.equals("/")) {
            return rotas.get(chave(metodo, "/"));
        }

        if (path.matches("^/\\d+$")) {
            return rotas.get(chave(metodo, "/id"));
        }

        if (path.startsWith("/tipo/")) {
            return rotas.get(chave(metodo, "/tipo"));
        }

        if (path.startsWith("/categoria/")) {
            return rotas.get(chave(metodo, "/categoria"));
        }

        if (path.startsWith("/categoriaetipo/") && path.contains("+")) {
            return rotas.get(chave(metodo, "/categoriaetipo"));
        }

        if (path.equals("/resumo")) {
            return rotas.get(chave(metodo, "/resumo"));
        }

        if (path.equals("/paginated")) {
            return rotas.get(chave(metodo, "/paginated"));
        }

        return null;
    }
    
}*/
