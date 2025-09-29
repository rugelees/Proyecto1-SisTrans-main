SELECT
    s.tipo AS tipo_servicio,
    COUNT(*) AS numero_de_viajes,
    ROUND((COUNT(*) * 100.0 / SUM(COUNT(*)) OVER ()), 2) AS porcentaje_del_total
FROM
    SERVICIO s
INNER JOIN
    PUNTO_TRAYECTO pt ON s.id_punto_partida = pt.id_punto
WHERE
    pt.ciudad = 'Bogotá' 
    AND s.fecha_hora_inicio BETWEEN TO_TIMESTAMP('2025-09-01 00:00:00', 'YYYY-MM-DD HH24:MI:SS') AND TO_TIMESTAMP('2025-09-30 23:59:59', 'YYYY-MM-DD HH24:MI:SS')
GROUP BY
    s.tipo
ORDER BY
    numero_de_viajes DESC;