SELECT
    s.id AS id_servicio,
    s.fecha_hora_inicio,
    s.costo_total,
    s.tipo AS tipo_servicio,
    pt_partida.direccion AS punto_de_partida,
    u_conductor.nombre AS nombre_conductor,
    v.placa
FROM
    SERVICIO s
LEFT JOIN
    USUARIO u_conductor ON s.cedula_conductor = u_conductor.cedula
LEFT JOIN
    VEHICULO v ON s.placa_vehiculo = v.placa
INNER JOIN
    PUNTO_TRAYECTO pt_partida ON s.id_punto_partida = pt_partida.id_punto
WHERE
    s.cedula_solicitante = 2001 
ORDER BY
    s.fecha_hora_inicio DESC;