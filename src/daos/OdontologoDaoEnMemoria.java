package daos;

import entity.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoEnMemoria implements IDao<Odontologo>{

    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoEnMemoria.class);
    private List<Odontologo> odontologoRepository;

    public OdontologoDaoEnMemoria(List<Odontologo> odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrar(Odontologo odontologo) {

        odontologoRepository.add(odontologo);
        LOGGER.info("Odontolodo Guardado: "+ odontologo);
        return odontologo;


    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listado de todos los odontologos : " + odontologoRepository);

        return odontologoRepository;
    }
}
