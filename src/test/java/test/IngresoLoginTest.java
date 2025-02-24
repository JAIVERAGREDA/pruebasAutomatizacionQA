package test;

import dto.tienda.TiendaDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.Ingreso.TiendaPage;

public class IngresoLoginTest {

    private TiendaPage tiendaPage;

    @BeforeClass
    public void iniciarPrueba() {
        tiendaPage = new TiendaPage();
    }

    @Test
    public void loginTest() {
        tiendaPage.abrirPagina("https://opencart.abstracta.us");
        TiendaDto tiendaDto = new TiendaDto();

        tiendaDto.setEmail("jaiver.agreda020133@gmail.com");
        tiendaDto.setPassword("12345");

        // Pasar el objeto TiendaDto al método de registro
        tiendaPage.login(tiendaDto);
    }

    @AfterClass
    public void cerrarSesion() {
        tiendaPage.cerrarPagina();
    }
}


