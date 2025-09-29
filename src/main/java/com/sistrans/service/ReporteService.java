package com.sistrans.service;

import com.sistrans.dto.Rfc1HistorialDTO;
import com.sistrans.dto.Rfc2TopConductorDTO;
import com.sistrans.dto.Rfc3GananciasVehiculoDTO;
import com.sistrans.dto.Rfc4UsoServicioDTO;
import com.sistrans.repository.RfcRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//rfc 1
@Service
public class ReporteService {

    private final RfcRepository repo;

    public ReporteService(RfcRepository repo) {
        this.repo = repo;
    }

    public List<Rfc1HistorialDTO> rfc1(String cedula, Integer limite) {
        return repo.rfc1Historial(cedula, limite);
    }

    public List<Rfc2TopConductorDTO> rfc2(Integer limite){
    return repo.rfc2TopConductores(limite);
    }

    public List<Rfc3GananciasVehiculoDTO> rfc3(String cedulaConductor){
    return repo.rfc3GananciasPorConductor(cedulaConductor);
    }

    public List<Rfc4UsoServicioDTO> rfc4(LocalDateTime desde, LocalDateTime hasta){
    return repo.rfc4UsoServicios(desde, hasta);
    }
}
