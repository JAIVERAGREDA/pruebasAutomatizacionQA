package test;

import dto.tienda.TiendaDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.Ingreso.TiendaPage;

public class RegistroInicioTest {
    private TiendaPage tiendaPage;

    @BeforeClass
    public void iniciarPrueba() {
        tiendaPage = new TiendaPage();
    }

    @Test
    public void testRegistrar() {
        tiendaPage.abrirPagina("https://opencart.abstracta.us");

        TiendaDto tiendaDto = new TiendaDto();
        tiendaDto.setFirstName("Jaiver");
        tiendaDto.setSecondName("Agreda");
        tiendaDto.setEmail("jaiver.agreda020133@gmail.com");
        tiendaDto.setTelephone("3154506142");
        tiendaDto.setPassword("12345");
        tiendaDto.setConfPassword("12345");

        // Pasar el objeto TiendaDto al método de registro
        tiendaPage.registro(tiendaDto);
    }

   @AfterClass
    public void cerrarSesion() {
        tiendaPage.cerrarPagina();
    }
}
