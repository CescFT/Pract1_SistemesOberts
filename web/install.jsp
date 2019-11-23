<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "model.entities.*" %>
<%@ page import = "autenticacio.*" %>
<%@ page import = "java.util.*" %>
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
            
            credentialsClient c1 = new credentialsClient();
            c1.setUsername("cesc");
            
            String alphaNumericString="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            int cmpt = 0;
            StringBuilder stringb = new StringBuilder();
            while(cmpt<32){
                int posRand=(int)(Math.random()*alphaNumericString.length());
                stringb.append(alphaNumericString.charAt(posRand));
                cmpt++;
            }
            
            String randomPasswd = stringb.toString();
            c1.setPassword(randomPasswd);
            c1.setEmail("77792731-S@estudiants.urv.cat");
            
            out.println("<pre> Password for " + c1.getUsername() + " is "+ c1.getPassword() +" <pre>");
            
            credentialsClient c2 = new credentialsClient();
            
            cmpt = 0;
            stringb = new StringBuilder();
            while(cmpt<32){
                int posRand=(int)(Math.random()*alphaNumericString.length());
                stringb.append(alphaNumericString.charAt(posRand));
                cmpt++;
            }
            randomPasswd=stringb.toString();
            c2.setUsername("aleix");
            c2.setPassword(randomPasswd);
            c2.setEmail("39925731-P@estudiants.urv.cat");
            
            out.println("<pre> Password for " + c2.getUsername() + " is "+ c2.getPassword() +" <pre>");
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String authorizedUsers[] = new String[]{
              "INSERT INTO " + schema +".CREDENTIALSCLIENT (PASSWORD, E_MAIL, USERNAME) VALUES ('"+c1.getCryptedPassword()+"','77792731-S@estudiants.urv.cat', 'cesc')",
              "INSERT INTO " + schema +".CREDENTIALSCLIENT (PASSWORD, E_MAIL, USERNAME) VALUES ('"+c2.getCryptedPassword()+"', '39925731-P@estudiants.urv.cat', 'aleix')",  
            };
            
            for(String user : authorizedUsers){
                if (stmt.executeUpdate(user)<=0) {
                    out.println("<span class='error'>Error inserting data: " + user + "</span>");
                    return;
                }
                out.println("<pre> -> " + user + "<pre>");
            }
            
            
            String dataLlogater[] = new String[]{
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Casanova',0,69,0,1,'Pere',0)",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES)  VALUES (NEXT VALUE FOR LLOGATER_GEN,'Casanova',0,69,0,1,'Pere',0)",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Casanova',0,69,0,1,'Pere',0)",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Fernandez',1,60,0,0,'Marta',1)",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Fernandez',1,60,0,0,'Marta',1)",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Fernandez',1,60,0,0,'Marta',1)",
                "INSERT INTO " + schema + ".LLOGATER (ID, COGNOM_LLOGATER,DONA,EDAT_LLOGATER,FUMADOR,HOME,NOM_LLOGATER,TEMASCOTES) VALUES (NEXT VALUE FOR LLOGATER_GEN,'Cognom1',1,30,0,0,'Lloga la Habitacio',1)",              
            };
            for (String datum : dataLlogater) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
            
            //Aqui hauriem de recuperar d'alguna manera la columna dels id dels paios i assignar-li un a cadascun.... per aixo ho he separat
            List<String> idsLlogaters = new ArrayList<String>();
            ResultSet result = stmt.executeQuery("SELECT ID FROM LLOGATER");
            while(result.next()){
                idsLlogaters.add(String.valueOf(result.getInt("ID")));
            }
            out.println("Ids de llogaters:");
            for(String s : idsLlogaters)
            {
                out.println("<pre> -> " + s + "<pre>");
            }
            String dataHabitacions[] = new String[]{
              
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, DOBLE,EXTERIOR,INTERIOR,MOBLADA,PREU_MES, SIMPLE,DONA, FUMADOR,HOME, MASCOTES, RANGEDATMAX,RANGEDATMIN,UNISEX,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN,'C/Mossen martí 33 3r 3a', 'Valls','descripcio habitacio 1',0,0,0,0,500.10,1,0,0,0,1,30,50,1,"+idsLlogaters.get(0)+")", 
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, DOBLE,EXTERIOR,INTERIOR,MOBLADA,PREU_MES, SIMPLE,DONA, FUMADOR,HOME, MASCOTES, RANGEDATMAX,RANGEDATMIN,UNISEX,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'C/Major 72','Montferri','descripcio habitacio 2',0,0,0,0,500.10,1,0,0,0,1,30,50,1,"+idsLlogaters.get(1)+")",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, DOBLE,EXTERIOR,INTERIOR,MOBLADA,PREU_MES, SIMPLE,DONA, FUMADOR,HOME, MASCOTES, RANGEDATMAX,RANGEDATMIN,UNISEX,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'Avda. Paisos Catalans 12', 'Reus', 'descripcio habitacio 3',0,0,0,0,500.10,1,0,0,0,1,30,50,1,"+idsLlogaters.get(2)+")",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, DOBLE,EXTERIOR,INTERIOR,MOBLADA,PREU_MES, SIMPLE,DONA, FUMADOR,HOME, MASCOTES, RANGEDATMAX,RANGEDATMIN,UNISEX,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'Rambla nova 5', 'Tarragona','descripcio habitacio 4',0,0,0,0,500.10,1,0,0,0,1,30,50,1,"+idsLlogaters.get(3)+")",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, DOBLE,EXTERIOR,INTERIOR,MOBLADA,PREU_MES, SIMPLE,DONA, FUMADOR,HOME, MASCOTES, RANGEDATMAX,RANGEDATMIN,UNISEX,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'C/Rafael de Casanova', 'Valls','descripcio habitacio 5',0,0,0,0,500.10,1,0,0,0,1,30,50,1,"+idsLlogaters.get(4)+")",
                "INSERT INTO " + schema + ".HABITACIO (HABITACIO_ID, ADREÇA, CIUTAT, DESCRIPCIO, DOBLE,EXTERIOR,INTERIOR,MOBLADA,PREU_MES, SIMPLE,DONA, FUMADOR,HOME, MASCOTES, RANGEDATMAX,RANGEDATMIN,UNISEX,LLOGATER_ID) VALUES (NEXT VALUE FOR HABITACIO_GEN, 'Avda. Catalunya 2', 'Vallmoll','descripcio habitacio 6',0,0,0,0,500.10,1,0,0,0,1,30,50,1,"+idsLlogaters.get(5)+")"  
                
                
            };
            for (String datum : dataHabitacions) {
                if (stmt.executeUpdate(datum)<=0) {
                    out.println("<span class='error'>Error inserting data: " + datum + "</span>");
                    return;
                }
                out.println("<pre> -> " + datum + "<pre>");
            }
            
            
            List<String> idsHabitacions = new ArrayList<String>();
            ResultSet resu = stmt.executeQuery("SELECT HABITACIO_ID FROM HABITACIO");
            while(resu.next()){
                idsLlogaters.add(String.valueOf(resu.getInt("HABITACIO_ID")));
            }
            out.println("ids de habitacions:");
            for(String s : idsLlogaters)
            {
                out.println("<pre> -> " + s + "<pre>");
            }
        %>
        <button onclick="window.location='<%=request.getSession().getServletContext().getContextPath()%>'">Go home</button>
    </body>
</html>
