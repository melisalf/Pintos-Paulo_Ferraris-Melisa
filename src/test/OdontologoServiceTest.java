package test;

import daos.OdontologoDaoH2;
import entity.Odontologo;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private static Connection connection = null;
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    @Test
    public void deberiaAgregarUnOdontologo(){
       Odontologo odontologoTest = new Odontologo(312312,"Nombre","Apellido" );
        Odontologo odontologoTest2 = new Odontologo(31332312,"Nombres","Apellidos" );


        Odontologo odontologoResult = odontologoService.registrarOdontologo(odontologoTest);
        Odontologo odontologoResult2 = odontologoService.registrarOdontologo(odontologoTest2);

        assertNotNull(odontologoResult);
        assertEquals("Apellido", odontologoResult.getApellido());
        assertNotNull(odontologoResult2);
        assertEquals("Apellido", odontologoResult.getApellido());

    }


    @Test
    public void deberiaMostrarOdontologosTest(){
        List<Odontologo> odontologosTest = OdontologoService.listarTodos();
        assertFalse(odontologosTest.isEmpty());
        assertTrue(odontologosTest.size() <= 3);
        }
    }


