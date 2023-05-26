package service;

import daos.IDao;
import entity.Odontologo;

import java.util.List;

public class OdontologoService {
    private static IDao<Odontologo> odontologoIDao;

    public IDao<Odontologo> getOdontologoIDao() {
        return odontologoIDao;
    }

    public void setOdontologoIDao(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }


    public Odontologo registrarOdontologo(Odontologo odontologo){

        return odontologoIDao.registrar(odontologo);

    }


    public static List<Odontologo> listarTodos(){
        return odontologoIDao.listarTodos();
    }


}
