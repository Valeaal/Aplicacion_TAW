package es.uma.proyectotaw.controller;

import es.uma.proyectotaw.dao.*;
import es.uma.proyectotaw.entity.TipoUsuario;
import es.uma.proyectotaw.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/usuarios")
    public String login(Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List<Integer> edades = usuarioRepository.sacarEdades(); //Esta consulta nos devuelve todas las edades, ordenadas y sin repetir listas para usar
        List<Integer> ingresos = usuarioRepository.sacarIngresos(); //Esta consulta nos devuelve todos los años, ordenados y sin repetir listos para usar
        List<Usuario> usuarios = usuarioRepository.sacarUsuarios(); //Esta consulta solo nos devuelve los usuarios, y queremos una lista de los roles

        //Para conseguir la lista de los roles, podríamos hacerlo desde la tabla TipoUsuario, pero gracias a este método vamos a hacer que solo nos
        //aparezcan los roles existentes en los usuarios que tenemos en este momento en el bdd, no todos los posibles.
        //Podríamos complicar la consulta SQL, pero realmente podemos simplemente recuperar los usuarios y ayudarnos de JPQL para hallar
        //los tipos de roles que le corresponden.
        Set<String> rolesUsuarios = new HashSet<>();    //Al usar un set nos ahorramos las repeticiones
        for (Usuario usr : usuarios) {
            rolesUsuarios.add(usr.getTipoUsuario().getTipo());
        }

        model.addAttribute("edades", edades);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("roles", rolesUsuarios);

        //------------ PARA RELLENAR LA TABLA ------------//
        List<Usuario> usuariosCompleto = usuarioRepository.sacarUsuarios();
        model.addAttribute("usuarios", usuariosCompleto);



        return "administrador/usuarios";
    }

    @GetMapping("/usuarios/filtrar")
    public String filtrar(@RequestParam("busquedaN") String inputNombre, @RequestParam("busquedaA1") String inputApellidos,
                          @RequestParam("StringEdad") String StringEdad, /*@RequestParam("ingreso") Integer inputIngreso,
                          @RequestParam("rol") Integer inputRol,*/ Model model) {

        //------------ PARA RELLENAR LOS SELECTORES DEL FORMULARIO ------------//
        List<Integer> edades = usuarioRepository.sacarEdades(); //Esta consulta nos devuelve todas las edades, ordenadas y sin repetir listas para usar
        List<Integer> ingresos = usuarioRepository.sacarIngresos(); //Esta consulta nos devuelve todos los años, ordenados y sin repetir listos para usar
        List<Usuario> usuarios = usuarioRepository.sacarUsuarios(); //Esta consulta solo nos devuelve los usuarios, y queremos una lista de los roles

        //En esencia es lo mismo que el método anterior
        Set<String> rolesUsuarios = new HashSet<>();    //Al usar un set nos ahorramos las repeticiones
        for (Usuario usr : usuarios) {
            rolesUsuarios.add(usr.getTipoUsuario().getTipo());
        }

        model.addAttribute("edades", edades);
        model.addAttribute("ingresos", ingresos);
        model.addAttribute("roles", rolesUsuarios);

        //------------ PARA RELLENAR LA TABLA ------------//

        Integer inputEdad;
        if( StringEdad.equals("Selecciona Edad") ){
            inputEdad = 0;
        } else {
            inputEdad = Integer.parseInt(StringEdad);
        }


        List<Usuario> usuariosFiltrado = usuarioRepository.filtrarUsuarios(inputNombre, inputApellidos, inputEdad);

        model.addAttribute("usuarios", usuariosFiltrado);



        return "administrador/usuarios";
    }

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
