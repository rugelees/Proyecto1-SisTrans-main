package com.sistrans.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sistrans.entity.Servicio;
import com.sistrans.entity.ConductorDisponible;


@Repository
public class ServicioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ConductorDisponible buscarConductorDisponible(String tipoServicio) {
        // Map service types to the vehicle type used in the seeded data
        String tipoVehiculo = mapearTipoServicioATipoVehiculo(tipoServicio);

        String sql = "SELECT u.cedula, v.placa FROM USUARIO u " +
                    "JOIN VEHICULO v ON u.cedula = v.cedula_dueño " +
                    "WHERE u.rol = 'CONDUCTOR' " +
                    "AND v.tipo = ? " +
                    "AND NOT EXISTS (SELECT 1 FROM SERVICIO s WHERE s.cedula_conductor = u.cedula AND s.fecha_hora_fin IS NULL) " +
                    "FETCH FIRST 1 ROWS ONLY";

        try {
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new ConductorDisponible(rs.getLong("cedula"), rs.getString("placa")), tipoVehiculo);
        } catch (Exception e) {
            return null;
        }
    }

    private String mapearTipoServicioATipoVehiculo(String tipoServicio) {
        if (tipoServicio == null) return "carro";
        String ts = tipoServicio.trim().toUpperCase();
        switch (ts) {
            case "TRANSPORTE_PASAJEROS":
            case "ENTREGA_COMIDA":
            case "TRANSPORTE_MERCANCIA":
                return "carro"; 
            default:
                return "carro";
        }
    }

    public Long insertarServicio(Servicio servicio) {
        String sql = "INSERT INTO SERVICIO (fecha_hora_inicio, fecha_hora_fin, distancia, costo_total, tipo, cedula_solicitante, cedula_conductor, placa_vehiculo, id_punto_partida, tarjeta_credito) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, servicio.getFechaHoraInicio(), servicio.getFechaHoraFin(), 
                          servicio.getDistancia(), servicio.getCostoTotal(), servicio.getTipo(), 
                          servicio.getCedulaSolicitante(), servicio.getCedulaConductor(), 
                          servicio.getPlacaVehiculo(), servicio.getIdPuntoPartida(), servicio.getTarjetaCredito());
        
        String getIdSql = "SELECT id FROM SERVICIO WHERE cedula_solicitante = ? AND cedula_conductor = ? ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
        return jdbcTemplate.queryForObject(getIdSql, Long.class, servicio.getCedulaSolicitante(), servicio.getCedulaConductor());
    }

    public void insertarDestino(Long idServicio, Long idPuntoDestino) {
        String sql = "INSERT INTO DESTINOS_SERVICIO (id_servicio, id_punto_destino, orden) VALUES (?, ?, 1)";
        jdbcTemplate.update(sql, idServicio, idPuntoDestino);
    }

    public boolean existeServicio(Long idServicio) {
        String sql = "SELECT COUNT(*) FROM SERVICIO WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, idServicio);
        return count != null && count > 0;
    }

    public void finalizarServicio(Long idServicio, String distancia, Double costoTotal) {
        String sql = "UPDATE SERVICIO SET fecha_hora_fin = CURRENT_TIMESTAMP, distancia = ?, costo_total = ? WHERE id = ?";
        jdbcTemplate.update(sql, distancia, costoTotal, idServicio);
    }

    public void insertarTransportePasajeros(Long idServicio, String nivel) {
        String sql = "INSERT INTO TRANSPORTE_PASAJEROS (servicio_id, nivel) VALUES (?, ?)";
        jdbcTemplate.update(sql, idServicio, nivel);
    }

    public void insertarEntregaComida(Long idServicio, String nombreRestaurante) {
        String sql = "INSERT INTO ENTREGA_COMIDA (servicio_id, nombre_restaurante) VALUES (?, ?)";
        jdbcTemplate.update(sql, idServicio, nombreRestaurante);
    }

    public void insertarTransporteMercancia(Long idServicio, Double pesoCarga) {
        String sql = "INSERT INTO TRANSPORTE_MERCANCIA (servicio_id, carga) VALUES (?, ?)";
        jdbcTemplate.update(sql, idServicio, pesoCarga);
    }

}
