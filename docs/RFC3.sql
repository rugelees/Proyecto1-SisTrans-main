SELECT
    s.placa_vehiculo,
    s.tipo AS tipo_servicio,
    SUM(s.costo_total * 0.60) AS ganancia_total
FROM
    SERVICIO s
WHERE
    s.cedula_conductor = 1001 
GROUP BY
    s.placa_vehiculo,
    s.tipo
ORDER BY
    s.placa_vehiculo,
    ganancia_total DESC;