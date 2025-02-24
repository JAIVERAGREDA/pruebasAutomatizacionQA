package test;

import dto.tienda.TiendaDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.Ingreso.TiendaPage;

public class BuscarProductoTest {

    private TiendaPage tiendaPage;

    @BeforeClass
    public void iniciarPrueba() {
        tiendaPage = new TiendaPage();
    }

    @Test
    public void realizarCompraTest() {
        tiendaPage.abrirPagina("https://opencart.abstracta.us");
        TiendaDto tiendaDto = new TiendaDto();

        tiendaDto.setEmail("jaiver.agreda020133@gmail.com");
        tiendaDto.setPassword("12345");
        tiendaDto.setBuscador("Samsung Galaxy Tab 10.1");

        // Pasar el objeto TiendaDto al método de registro
        tiendaPage.login(tiendaDto);
        tiendaPage.buscar(tiendaDto);
    }

    @AfterClass
    public void cerrarSesion() {
        tiendaPage.cerrarPagina();
    }
}


