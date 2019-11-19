<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "model.entities.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Database SQL Load</title>
    </head>
    <style>
        .error {
            color: red;
        }
        pre {
            color: green;
        }
    </style>
    <body>
        <h2>Database SQL Load</h2>
        <%
            /* How to customize:
             * 1. Update the database name on dbname.
             * 2. Create the list of tables, under tablenames[].
             * 3. Create the list of table definition, under tables[].
             * 4. Create the data into the above table, under data[]. 
             * 
             * If there is any problem, it will exit at the very first error.
             */
            String dbname = "homework1";
            String schema = "ROOT";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            /* this will generate database if not exist */
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dbname, "root", "root");
            Statement stmt = con.createStatement();
            
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String dataLlogater[] = new String[]{
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER, NOM_LLOGATER, SEXE) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Casanova','Pere','home')",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER, NOM_LLOGATER, SEXE)  VALUES (NEXT VALUE FOR LLOGATER_GEN,'Casanova','Pere','home')",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER, NOM_LLOGATER, SEXE) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Casanova','Pere','home')",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER, NOM_LLOGATER, SEXE) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Fernandez','Marta','dona')",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER, NOM_LLOGATER, SEXE) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Fernandez','Marta','dona')",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER, NOM_LLOGATER, SEXE) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Fernandez','Marta','dona')",              
            };
            for (String datum : dataLlogater) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
            
            //Aqui hauriem de recuperar d'alguna manera la columna dels id dels paios i assignar-li un a cadascun.... per aixo ho he separat
            
            String dataHabitacions[] = new String[]{
              //recuperar el id de un Llogater?¿?¿?¿?
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, PREU_MES, TIPUS, FUMADOR, MASCOTES, RANGEDATMAX,RANGEDATMIN,SEXE,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN,'C/Mossen martí 33 3r 3a', 'Valls','descripcio habitacio 1',500.10,'simple',1,1,30,50,'unisex',null)", 
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, PREU_MES, TIPUS, FUMADOR, MASCOTES, RANGEDATMAX,RANGEDATMIN,SEXE,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'C/Major 72','Montferri','descripcio habitacio 2',400,'doble',1,1,30,50,'unisex',null)",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, PREU_MES, TIPUS, FUMADOR, MASCOTES, RANGEDATMAX,RANGEDATMIN,SEXE,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'Avda. Paisos Catalans 12', 'Reus', 'descripcio habitacio 3',600.10,'moblada',1,1,30,50,'unisex',null)",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, PREU_MES, TIPUS, FUMADOR, MASCOTES, RANGEDATMAX,RANGEDATMIN,SEXE,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'Rambla nova 5', 'Tarragona','descripcio habitacio 4',100.22,'interior',0, 0, 40,60,'home',null)",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, PREU_MES, TIPUS, FUMADOR, MASCOTES, RANGEDATMAX,RANGEDATMIN,SEXE,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'C/Rafael de Casanova', 'Valls','descripcio habitacio 5',120.33,'exterior',0, 0,40,60,'home',null)",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, PREU_MES, TIPUS, FUMADOR, MASCOTES, RANGEDATMAX,RANGEDATMIN,SEXE,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'Avda. Catalunya 2', 'Vallmoll','descripcio habitacio 6',200.10 ,'simple', 0, 0,40,60,'home',null)"  
                
                
            };
            for (String datum : dataHabitacions) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
