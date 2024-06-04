package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.*;
import es.uma.proyectotaw.entity.Usuario;
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

    //////////////////////////////////////////////////////////////////////
    //GESTIÓN DE LOS USUARIOS
    //////////////////////////////////////////////////////////////////////

    @GetMapping("/usuarios")
    public String login(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List<Integer> edades = usuarioRepository.sacarEdades(); //Esta consulta nos devuelve todas las edades, ordenadas y sin repetir listas para usar
        List<Integer> ingresos = usuarioRepository.sacarIngresos(); //Esta consulta nos devuelve todos los años, ordenados y sin repetir listos para usar
        List<Usuario> usuarios = usuarioRepository.sacarUsuarios(); //Esta consulta solo nos devuelve los usuarios, y queremos una lista de los roles
        List<TipoUsuario> rolesUsuarios = usuarioRepository.sacarRoles(); //Esta consulta nos devuelve los roles de usuario que se usan ahora

        model.addAttribute("edades", edades);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("roles", rolesUsuarios);

        //------------ PARA RELLENAR LA TABLA DE USUARIOS (sin filtro)------------//
        List<Usuario> usuariosCompleto = usuarioRepository.sacarUsuarios();
        model.addAttribute("usuarios", usuariosCompleto);

        return "administrador/usuarios";
    }

    @GetMapping("/usuarios/filtrar")
    public String filtrar(@RequestParam("inputNombre") String inputNombre, @RequestParam("inputApellidos") String inputApellidos,
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

        //Para controlar si hemos introducido el dato, ponemos o no a null. Luego esto se maneja en la consulta
        Integer inputEdad;
        if( StringEdad.equals("Selecciona Edad") ){
            inputEdad = null;
        } else {
            inputEdad = Integer.parseInt(StringEdad);
        }

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
    public String seleccionar(@RequestParam(name = "uSeleccionado", required = false) Integer inputUsr, @RequestParam("Boton") String inputBoton, Model model) {

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
                direccionRetorno = "administrador/usuarioUpdate";
            }
        }
        return direccionRetorno;
    }

    @PostMapping("/usuarios/actualizar")
    public String actualizar(Model model, @RequestParam("usuarioId") Integer usuarioId,
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
        //En principio no consideramos que el usuario pueda cambiar la contraseña

        usuarioRepository.save(usr);

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/guardar")
    public String guardar(Model model,
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

        //TODO: CAMBIO GRANDE EN BDD A ESPERA DE MIKI, NO FUNCIONA PORQUE FALTA EL IDENTIFIER DEL USUARIO

        TipoUsuario nuevoRol = tipoUsuarioRepository.buscarPorString(inputRol);
        nuevoUsuario.setTipoUsuario(nuevoRol);


        usuarioRepository.save(nuevoUsuario);

        return "redirect:/admin/usuarios";
    }


    //////////////////////////////////////////////////////////////////////
    //ASIGNACIÓN DE CLIENTES A ENTRENADORES
    //////////////////////////////////////////////////////////////////////

    @GetMapping("/clientesEntrenadores")
    public String clientesEntrenadores(Model model) {

        TipoUsuario tipoCliente = tipoUsuarioRepository.buscarPorID(4);
        List<Usuario> clientes = usuarioRepository.buscarPorTipo(tipoCliente);

        TipoUsuario tipoBodubuilder = tipoUsuarioRepository.buscarPorID(2);
        List<Usuario> bodybuilders = usuarioRepository.buscarPorTipo(tipoBodubuilder);

        TipoUsuario tipoCrosstrainer = tipoUsuarioRepository.buscarPorID(5);
        List<Usuario> crosstrainers = usuarioRepository.buscarPorTipo(tipoCrosstrainer);

        List<Usuario> entrenadores = new ArrayList<>();
        entrenadores.addAll(bodybuilders);
        entrenadores.addAll(crosstrainers);

        model.addAttribute("clientes", clientes);
        model.addAttribute("entrenadores", entrenadores);

        return "administrador/clientesEntrenadores";
    }

}
