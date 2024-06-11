//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.TipoUsuarioDTO;
import es.uma.proyectotaw.dto.UsuarioDTO;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.entity.Usuario;
import es.uma.proyectotaw.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EjercicioRepository ejercicioRepository;
    @Autowired
    private TipoEjercicioRepository tipoEjercicioRepository;
    @Autowired
    private GrupoMuscularRepository grupoMuscularRepository;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private EjercicioEntrenamientoRepository ejercicioEntrenamientoRepository;
    @Autowired
    private EntrenamientoRepository entrenamientoRepository;
    @Autowired
    private AdminService adminService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS USUARIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/usuarios")
    public String login(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List<Integer> edades = adminService.sacarEdades();
        List<Integer> ingresos = adminService.sacarIngresos();
        List<TipoUsuarioDTO> rolesUsuarios = adminService.sacarRoles();

        model.addAttribute("edades", edades);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("roles", rolesUsuarios);

        //------------ PARA RELLENAR LA TABLA DE USUARIOS (sin filtro)------------//
        List<UsuarioDTO> usuariosCompleto = adminService.sacarUsuarios();
        model.addAttribute("usuarios", usuariosCompleto);

        return "administrador/usuarios";
    }

    @GetMapping("/usuarios/filtrar")
    public String Usuariofiltrar(@RequestParam("inputNombre") String inputNombre, @RequestParam("inputApellidos") String inputApellidos,
                                @RequestParam("StringEdad") String StringEdad, @RequestParam("StringIngreso") String StringIngreso,
                                @RequestParam("StringRol") String StringRol, Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO (igual que en /usuarios)------------//
        List<Integer> edades = usuarioRepository.sacarEdades(); //Esta consulta nos devuelve todas las edades, ordenadas y sin repetir listas para usar
        List<Integer> ingresos = usuarioRepository.sacarIngresos(); //Esta consulta nos devuelve todos los años, ordenados y sin repetir listos para usar
        List<Usuario> usuarios = usuarioRepository.sacarUsuarios(); //Esta consulta solo nos devuelve los usuarios, y queremos una lista de los roles
        List<TipoUsuario> rolesUsuarios = usuarioRepository.sacarRoles(); //Esta consulta nos devuelve los roles de usuario que se usan ahora

        model.addAttribute("edades", edades);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("roles", rolesUsuarios);

        //------------ PARA RELLENAR LA TABLA (con filtros)------------//

        //Esto con un input type number podemos solucionarlo de otra forma, pero aquí hemos elegido hacer un select. Tenemos ejemplos en este Controller usando type number.
        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Integer inputEdad;
        if( StringEdad.equals("Selecciona Edad") ){
            inputEdad = null;
        } else {
            inputEdad = Integer.parseInt(StringEdad);
        }

        //Esto con un input type number podemos solucionarlo de otra forma, pero aquí hemos elegido hacer un select. Tenemos ejemplos en este Controller usando type number.
        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Integer inputIngreso;
        if( StringIngreso.equals("Selecciona Año de Ingreso") ){
            inputIngreso = null;
        } else {
            inputIngreso = Integer.parseInt(StringIngreso);
        }

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        TipoUsuario inputRol;
        if( StringRol.equals("Selecciona Rol") ){
            inputRol = null;
        } else {
            inputRol = tipoUsuarioRepository.buscarPorString(StringRol);
        }

        List<Usuario> usuariosFiltrado = usuarioRepository.filtrarUsuarios(inputNombre, inputApellidos, inputEdad, inputIngreso, inputRol);
        model.addAttribute("usuarios", usuariosFiltrado);

        return "administrador/usuarios";
    }

    @GetMapping("/usuarios/seleccionar")
    public String seleccionarUsuarios(@RequestParam(name = "uSeleccionado", required = false) Integer inputUsr, @RequestParam("Boton") String inputBoton, Model model) {

        //Si sí se selecciona y se pulsa editar, se va a la página correspondiente.
        //Si sí se selecciona y se pulsa borrar, se ejecuta la sentencia y se permanece en la página.
        //Independientemente si se selecciona o no un usuario y se pulsa añadir un usuario, llevará a la página correspondiente.
        String direccionRetorno = "redirect:/admin/usuarios";

        List<Usuario> usuarios = usuarioRepository.sacarUsuarios();
        List<TipoUsuario> rolesUsuarios = tipoUsuarioRepository.findAll();

        model.addAttribute("roles", rolesUsuarios);
        model.addAttribute("usuario", usuarioRepository.buscarPorID(inputUsr));

        if (inputBoton.equals("Añadir")) {
            direccionRetorno = "administrador/nuevoUsuario";
        } else if (inputUsr != null){
            if (inputBoton.equals("Eliminar")){
                usuarioRepository.deleteById(inputUsr);
            } else if (inputBoton.equals("Modificar")){
                direccionRetorno = "administrador/modificarUsuario";
            }
        }
        return direccionRetorno;
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuarios(Model model, @RequestParam("usuarioId") Integer usuarioId,
                                    @RequestParam("inputEmail") String inputEmail, @RequestParam("inputNombre") String inputNombre,
                                    @RequestParam("inputApellidos") String inputApellidos, @RequestParam("inputNacimiento") LocalDate inputNacimiento,
                                    @RequestParam("inputIngreso") LocalDate inputIngreso, @RequestParam("inputRol") String inputRol){

        Usuario usr = usuarioRepository.buscarPorID(usuarioId);
        usr.setNombre(inputNombre);
        usr.setApellidos(inputApellidos);
        usr.setEmail(inputEmail);
        usr.setFechaNacimiento(inputNacimiento);
        usr.setPerteneceDesde(inputIngreso);
        TipoUsuario nuevoRol = tipoUsuarioRepository.buscarPorString(inputRol);
        usr.setTipoUsuario(nuevoRol);
        //En principio no consideramos que el administrador pueda cambiar la contraseña del usuario no?

        usuarioRepository.save(usr);

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuarios(Model model,
                          @RequestParam("inputEmail") String inputEmail,
                          @RequestParam("inputNombre") String inputNombre,
                          @RequestParam("inputApellidos") String inputApellidos,
                          @RequestParam("inputNacimiento") LocalDate inputNacimiento,
                          @RequestParam("inputIngreso") LocalDate inputIngreso,
                          @RequestParam("inputRol") String inputRol,
                          @RequestParam("inputContraseña") String inputContraseña){

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(inputNombre);
        nuevoUsuario.setApellidos(inputApellidos);
        nuevoUsuario.setEmail(inputEmail);
        nuevoUsuario.setFechaNacimiento(inputNacimiento);
        nuevoUsuario.setPerteneceDesde(inputIngreso);
        nuevoUsuario.setPassword(inputContraseña);
        TipoUsuario nuevoRol = tipoUsuarioRepository.buscarPorString(inputRol);
        nuevoUsuario.setTipoUsuario(nuevoRol);

        usuarioRepository.save(nuevoUsuario);

        return "redirect:/admin/usuarios";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ASIGNACIÓN DE CLIENTES A ENTRENADORES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/clientesEntrenadores")
    public String clientesEntrenadores(Model model) {

        TipoUsuario tipoCliente = tipoUsuarioRepository.buscarPorID(5);
        List<Usuario> clientes = usuarioRepository.buscarPorTipo(tipoCliente);

        TipoUsuario tipoBodubuilder = tipoUsuarioRepository.buscarPorID(2);
        List<Usuario> bodybuilders = usuarioRepository.buscarPorTipo(tipoBodubuilder);

        TipoUsuario tipoCrosstrainer = tipoUsuarioRepository.buscarPorID(3);
        List<Usuario> crosstrainers = usuarioRepository.buscarPorTipo(tipoCrosstrainer);

        List<Usuario> entrenadores = new ArrayList<>();
        entrenadores.addAll(bodybuilders);
        entrenadores.addAll(crosstrainers);

        model.addAttribute("clientes", clientes);
        model.addAttribute("entrenadores", entrenadores);

        return "administrador/clientesEntrenadores";
    }

    @GetMapping("/clientesEntrenadores/asignar")
    public String clientesEntrenadoresAsignar(@RequestParam(required = false, name = "clienteSeleccionado") Integer clienteId,
                                              @RequestParam(required = false, name = "entrenadorSeleccionado") Integer entrenadorId,
                                              Model model) {

        //El control de si estos valores son nulos también podría hacerse con <Optional> y .isPresent()
        if (clienteId != null && entrenadorId != null) {
            Cliente cliente = clienteRepository.getClienteByUserId(clienteId);
            Usuario entrenador = usuarioRepository.buscarPorID(entrenadorId);

            cliente.setEntrenador(entrenador);

            clienteRepository.save(cliente);
        }

        return "redirect:/admin/clientesEntrenadores";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ASIGNACIÓN DE CLIENTES A DIETISTAS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/clientesDietistas")
    public String clientesDietistas(Model model) {

        TipoUsuario tipoCliente = tipoUsuarioRepository.buscarPorID(5);
        List<Usuario> clientes = usuarioRepository.buscarPorTipo(tipoCliente);

        TipoUsuario tipoDietista = tipoUsuarioRepository.buscarPorID(4);
        List<Usuario> dietistas = usuarioRepository.buscarPorTipo(tipoDietista);

        model.addAttribute("clientes", clientes);
        model.addAttribute("dietistas", dietistas);

        return "administrador/clientesDietistas";
    }

    @GetMapping("/clientesDietistas/asignar")
    public String clientesDietistasAsignar(@RequestParam(required = false, name = "clienteSeleccionado") Integer clienteId,
                                              @RequestParam(required = false, name = "dietistaSeleccionado") Integer dietistaId,
                                              Model model) {

        //El control de si estos valores son nulos también podría hacerse con <Optional> y .isPresent()
        if (clienteId != null && dietistaId != null) {
            Cliente cliente = clienteRepository.getClienteByUserId(clienteId);
            Usuario dietista = usuarioRepository.buscarPorID(dietistaId);

            cliente.setDietista(dietista);

            clienteRepository.save(cliente);
        }

        return "redirect:/admin/clientesDietistas";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS EJERCICIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/ejercicios")
    public String ejercicios(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List <TipoEjercicio> tiposEjercicio = tipoEjercicioRepository.findAll();
        List <GrupoMuscular> gruposMusculares = grupoMuscularRepository.findAll();

        model.addAttribute("tiposEjercicio", tiposEjercicio);
        model.addAttribute("gruposMusculares", gruposMusculares);

        //------------ PARA RELLENAR LA TABLA DE USUARIOS (sin filtro)------------//
        List<Ejercicio> ejerciciosCompleto = ejercicioRepository.findAll();
        model.addAttribute("ejercicios", ejerciciosCompleto);

        return "/administrador/ejercicios";
    }

    @GetMapping("/ejercicios/filtrar")
    public String Ejerciciofiltrar(@RequestParam("inputNombre") String inputNombre, @RequestParam("StringGrupo") String StringGrupo,
                                   @RequestParam("StringTipo") String StringTipo, Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List <TipoEjercicio> tiposEjercicio = tipoEjercicioRepository.findAll();
        List <GrupoMuscular> gruposMusculares = grupoMuscularRepository.findAll();

        model.addAttribute("tiposEjercicio", tiposEjercicio);
        model.addAttribute("gruposMusculares", gruposMusculares);

        //------------ PARA RELLENAR LA TABLA (con filtros)------------//

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        GrupoMuscular inputGrupo;
        if(StringGrupo.equals("Selecciona Grupo Muscular") ){
            inputGrupo = null;
        } else {
            inputGrupo = grupoMuscularRepository.buscarPorString(StringGrupo);
        }

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        TipoEjercicio inputTipo;
        if(StringTipo.equals("Selecciona Tipo de Ejercicio") ){
            inputTipo = null;
        } else {
            inputTipo = tipoEjercicioRepository.buscarPorString(StringTipo);
        }

        List<Ejercicio> ejerciciosFiltrado = ejercicioRepository.filtrarEjercicios(inputNombre, inputTipo, inputGrupo);
        model.addAttribute("ejercicios", ejerciciosFiltrado);

        return "administrador/ejercicios";
    }

    @GetMapping("/ejercicios/seleccionar")
    public String seleccionarEjercicios(@RequestParam(name = "eSeleccionado", required = false) Integer inputEj, @RequestParam("Boton") String inputBoton, Model model) {

        //Si sí se selecciona y se pulsa editar, se va a la página correspondiente.
        //Si sí se selecciona y se pulsa borrar, se ejecuta la sentencia y se permanece en la página.
        //Independientemente si se selecciona o no un ejercicio y se pulsa añadir, llevará a la página correspondiente.
        String direccionRetorno = "redirect:/admin/ejercicios";

        List <TipoEjercicio> tiposEjercicio = tipoEjercicioRepository.findAll();
        List <GrupoMuscular> gruposMusculares = grupoMuscularRepository.findAll();

        model.addAttribute("tiposEjercicio", tiposEjercicio);
        model.addAttribute("gruposMusculares", gruposMusculares);

        if (inputBoton.equals("Añadir")) {
            Ejercicio ejercicio = new Ejercicio();
            model.addAttribute("ejercicio", ejercicio);
            direccionRetorno = "administrador/nuevoEjercicio";
        } else if (inputEj != null){
            if (inputBoton.equals("Eliminar")){
                ejercicioRepository.deleteById(inputEj);
            } else if (inputBoton.equals("Modificar")){
                Ejercicio ejercicio = ejercicioRepository.getById(inputEj);
                model.addAttribute("ejercicio", ejercicio);
                direccionRetorno = "administrador/modificarEjercicio";
            }
        }
        return direccionRetorno;
    }

    @PostMapping("/ejercicios/actualizar")
    public String actualizarEjercicios(Model model, @ModelAttribute("ejercicio") Ejercicio ejercicio){

        Ejercicio nuevoEj = ejercicioRepository.findById(ejercicio.getId()).orElse(new Ejercicio());
        nuevoEj.setNombre(ejercicio.getNombre());
        nuevoEj.setDescripcion(ejercicio.getDescripcion());
        nuevoEj.setUrlVideo(ejercicio.getUrlVideo());
        nuevoEj.setTipo(ejercicio.getTipo());
        nuevoEj.setGrupoMuscular(ejercicio.getGrupoMuscular());

        ejercicioRepository.save(nuevoEj);

        return "redirect:/admin/ejercicios";
    }

    @PostMapping("/ejercicios/guardar")
    public String guardarEjercicios(Model model, @ModelAttribute("ejercicio") Ejercicio ejercicio){

        ejercicioRepository.save(ejercicio);

        return "redirect:/admin/ejercicios";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS MENÚS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/menus")
    public String menus(Model model) {

        //------------ PARA RELLENAR LA TABLA DE USUARIOS (sin filtro)------------//
        List<Menu> menusCompleto = menuRepository.findAll();
        model.addAttribute("menus", menusCompleto);

        return "/administrador/menus";
    }

    @GetMapping("/menus/filtrar")
    public String Menufiltrar(@RequestParam("inputNombre") String inputNombre,
                              @RequestParam("inputAlergenos") String inputAlergenos, Model model) {

        //------------ PARA RELLENAR LA TABLA (con filtros)------------//

        List<Menu> menusFiltrado = menuRepository.filtrarMenus(inputNombre, inputAlergenos);
        model.addAttribute("menus", menusFiltrado);

        return "administrador/menus";
    }

    @GetMapping("/menus/seleccionar")
    public String seleccionarMenus(@RequestParam(name = "mSeleccionado", required = false) Integer inputMenu, @RequestParam("Boton") String inputBoton, Model model) {

        //Si sí se selecciona y se pulsa editar, se va a la página correspondiente.
        //Si sí se selecciona y se pulsa borrar, se ejecuta la sentencia y se permanece en la página.
        //Independientemente si se selecciona o no un menú y se pulsa añadir, llevará a la página correspondiente.
        String direccionRetorno = "redirect:/admin/menus";

        if (inputBoton.equals("Añadir")) {
            Menu menu = new Menu();
            model.addAttribute("menu", menu);
            direccionRetorno = "administrador/nuevoMenu";
        } else if (inputMenu != null){
            if (inputBoton.equals("Eliminar")){
                menuRepository.deleteById(inputMenu);
            } else if (inputBoton.equals("Modificar")){
                Menu menu = menuRepository.getById(inputMenu);
                model.addAttribute("menu", menu);
                direccionRetorno = "administrador/modificarMenu";
            }
        }
        return direccionRetorno;
    }

    @PostMapping("/menus/actualizar")
    public String actualizarMenus(Model model, @ModelAttribute("menu") Menu menu){

        Menu nuevoMenu = menuRepository.findById(menu.getId()).orElse(new Menu());
        nuevoMenu.setNombre(menu.getNombre());
        nuevoMenu.setDescripcion(menu.getDescripcion());
        nuevoMenu.setAlergenos(menu.getAlergenos());

        menuRepository.save(nuevoMenu);

        return "redirect:/admin/menus";
    }

    @PostMapping("/menus/guardar")
    public String guardarMenus(Model model, @ModelAttribute("menu") Menu menu){

        menuRepository.save(menu);

        return "redirect:/admin/menus";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE DIMENSIONES DE EJERCICIOS (TABLA EJERCICIO_ENTRENAMIENTO)
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/ejerciciosEntrenamientos")
    public String ejerciciosEntrenamientos(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List <Ejercicio> ejercicios = ejercicioRepository.findAll();
        List <Entrenamiento> entrenamientos = entrenamientoRepository.findAll();

        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("entrenamientos", entrenamientos);

        //------------ PARA RELLENAR LA TABLA DE DIMENSIONES DE EJERCICIOS (sin filtro)------------//
        List<EjercicioEntrenamiento> ejerciciosEntrenamientosCompleto = ejercicioEntrenamientoRepository.findAll();
        model.addAttribute("ejerciciosEntrenamientos", ejerciciosEntrenamientosCompleto);

        return "/administrador/ejerciciosEntrenamientos";
    }

    @GetMapping("/ejerciciosEntrenamientos/filtrar")
    public String EjerciciosEntrenamientosfiltrar(
            @RequestParam(value = "inputRepeticiones", required = false) Integer inputRepeticiones,
            @RequestParam(value = "inputPeso", required = false) Integer inputPeso,
            @RequestParam(value = "inputTiempo", required = false) Integer inputTiempo,
            @RequestParam(value = "inputDistancia", required = false) Integer inputDistancia,
            @RequestParam(value = "inputOrden", required = false) Integer inputOrden,
            @RequestParam(value = "StringEntrenamiento", required = false) String StringEntrenamiento,
            @RequestParam(value = "StringEjercicio", required = false) String StringEjercicio,
            @RequestParam(value = "inputSeries", required = false) Integer inputSeries,
            Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List <Ejercicio> ejercicios = ejercicioRepository.findAll();
        List <Entrenamiento> entrenamientos = entrenamientoRepository.findAll();

        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("entrenamientos", entrenamientos);

        //------------ PARA RELLENAR LA TABLA (con filtros)------------//

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Ejercicio inputEjercicio;
        if(StringEntrenamiento.equals("Selecciona Ejercicio") ){
            inputEjercicio = null;
        } else {
            inputEjercicio = ejercicioRepository.buscarPorString(StringEjercicio);
        }

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Entrenamiento inputEntrenamiento;
        if(StringEntrenamiento.equals("Selecciona Entrenamiento") ){
            inputEntrenamiento = null;
        } else {
            inputEntrenamiento = entrenamientoRepository.buscarPorString(StringEntrenamiento);
        }

        List<EjercicioEntrenamiento> ejerciciosEntrenamientoFiltrado = ejercicioEntrenamientoRepository.filtrarEjerciciosEntrenamiento(inputEjercicio, inputEntrenamiento, inputSeries, inputRepeticiones, inputPeso, inputTiempo, inputDistancia, inputOrden);
        model.addAttribute("ejerciciosEntrenamientos", ejerciciosEntrenamientoFiltrado);

        return "administrador/ejerciciosEntrenamientos";
    }

    @GetMapping("/ejerciciosEntrenamientos/seleccionar")
    public String seleccionarEjerciciosEntrenamiento(@RequestParam(name = "eSeleccionado", required = false) Integer inputEj, @RequestParam("Boton") String inputBoton, Model model) {

        //Si sí se selecciona y se pulsa editar, se va a la página correspondiente.
        //Si sí se selecciona y se pulsa borrar, se ejecuta la sentencia y se permanece en la página.
        //Independientemente si se selecciona o no un ejercicioEntrenamiento y se pulsa añadir, llevará a la página correspondiente.
        String direccionRetorno = "redirect:/admin/ejerciciosEntrenamientos";

        List <Ejercicio> ejercicios = ejercicioRepository.findAll();
        List <Entrenamiento> entrenamientos = entrenamientoRepository.findAll();

        model.addAttribute("ejercicios", ejercicios);
        model.addAttribute("entrenamientos", entrenamientos);

        if (inputBoton.equals("Añadir")) {
            EjercicioEntrenamiento nuevo = new EjercicioEntrenamiento();
            model.addAttribute("ejercicioEntrenamiento", nuevo);
            direccionRetorno = "administrador/nuevoEjercicioEntrenamiento";
        } else if (inputEj != null){
            if (inputBoton.equals("Eliminar")){
                ejercicioEntrenamientoRepository.deleteById(inputEj);
            } else if (inputBoton.equals("Modificar")){
                EjercicioEntrenamiento ejercicioEntrenamiento = ejercicioEntrenamientoRepository.getById(inputEj);
                model.addAttribute("ejercicioEntrenamiento", ejercicioEntrenamiento);
                direccionRetorno = "administrador/modificarEjercicioEntrenamiento";
            }
        }
        return direccionRetorno;
    }

    //En este caso, hemos hecho que tanto el modificar como el añadir se manejen en este método
    @PostMapping("/ejerciciosEntrenamientos/guardar")
    public String guardarEjerciciosEntrenamientos(Model model, @ModelAttribute("ejercicioEntrenamiento") EjercicioEntrenamiento ejercicioEntrenamiento){

        ejercicioEntrenamientoRepository.save(ejercicioEntrenamiento);

        return "redirect:/admin/ejerciciosEntrenamientos";
    }

}
