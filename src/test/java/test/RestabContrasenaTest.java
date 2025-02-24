package test;

import dto.tienda.TiendaDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.Ingreso.TiendaPage;

public class RestabContrasenaTest {
    private TiendaPage tiendaPage;

    @BeforeClass
    public void iniciarPrueba() {
        tiendaPage = new TiendaPage();
    }

    @Test
    public void restablecerContrasena() {
        tiendaPage.abrirPagina("https://opencart.abstracta.us");

        TiendaDto tiendaDto = new TiendaDto();

        tiendaDto.setEmail("jaiver.agreda020133gmail.com");

        tiendaPage.restablecerContrasena(tiendaDto);
    }

   @AfterClass
    public void cerrarSesion() {
        tiendaPage.cerrarPagina();
    }
}
