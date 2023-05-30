import java.util.ArrayList;
import java.util.Scanner;


public class manipularPrograma {
    public void menu(){
    
        Scanner entrada = new Scanner(System.in);

        //instancia del crud
        AccionesPersona control = new AccionesPersona();

        System.out.println("Bienvenido al programa para manipular pokemones");
        System.out.println("Elija la opcion deseada");
        System.out.println("1.- Mostrar todas las personas");
        System.out.println("2.- Registrar nuevo pukamon");
        System.out.println("3.- Editar los datos del pukamon");
        System.out.println("4.- Borrar el pukamon");
        System.out.println("5.- Salir");
        
        int opcion = Integer.parseInt(entrada.nextLine());
        
        switch (opcion) {
            case 1 -> {
                //instancia
                ArrayList<Persona> listadepersonas = 
                        control.mostrarPersonas();
                //debo imprimir los resultados del arreglo
                for(Persona objeto : listadepersonas){
                    System.out.println("El id del pukamon es: " + objeto.getId()
                            + "El nombre del pukamon es: " + objeto.getNombre()
                            + "El edad del pukamon es: " + objeto.getEdad());
                }
                menu();
            }
            case 2 -> {
                System.out.println("Ingrese el id del pukamon");
                int idpersona = Integer.parseInt(entrada.nextLine());
                System.out.println("Ingrese el nombre del pukamon");
                String nombrepersona = entrada.nextLine();
                System.out.println("Ingrese la edad del pukamon");
                int edadpersonar = Integer.parseInt(entrada.nextLine());
                
                //guardarlo
                Persona personaPukamon = 
                        new Persona(idpersona, nombrepersona, edadpersonar);
                //mando a llamar al metodo para guardar
                control.agregarPersona(personaPukamon);
                menu();
            }
            case 3 -> {
                //primero debo de hacer una busqueda por el id
                System.out.println("Ingrese el id del pukamon a buscar");
                int idpersona = Integer.parseInt(entrada.nextLine());
                //creo mi objeto para buscar a una persona
                Persona personaBuscar = control.buscarPersona(idpersona);
        System.out.println("La informacion del pukamon es: \n"
    + "ID: " + personaBuscar.getId() + "\n"
    + "Nombre: " + personaBuscar.getNombre() + "\n"
    + "Edad: " + personaBuscar.getEdad() + "\n");

                //cambiarlos
                System.out.println("Ingresa el nuevo nombre");
                String nuevonombre = entrada.nextLine();
                System.out.println("Ingresa la nueva edad:");
                int nuevaedad = Integer.parseInt(entrada.nextLine());
                //envio los nuevos datos
                personaBuscar.setNombre(nuevonombre);
                personaBuscar.setEdad(nuevaedad);
                //actualizo
                control.actualizarPersona(personaBuscar);
                menu();
            }
            case 4 -> {
                System.out.println("Ingrese el id del pukamon a sacrificar");
                int idpersona = Integer.parseInt(entrada.nextLine());
                //creo mi objeto para eliminar
                Persona personaEliminar = control.buscarPersona(idpersona);
                //la peto
                control.eliminarPersona(personaEliminar);
                System.out.println("Ese pukamon ya es libre");
                menu();
            }
                
            default -> System.out.println("Opcion no valida llegale");
        }
    
    }
                
     
}