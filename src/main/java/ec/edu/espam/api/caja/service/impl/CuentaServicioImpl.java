package ec.edu.espam.api.caja.service.impl;

import ec.edu.espam.api.caja.domain.Cuenta;
import ec.edu.espam.api.caja.repository.CuentaRepositorio;
import ec.edu.espam.api.caja.service.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {

    private final CuentaRepositorio cuentaRepositorio;
    @Override
    public List<Cuenta> obtenerTodos() {
        return cuentaRepositorio.findAll();
    }

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    @Override
    public Cuenta editar(Long idCuenta, Cuenta cuenta) throws Exception {
        Optional<Cuenta> cuentaOptional= cuentaRepositorio.findById(idCuenta);
        if (cuentaOptional.isPresent()) {
            Cuenta cuentaO = cuentaOptional.get();
            cuentaO.setNumero(cuenta.getNumero());
            cuentaO.setTipo(cuenta.getTipo());
            cuentaO.setBalanceInicial(cuenta.getBalanceInicial());
            cuentaO.setSaldo(cuenta.getSaldo());
            cuentaO.setEstado(cuenta.getEstado());
            return cuentaRepositorio.save(cuentaO);
        }else{
            throw new Exception("No se encontraron datos ");
        }
    }

}
