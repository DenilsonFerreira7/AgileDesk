package com.laudoStratus.demo.mapper;

import com.laudoStratus.demo.DTO.EmpresaResponse;
import com.laudoStratus.demo.models.Empresa;

public class EmpresaMapper {

    public static EmpresaResponse mapEmpresaToEmpresaResponse(Empresa empresa) {
        return new EmpresaResponse(
                empresa.getIdEmpresa(),
                empresa.getNomeEmpresa(),
                empresa.getEndereco(),
                empresa.getTelefone()
        );
    }
}