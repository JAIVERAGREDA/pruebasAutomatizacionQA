package page.Ingreso;

import dto.tienda.TiendaDto;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TiendaPage {

    // Se declaran las variables para la prueba
    protected WebDriver driver;
    protected By login = By.xpath("/html/body/nav/div/div[2]/ul/li[2]");
    protected By registrar = By.xpath("//ul[contains(@class,'dropdown-menu')]//a[contains(text(),'Register')]");
    protected By inSesion = By.xpath("/html/body/nav/div/div[2]/ul/li[2]/ul/li[2]/a");
    protected By firstName = By.id("input-firstname");
    protected By secondName = By.id("input-lastname");
    protected By email = By.id("input-email");
    protected By telephone = By.id("input-telephone");
    protected By password = By.id("input-password");
    protected By confPassword = By.id("input-confirm");
    protected By privacity = By.xpath("/html/body/div[2]/div/div/form/div/div/input[1]");
    protected By btnContinue = By.xpath("/html/body/div[2]/div/div/form/div/div/input[2]");
    protected By btnContinueTwo = By.xpath("/html/body/div[2]/div/div/div/div/a");
    protected By btnAceptar = By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/input");
    protected By restablecerCon = By.xpath("/html/body/div[2]/div/div/div/div[2]/div/form/div[2]/a");
    protected By btnContinuar = By.xpath("/html/body/div[2]/div/div/form/div/div[2]/input");
    protected By buscador = By.xpath("/html/body/header/div/div/div[2]/div/input");
    protected By buscadorLaptop = By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/a");
    protected By seleccProducto = By.xpath("/html/body/div[1]/nav/div[2]/ul/li[2]/div/a");
    protected By agregarCarrito = By.xpath("/html/body/div[2]/div/div/div[4]/div[4]/div/div[2]/div[2]/button[1]/span");
    protected By agregarCarrDos = By.xpath("/html/body/div[2]/div/div/div[3]/div/div/div[2]/div[2]/button[1]");
    protected By verificarCarrito = By.xpath("/html/body/header/div/div/div[3]/div/button");
    protected By btnBuscar = By.xpath("/html/body/header/div/div/div[2]/div/span/button");
    protected By btnEliminar = By.xpath("/html/body/header/div/div/div[3]/div/ul/li[1]/table/tbody/tr[1]/td[5]/button");
    protected By btnCompra = By.xpath("/html/body/header/div/div/div[3]/div/ul/li[2]/div/p/a[2]");
    protected By continuarCompra = By.id("button-payment-address");
    protected By continuarCompraDos = By.id("button-shipping-address");
    protected By continuarCompraTres = By.id("button-shipping-method");
    protected By contCompUno = By.id("button-payment-method");
    protected By contCompCheck = By.xpath("/html/body/div[2]/div/div/div/div[5]/div[2]/div/div[3]/div/input[1]");
    protected By btnConfCompra = By.id("button-confirm");

    /** Método para abrir página web */
    public void abrirPagina(String url) {
        // Configurar Chrome con opciones para ignorar errores de seguridad
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-insecure-localhost");

        // Descargar y ejecutar ChromeDriver sin necesidad de instalación manual
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);

        // Esperar hasta que la página termine de cargar
        esperarCargaPagina(10);

        // Manejo del error de seguridad (si aparece)
        try {
            WebElement btnAvanzado = esperarVisibilidad(By.id("details-button"), 5);
            btnAvanzado.click();
            WebElement continuar = esperarVisibilidad(By.id("proceed-link"), 5);
            continuar.click();
        } catch (Exception e) {
            System.out.println("No se encontró la advertencia de seguridad.");
        }
    }

    /** Método para cerrar página web */
    public void cerrarPagina() {
        if (driver != null) {
            driver.quit();
        }
    }

    /** Método para esperar que un elemento sea visible con tiempo personalizado */
    public WebElement esperarVisibilidad(By locator, int tiempoSegundos) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tiempoSegundos));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Método para esperar que un elemento sea clickeable con tiempo personalizado */
    public WebElement esperarClickeable(By locator, int tiempoSegundos) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tiempoSegundos));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /** Método para esperar la carga completa de la página */
    public void esperarCargaPagina(int tiempoSegundos) {
        new WebDriverWait(driver, Duration.ofSeconds(tiempoSegundos))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    /** Método para registrarse*/
    public void registro(TiendaDto tiendaDto) {
        esperarCargaPagina(10);

        WebElement loginElement = esperarClickeable(login, 5);
        loginElement.click();
        WebElement registrarElement = esperarClickeable(registrar, 7);
        registrarElement.click();

        boolean emailRegistrado = true;
        int intentos = 0;

        while (emailRegistrado && intentos < 3) {
            // Ingresar datos
            ingresarTexto(firstName, tiendaDto.getFirstName(), 5);
            ingresarTexto(secondName, tiendaDto.getSecondName(), 5);
            ingresarTexto(email, tiendaDto.getEmail(), 5);
            ingresarTexto(telephone, tiendaDto.getTelephone(), 5);
            ingresarTexto(password, tiendaDto.getPassword(), 5);
            ingresarTexto(confPassword, tiendaDto.getConfPassword(), 5);

            // Aceptar términos y continuar con el registro
            hacerClick(privacity, 5);
            hacerClick(btnContinue, 5);

            // Verificar si aparece el mensaje de error
            try {
                WebElement warning = driver.findElement(By.xpath("//*[contains(text(),'Warning: E-Mail Address is already registered!')]"));
                if (warning.isDisplayed()) {
                    System.out.println("El correo ya está registrado. Generando uno nuevo...");
                    tiendaDto.setEmail(generarNuevoCorreo());
                    intentos++;
                    recargarPagina();
                } else {
                    emailRegistrado = false;
                }
            } catch (NoSuchElementException e) {
                emailRegistrado = false;
            }
        }

        hacerClick(btnContinueTwo, 5);
    }

    // Método para recargar la página (simula volver al formulario de registro)
    private void recargarPagina() {
        driver.navigate().refresh();
        esperarCargaPagina(5);
        WebElement loginElement = esperarClickeable(login, 5);
        loginElement.click();
        WebElement registrarElement = esperarClickeable(registrar, 7);
        registrarElement.click();
    }

    //Método para ingresar con usuario y contraseña
    public void login(TiendaDto tiendaDto) {
        esperarCargaPagina(10);

        WebElement loginElement = esperarClickeable(login, 5);
        loginElement.click();
        WebElement loginInitElement = esperarClickeable(inSesion, 7);
        loginInitElement.click();

        boolean credencialesIncorrectas = true;
        int intentos = 0;

        while (credencialesIncorrectas && intentos < 3) {
            ingresarTexto(email, tiendaDto.getEmail(), 5);
            ingresarTexto(password, tiendaDto.getPassword(), 5);
            WebElement aceptarElement = esperarClickeable(btnAceptar, 5);
            aceptarElement.click();

            // Verificar si aparece el mensaje de error de credenciales incorrectas
            try {
                WebElement errorMsg = driver.findElement(By.xpath("//*[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"));
                if (errorMsg.isDisplayed()) {
                    System.out.println("Contraseña incorrecta. Reintentando...");
                    tiendaDto.setPassword(obtenerNuevaContrasena()); // Método para obtener la nueva contraseña
                    intentos++;
                } else {
                    credencialesIncorrectas = false;
                }
            } catch (NoSuchElementException e) {
                credencialesIncorrectas = false;
            }
        }
    }

    public void restablecerContrasena(TiendaDto tiendaDto) {
        esperarCargaPagina(10);

        WebElement loginElement = esperarClickeable(login, 5);
        loginElement.click();
        WebElement loginInitElement = esperarClickeable(inSesion, 7);
        loginInitElement.click();
        WebElement restablecerPassword = esperarClickeable(restablecerCon, 5);
        restablecerPassword.click();

        boolean restablecido = false;
        int intentos = 0;

        while (!restablecido && intentos < 3) { // Intentar hasta 3 veces
            ingresarTexto(email, tiendaDto.getEmail(), 5);
            WebElement btnContinuarr = esperarClickeable(btnContinuar, 5);
            btnContinuarr.click();

            // Verificar si el mensaje de éxito aparece
            try {
                WebElement mensajeExito = driver.findElement(By.xpath("//*[contains(text(),'An email with a confirmation link has been sent')]"));
                if (mensajeExito.isDisplayed()) {
                    System.out.println("Restablecimiento de contraseña exitoso.");
                    restablecido = true;
                    break;
                }
            } catch (NoSuchElementException e) {
                System.out.println("No se encontró el mensaje de éxito, verificando errores...");
            }

            // Verificar si aparece el mensaje de error
            try {
                WebElement mensajeError = driver.findElement(By.xpath("//*[contains(text(),'Warning: The E-Mail Address was not found in our records')]"));
                if (mensajeError.isDisplayed()) {
                    System.out.println("El correo no está registrado. Reintentando con otro...");
                    tiendaDto.setEmail(generarNuevoCorreo()); // Genera un nuevo correo
                    intentos++;
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("No se encontró el mensaje de error.");
            }
        }

        if (!restablecido) {
            System.out.println("No se pudo restablecer la contraseña después de 3 intentos.");
        }
    }


    public void buscar(TiendaDto tiendaDto) {
        WebElement laptop = esperarClickeable(buscadorLaptop, 5);
        laptop.click();
        WebElement seleccionarProducto = esperarClickeable(seleccProducto, 5);
        seleccionarProducto.click();
        WebElement carrito = esperarClickeable(agregarCarrito, 10);
        carrito.click();

        ingresarTexto(buscador, tiendaDto.getBuscador(), 5);
        esperar(2000);

        WebElement botonBuscar = esperarClickeable(btnBuscar, 10);
        botonBuscar.click();

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        esperar(2000);

        WebElement carritoDos = esperarClickeable(agregarCarrDos, 10);
        carritoDos.click();
        esperar(2000);

        WebElement verificar = esperarClickeable(verificarCarrito, 10);
        verificar.click();
        esperar(2000);

        WebElement botonEliminar = esperarClickeable(btnEliminar, 10);
        botonEliminar.click();
        esperar(2000);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        esperar(2000);

        WebElement carritoTres = esperarClickeable(agregarCarrDos, 10);
        carritoTres.click();
        esperar(2000);

        WebElement verificarDos = esperarClickeable(verificarCarrito, 10);
        verificarDos.click();
        esperar(2000);

        WebElement comprar = esperarClickeable(btnCompra, 10);
        comprar.click();
        esperar(2000);

        WebElement continuarUno = esperarClickeable(continuarCompra, 10);
        continuarUno.click();
        esperar(2000);

        WebElement continuarDos = esperarClickeable(continuarCompraDos, 10);
        continuarDos.click();
        esperar(2000);

        WebElement continuarTres = esperarClickeable(continuarCompraTres, 10);
        continuarTres.click();
        esperar(2000);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
        WebElement continuarCheck = esperarClickeable(contCompCheck, 10);
        continuarCheck.click();
        esperar(2000);

        WebElement continuarCuatro = esperarClickeable(contCompUno, 10);
        continuarCuatro.click();
        esperar(2000);

        WebElement botonComprar = esperarClickeable(btnConfCompra, 10);
        botonComprar.click();
    }


    /**-----------------------------------*/
    // Método para obtener una nueva contraseña
    private String obtenerNuevaContrasena() {
        return "12345";
    }

    /** Método para hacer clic en elementos con manejo de excepciones */
    public void hacerClick(By locator, int tiempoSegundos) {
        try {
            WebElement elemento = esperarClickeable(locator, tiempoSegundos);
            elemento.click();
        } catch (Exception e) {
            System.out.println("No se pudo hacer clic en el elemento: " + e.getMessage());
        }
    }

    /** Método para ingresar texto en un campo con espera */
    public void ingresarTexto(By locator, String texto, int tiempoSegundos) {
        try {
            WebElement campo = esperarVisibilidad(locator, tiempoSegundos);
            campo.clear();
            campo.sendKeys(texto);
        } catch (Exception e) {
            System.out.println("No se pudo ingresar texto en el campo: " + e.getMessage());
        }
    }

    // Método para generar un nuevo correo
    private String generarNuevoCorreo() {
        return "jaiver.agreda020133@gmail.com";
    }

    /**
     * Método para pausar la ejecución por el tiempo indicado.
     * @param milisegundos
     */
    private void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
