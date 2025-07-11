package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ResumoFinanceiro;
import model.Transacao;
import util.ConnectionFactory;

public class TransacaoDAO {

    private int quantidadePorPagina = 10;

    public void inserir(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO Transacoes (descricao, valor, tipo, categoria, data_criacao) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            stmt.setString(4, transacao.getCategoria());
            stmt.setDate(5, java.sql.Date.valueOf(transacao.getData()));

            stmt.executeUpdate();
        }
    }

    public List<Transacao> listar(int pagina) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM Transacoes ORDER BY id DESC LIMIT ? OFFSET ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantidadePorPagina);
            stmt.setInt(2, quantidadePorPagina * pagina);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao t = new Transacao();

                    t.setId(rs.getInt("id"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setValor(rs.getDouble("valor"));
                    t.setTipo(rs.getString("tipo"));
                    t.setCategoria(rs.getString("categoria"));
                    t.setData(rs.getObject("data_criacao", LocalDate.class));

                    transacoes.add(t);
                }
            }
        }

        return transacoes;
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM Transacoes WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            return linhasAfetadas > 0;
        }
    }

    public Transacao buscarPorId(int id) throws SQLException {
        Transacao transacao = null;
        String sql = "SELECT * FROM Transacoes WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    transacao = new Transacao();

                    transacao.setId(rs.getInt("id"));
                    transacao.setDescricao(rs.getString("descricao"));
                    transacao.setValor(rs.getDouble("valor"));
                    transacao.setTipo(rs.getString("tipo"));
                    transacao.setCategoria(rs.getString("categoria"));
                    transacao.setData(rs.getObject("data_criacao", LocalDate.class));
                }
            }
        }

        return transacao;
    }

    public List<Transacao> buscarPorCategoria(String categoria) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM Transacoes WHERE categoria = ? ORDER BY id DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, categoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao t = new Transacao();

                    t.setId(rs.getInt("id"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setValor(rs.getDouble("valor"));
                    t.setTipo(rs.getString("tipo"));
                    t.setCategoria(rs.getString("categoria"));
                    t.setData(rs.getObject("data_criacao", LocalDate.class));

                    transacoes.add(t);
                }
            }
        }

        return transacoes;
    }

    public List<Transacao> buscarPorTipo(String tipo) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM Transacoes WHERE tipo = ? ORDER BY id DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao t = new Transacao();

                    t.setId(rs.getInt("id"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setValor(rs.getDouble("valor"));
                    t.setTipo(rs.getString("tipo"));
                    t.setCategoria(rs.getString("categoria"));
                    t.setData(rs.getObject("data_criacao", LocalDate.class));

                    transacoes.add(t);
                }
            }
        }

        return transacoes;
    }

    public List<Transacao> buscarPorTipoECategoria(String tipo, String categoria) throws SQLException {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM Transacoes WHERE tipo = ? AND categoria = ? ORDER BY id DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipo);
            stmt.setString(2, categoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao t = new Transacao();

                    t.setId(rs.getInt("id"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setValor(rs.getDouble("valor"));
                    t.setTipo(rs.getString("tipo"));
                    t.setCategoria(rs.getString("categoria"));
                    t.setData(rs.getObject("data_criacao", LocalDate.class));

                    transacoes.add(t);
                }
            }
        }

        return transacoes;
    }

    public boolean atualizar(int id, Transacao transacao) throws SQLException {
        String sql = "UPDATE Transacoes SET descricao = ?, valor = ?, tipo = ?, categoria = ?, data_criacao = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transacao.getDescricao());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            stmt.setString(4, transacao.getCategoria());
            stmt.setDate(5, java.sql.Date.valueOf(transacao.getData()));
            stmt.setInt(6, id);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        }
    }

    public ResumoFinanceiro obterResumo() throws Exception {
        ResumoFinanceiro resumo = new ResumoFinanceiro();

        try (Connection conn = ConnectionFactory.getConnection()) {

            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COALESCE(SUM(valor), 0) FROM Transacoes WHERE tipo = 'receita'");
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    resumo.setTotalReceitas(rs.getDouble(1));
                }
            }

            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT COALESCE(SUM(valor), 0) FROM Transacoes WHERE tipo = 'despesa'");
                 ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    resumo.setTotalDespesas(rs.getDouble(1));
                }
            }

            resumo.setSaldoAtual(resumo.getTotalReceitas() - resumo.getTotalDespesas());

            Map<String, Double> despesasPorCategoria = new HashMap<>();
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT categoria, COALESCE(SUM(valor), 0) AS total FROM Transacoes WHERE tipo = 'despesa' GROUP BY categoria");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    despesasPorCategoria.put(rs.getString("categoria"), rs.getDouble("total"));
                }
            }
            resumo.setDespesasPorCategoria(despesasPorCategoria);

            Map<String, Double> receitasPorCategoria = new HashMap<>();
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT categoria, COALESCE(SUM(valor), 0) AS total FROM Transacoes WHERE tipo = 'receita' GROUP BY categoria");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    receitasPorCategoria.put(rs.getString("categoria"), rs.getDouble("total"));
                }
            }
            resumo.setReceitasPorCategoria(receitasPorCategoria);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return resumo;
    }

}
