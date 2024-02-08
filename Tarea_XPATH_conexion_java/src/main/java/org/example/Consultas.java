package org.example;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Consultas {

    private static int numConsulta;

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



    public static void variasConsultasFaciles(Collection col) throws XMLDBException {
        XPathQueryService xqservicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xqservicio.setProperty("indent", "yes");

        String nombreUniversidad = "data(/universidad/nombre)";
        String paisUniversidad = "data(/universidad/pais)";
        String nombreCarreras = "data(/universidad/carreras/carrera/nombre)";
        String nombreYplan = "/universidad/carreras/carrera/nombre | /universidad/carreras/carrera/plan";
        String nombreAlumnos = "/universidad/alumnos/alumno/nombre";
        //String idCarreras = "/universidad/carreras/carrera/@id"; NO FUNCIONA
        String datosId1 = "/universidad/carreras/carrera[@id='c01']";


        List<String> consultas = List.of(nombreUniversidad,paisUniversidad, nombreCarreras, nombreYplan, nombreAlumnos, datosId1);
        numConsulta = 1;

        for (String consulta : consultas) {
            try {
                ResourceSet result = xqservicio.query(consulta);
                ResourceIterator i = result.getIterator();

                System.out.println("Resultados de la consulta: "+numConsulta);
                numConsulta++;

                while (i.hasMoreResources()) {
                    try {
                        Resource resource = i.nextResource();
                        System.out.println(resource.getContent());
                    } catch (XMLDBException e) {
                        e.printStackTrace();
                    }
                }
            } catch (XMLDBException e) {
                e.printStackTrace();
            }

            System.out.println("-------------------------------");
        }
    }




}
