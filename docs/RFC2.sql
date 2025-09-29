SELECT
    u.cedula,
    u.nombre,
    u.correo,
    COUNT(s.id) AS total_servicios_prestados
FROM
    USUARIO u
INNER JOIN
    SERVICIO s ON u.cedula = s.cedula_conductor
WHERE
    u.rol = 'CONDUCTOR'
GROUP BY
    u.cedula, u.nombre, u.correo
ORDER BY
    total_servicios_prestados DESC
FETCH FIRST 20 ROWS ONLY;