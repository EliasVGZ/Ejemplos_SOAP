package org.example;

import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Consultas {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void realizarConsultaXQuery(Collection col) throws XMLDBException {
        XPathQueryService xqservicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xqservicio.setProperty("indent", "yes");

        String xquery = "for $doc in collection('/db/ColeccionGimnasio') return $doc";
        ResourceSet result = xqservicio.query(xquery);

        ResourceIterator i = result.getIterator();

        System.out.println("Resultados de la consulta:");

        while (i.hasMoreResources()) {
            try {
                Resource resource = i.nextResource();
                System.out.println(resource.getContent());
            } catch (XMLDBException e) {
                e.printStackTrace();
            }
        }
    }

    public static void busquedaCodigoSocio(Collection col) throws XMLDBException {
        XPathQueryService xqservicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xqservicio.setProperty("indent", "yes");

        String xquery = "for $usuario in /SOCIOS_GIM/fila_socios where $usuario/COD = '10' return $usuario";
        ResourceSet result = xqservicio.query(xquery);

        ResourceIterator i = result.getIterator();

        System.out.println("Resultados de la consulta:");

        while (i.hasMoreResources()) {
            try {
                Resource resource = i.nextResource();
                System.out.println(resource.getContent());
            } catch (XMLDBException e) {
                e.printStackTrace();
            }
        }
    }

    public static void busquedaCodigoSocioFormaDinamica(Collection col) throws XMLDBException {
        XPathQueryService xqservicio = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        xqservicio.setProperty("indent", "yes");

        System.out.println("Escribe el codigo del usuario");

        String xquery = "for $usuario in /SOCIOS_GIM/fila_socios where $usuario/COD = '10' return $usuario";
        ResourceSet result = xqservicio.query(xquery);

        ResourceIterator i = result.getIterator();

        System.out.println("Resultados de la consulta:");

        while (i.hasMoreResources()) {
            try {
                Resource resource = i.nextResource();
                System.out.println(resource.getContent());
            } catch (XMLDBException e) {
                e.printStackTrace();
            }
        }
    }



}
