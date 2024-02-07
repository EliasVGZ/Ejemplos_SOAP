package org.example;

import org.xmldb.api.base.*;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XQueryService;

public class XMLDB_Conexion {

    public static void main(String[] args) {
        Collection col = null;

        try {
            col = obtenColeccion("/ColeccionGimnasio");
            imprimirInformacionColeccion(col);
            //imprimirHijasColeccion(col);
            imprimirDocumentosColeccion(col);
            //imprimirServiciosColeccion(col);
            //Consultas.realizarConsultaXQuery(col);
            Consultas.busquedaCodigoSocio(col);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cerrarColeccion(col);
        }
    }

    private static Collection obtenColeccion(String nomCol) throws Exception {
        Database dbDriver;
        Collection col;
        dbDriver = (Database) Class.forName("org.exist.xmldb.DatabaseImpl").newInstance();
        DatabaseManager.registerDatabase(dbDriver);
        col = DatabaseManager.getCollection(
                "xmldb:exist://localhost:8080/exist/xmlrpc/db" + nomCol,
                "admin", "abc123.");
        return col;
    }

    private static void imprimirInformacionColeccion(Collection col) throws XMLDBException {
        System.out.println("Colección actual: " + col.getName());
    }

    private static void imprimirHijasColeccion(Collection col) throws XMLDBException {
        int numHijas = col.getChildCollectionCount();
        System.out.println(numHijas + " colecciones hijas.");
        if (numHijas > 0) {
            String nomHijas[] = col.listChildCollections();
            for (int i = 0; i < numHijas; i++) {
                System.out.println("\t" + nomHijas[i]);
            }
        }
    }

    private static void imprimirDocumentosColeccion(Collection col) throws XMLDBException {
        int numDocs = col.getResourceCount();
        System.out.println(numDocs + " documentos.");
        if (numDocs > 0) {
            String nomDocs[] = col.listResources();
            for (int i = 0; i < numDocs; i++) {
                System.out.println("\t" + nomDocs[i]);
            }
        }
    }

    private static void imprimirServiciosColeccion(Collection col) throws XMLDBException {
        Service[] servicios = col.getServices();
        System.out.println("Servicios proporcionados por colección " + col.getName() + ":");
        for (int i = 0; i < servicios.length; i++) {
            System.out.println("\t" + servicios[i].getName() + " - Versión: " + servicios[i].getVersion());
        }
    }



    private static void cerrarColeccion(Collection col) {
        try {
            if (col != null) {
                col.close();
            }
        } catch (XMLDBException e) {
            e.printStackTrace();
        }
    }
}