//Autor: Álvaro Valencia Vilallón
package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.dto.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.entity.Usuario;
import es.uma.proyectotaw.service.*;
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
    @Autowired
    private TipoUsuarioService tipoUsuarioService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TipoEjercicioService tipoEjercicioService;
    @Autowired
    private GrupoMuscularService grupoMuscularService;
    @Autowired
    private EjercicioService ejercicioService;
    @Autowired
    private MenuService menuService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS USUARIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/usuarios")
    public String login(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List<Integer> edades = adminService.sacarEdades();
        List<Integer> ingresos = adminService.sacarIngresos();
        List<TipoUsuarioDTO> rolesUsuarios = tipoUsuarioService.sacarRoles();

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
        List<Integer> edades = adminService.sacarEdades();
        List<Integer> ingresos = adminService.sacarIngresos();
        List<TipoUsuarioDTO> rolesUsuarios = tipoUsuarioService.sacarRoles();

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
        Integer inputRol;
        if( StringRol.equals("Selecciona Rol") ){
            inputRol = null;
        } else {
            inputRol = tipoUsuarioService.buscarRolPorString(StringRol);
        }

        List<UsuarioDTO> usuariosFiltrado = adminService.filtrarUsuarios(inputNombre, inputApellidos, inputEdad, inputIngreso, inputRol);
        model.addAttribute("usuarios", usuariosFiltrado);

        return "administrador/usuarios";
    }

    @GetMapping("/usuarios/seleccionar")
    public String seleccionarUsuarios(@RequestParam(name = "uSeleccionado", required = false) Integer inputUsr, @RequestParam("Boton") String inputBoton, Model model) {

        //Si sí se selecciona y se pulsa editar, se va a la página correspondiente.
        //Si sí se selecciona y se pulsa borrar, se ejecuta la sentencia y se permanece en la página.
        //Independientemente si se selecciona o no un usuario y se pulsa añadir un usuario, llevará a la página correspondiente.
        String direccionRetorno = "redirect:/admin/usuarios";

        List<TipoUsuarioDTO> rolesUsuarios = tipoUsuarioService.sacarRolesComleto();

        model.addAttribute("roles", rolesUsuarios);
        model.addAttribute("usuario", usuarioService.buscarUsuarioPorId(inputUsr));

        if (inputBoton.equals("Añadir")) {
            direccionRetorno = "administrador/nuevoUsuario";
        } else if (inputUsr != null){
            if (inputBoton.equals("Eliminar")){
                adminService.eliminarUsuario(inputUsr);
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

        UsuarioDTO usr = usuarioService.buscarUsuarioPorId(usuarioId);
        usr.setNombre(inputNombre);
        usr.setApellidos(inputApellidos);
        usr.setEmail(inputEmail);
        usr.setFechaNacimiento(inputNacimiento);
        usr.setPerteneceDesde(inputIngreso);
        Integer nuevoRolId = tipoUsuarioService.buscarRolPorString(inputRol);
        TipoUsuarioDTO nuevoRol = tipoUsuarioService.buscarRolPorId(nuevoRolId);
        usr.setTipoUsuario(nuevoRol);
        //En principio no consideramos que el administrador pueda cambiar la contraseña del usuario no?

        adminService.guardarUsuario(usr);

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

        UsuarioDTO nuevoUsuario = new UsuarioDTO();
        nuevoUsuario.setNombre(inputNombre);
        nuevoUsuario.setApellidos(inputApellidos);
        nuevoUsuario.setEmail(inputEmail);
        nuevoUsuario.setFechaNacimiento(inputNacimiento);
        nuevoUsuario.setPerteneceDesde(inputIngreso);
        nuevoUsuario.setPassword(inputContraseña);
        Integer nuevoRolId = tipoUsuarioService.buscarRolPorString(inputRol);
        TipoUsuarioDTO nuevoRol = tipoUsuarioService.buscarRolPorId(nuevoRolId);
        nuevoUsuario.setTipoUsuario(nuevoRol);

        adminService.guardarUsuario(nuevoUsuario);

        return "redirect:/admin/usuarios";
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ASIGNACIÓN DE CLIENTES A ENTRENADORES
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/clientesEntrenadores")
    public String clientesEntrenadores(Model model) {

        TipoUsuarioDTO tipoCliente = tipoUsuarioService.buscarRolPorId(5);
        List<UsuarioDTO> clientes = usuarioService.sacarUsuariosPorTipo(tipoCliente);

        TipoUsuarioDTO tipoBodubuilder = tipoUsuarioService.buscarRolPorId(2);
        List<UsuarioDTO> bodybuilders = usuarioService.sacarUsuariosPorTipo(tipoBodubuilder);

        TipoUsuarioDTO tipoCrosstrainer = tipoUsuarioService.buscarRolPorId(3);
        List<UsuarioDTO> crosstrainers = usuarioService.sacarUsuariosPorTipo(tipoCrosstrainer);

        List<UsuarioDTO> entrenadores = new ArrayList<>();
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
            ClienteDTO cliente = clienteService.getClienteByUserId(clienteId);
            UsuarioDTO entrenador = usuarioService.buscarUsuarioPorId(entrenadorId);

            cliente.setEntrenador(entrenador);

            clienteService.guardarCliente(cliente);
        }

        return "redirect:/admin/clientesEntrenadores";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //ASIGNACIÓN DE CLIENTES A DIETISTAS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/clientesDietistas")
    public String clientesDietistas(Model model) {

        TipoUsuarioDTO tipoCliente = tipoUsuarioService.buscarRolPorId(5);
        List<UsuarioDTO> clientes = usuarioService.sacarUsuariosPorTipo(tipoCliente);

        TipoUsuarioDTO tipoDietista = tipoUsuarioService.buscarRolPorId(4);
        List<UsuarioDTO> dietistas = usuarioService.sacarUsuariosPorTipo(tipoDietista);

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
            ClienteDTO cliente = clienteService.getClienteByUserId(clienteId);
            UsuarioDTO dietista = usuarioService.buscarUsuarioPorId(dietistaId);

            cliente.setDietista(dietista);

            clienteService.guardarCliente(cliente);
        }

        return "redirect:/admin/clientesDietistas";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS EJERCICIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/ejercicios")
    public String ejercicios(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List <TipoEjercicioDTO> tiposEjercicio = tipoEjercicioService.findAll();
        List <GrupoMuscularDTO> gruposMusculares = grupoMuscularService.findAll();

        model.addAttribute("tiposEjercicio", tiposEjercicio);
        model.addAttribute("gruposMusculares", gruposMusculares);

        //------------ PARA RELLENAR LA TABLA DE USUARIOS (sin filtro)------------//
        List<EjercicioDTO> ejerciciosCompleto = ejercicioService.findAll();
        model.addAttribute("ejercicios", ejerciciosCompleto);

        return "/administrador/ejercicios";
    }

    @GetMapping("/ejercicios/filtrar")
    public String Ejerciciofiltrar(@RequestParam("inputNombre") String inputNombre, @RequestParam("StringGrupo") String StringGrupo,
                                   @RequestParam("StringTipo") String StringTipo, Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List <TipoEjercicioDTO> tiposEjercicio = tipoEjercicioService.findAll();
        List <GrupoMuscularDTO> gruposMusculares = grupoMuscularService.findAll();

        model.addAttribute("tiposEjercicio", tiposEjercicio);
        model.addAttribute("gruposMusculares", gruposMusculares);

        //------------ PARA RELLENAR LA TABLA (con filtros)------------//

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Integer inputGrupo;
        if(StringGrupo.equals("Selecciona Grupo Muscular") ){
            inputGrupo = null;
        } else {
            inputGrupo = grupoMuscularService.buscarPorString(StringGrupo);
        }

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Integer inputTipo;
        if(StringTipo.equals("Selecciona Tipo de Ejercicio") ){
            inputTipo = null;
        } else {
            inputTipo = tipoEjercicioService.buscarPorString(StringTipo);
        }

        List<EjercicioDTO> ejerciciosFiltrado = ejercicioService.filtrarEjercicios(inputNombre, inputTipo, inputGrupo);
        model.addAttribute("ejercicios", ejerciciosFiltrado);

        return "administrador/ejercicios";
    }

    @GetMapping("/ejercicios/seleccionar")
    public String seleccionarEjercicios(@RequestParam(name = "eSeleccionado", required = false) Integer inputEj, @RequestParam("Boton") String inputBoton, Model model) {

        //Si sí se selecciona y se pulsa editar, se va a la página correspondiente.
        //Si sí se selecciona y se pulsa borrar, se ejecuta la sentencia y se permanece en la página.
        //Independientemente si se selecciona o no un ejercicio y se pulsa añadir, llevará a la página correspondiente.
        String direccionRetorno = "redirect:/admin/ejercicios";

        List <TipoEjercicioDTO> tiposEjercicio = tipoEjercicioService.findAll();
        List <GrupoMuscularDTO> gruposMusculares = grupoMuscularService.findAll();

        model.addAttribute("tiposEjercicio", tiposEjercicio);
        model.addAttribute("gruposMusculares", gruposMusculares);

        if (inputBoton.equals("Añadir")) {
            EjercicioDTO ejercicio = new EjercicioDTO();
            model.addAttribute("ejercicioDTO", ejercicio);
            direccionRetorno = "administrador/nuevoEjercicio";
        } else if (inputEj != null){
            if (inputBoton.equals("Eliminar")){
                ejercicioService.deleteById(inputEj);
            } else if (inputBoton.equals("Modificar")){
                EjercicioDTO ejercicio = ejercicioService.findById(inputEj);
                model.addAttribute("ejercicio", ejercicio);
                direccionRetorno = "administrador/modificarEjercicio";
            }
        }
        return direccionRetorno;
    }

    @PostMapping("/ejercicios/actualizar")
    public String actualizarEjercicios(Model model, @ModelAttribute("ejercicio") EjercicioDTO ejercicio){

        EjercicioDTO nuevoEj = ejercicioService.findById(ejercicio.getId());
        nuevoEj.setNombre(ejercicio.getNombre());
        nuevoEj.setDescripcion(ejercicio.getDescripcion());
        nuevoEj.setUrlVideo(ejercicio.getUrlVideo());
        nuevoEj.setTipo(ejercicio.getTipo());
        nuevoEj.setGrupoMuscular(ejercicio.getGrupoMuscular());

        ejercicioService.save(nuevoEj);

        return "redirect:/admin/ejercicios";
    }

    @PostMapping("/ejercicios/guardar")
    public String guardarEjercicios(Model model, @ModelAttribute("ejercicio") EjercicioDTO ejercicio){

        ejercicioService.save(ejercicio);

        return "redirect:/admin/ejercicios";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS MENÚS
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/menus")
    public String menus(Model model) {

        //------------ PARA RELLENAR LA TABLA DE USUARIOS (sin filtro)------------//
        List<MenuDTO> menusCompleto = menuService.findAll();
        model.addAttribute("menus", menusCompleto);

        return "/administrador/menus";
    }

    @GetMapping("/menus/filtrar")
    public String Menufiltrar(@RequestParam("inputNombre") String inputNombre,
                              @RequestParam("inputAlergenos") String inputAlergenos, Model model) {

        //------------ PARA RELLENAR LA TABLA (con filtros)------------//

        List<MenuDTO> menusFiltrado = menuService.filtrarMenus(inputNombre, inputAlergenos);
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
            MenuDTO menu = new MenuDTO();
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
    public String guardarMenus(Model model, @ModelAttribute("menu") MenuDTO menu){

        menuService.save(menu);

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
