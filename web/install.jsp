<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.*" %>
<%@ page import ="model.entities.*" %>
<%@ page import ="java.util.*" %>
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
            Requeriment r1 = new Requeriment();
            r1.setFumador(true);
            r1.setMascotes(true);
            r1.setSexe(SexeLlogater.UNISEX);
            r1.setRang(18, 30);
            
            Requeriment r2 = new Requeriment();
            r2.setFumador(false);
            r2.setMascotes(false);
            r2.setSexe(SexeLlogater.HOME);
            r2.setRang(40, 60);
            
            informacioLlogater i1 = new informacioLlogater();
            i1.setNom("Pere");
            i1.setCognom("Casanova");
            i1.setSexe(SexeLlogater.HOME);
            
            informacioLlogater i2 = new informacioLlogater();
            i2.setNom("Marta");
            i2.setCognom("Fernandez");
            i2.setSexe(SexeLlogater.DONA);
            
            Llogater ll1 = new Llogater();
            ll1.setInfo(i1);
            
            Llogater ll2 = new Llogater();
            ll2.setInfo(i2);
            
            Habitacio hab1 = new Habitacio();
            hab1.setAdresa("C/Mossen martí 33 3r 3a");
            hab1.setCiutat("Valls");
            hab1.setDescripcio("descripcio habitacio 1");
            hab1.setLlogater(ll1);
            hab1.setPreuMes((float)500.10);
            hab1.setRequeriment(r1);
            hab1.setTipus(TipusHabitacio.SIMPLE);
            
            Habitacio hab2 = new Habitacio();
            hab2.setAdresa("C/Mossen martí 33 3r 3a");
            hab2.setCiutat("Valls");
            hab2.setDescripcio("descripcio habitacio 1");
            hab2.setLlogater(ll1);
            hab2.setPreuMes((float)500.10);
            hab2.setRequeriment(r1);
            hab2.setTipus(TipusHabitacio.SIMPLE);
            
            Habitacio hab3 = new Habitacio();
            hab3.setAdresa("C/Mossen martí 33 3r 3a");
            hab3.setCiutat("Valls");
            hab3.setDescripcio("descripcio habitacio 1");
            hab3.setLlogater(ll1);
            hab3.setPreuMes((float)500.10);
            hab3.setRequeriment(r1);
            hab3.setTipus(TipusHabitacio.SIMPLE);
            
            Habitacio hab4 = new Habitacio();
            hab4.setAdresa("C/Mossen martí 33 3r 3a");
            hab4.setCiutat("Valls");
            hab4.setDescripcio("descripcio habitacio 1");
            hab4.setLlogater(ll1);
            hab4.setPreuMes((float)500.10);
            hab4.setRequeriment(r1);
            hab4.setTipus(TipusHabitacio.SIMPLE);
            
            Habitacio hab5 = new Habitacio();
            hab5.setAdresa("C/Mossen martí 33 3r 3a");
            hab5.setCiutat("Valls");
            hab5.setDescripcio("descripcio habitacio 1");
            hab5.setLlogater(ll1);
            hab5.setPreuMes((float)500.10);
            hab5.setRequeriment(r1);
            hab5.setTipus(TipusHabitacio.SIMPLE);
            
            
            Habitacio hab6 = new Habitacio();
            hab6.setAdresa("C/Mossen martí 33 3r 3a");
            hab6.setCiutat("Valls");
            hab6.setDescripcio("descripcio habitacio 1");
            hab6.setLlogater(ll1);
            hab6.setPreuMes((float)500.10);
            hab6.setRequeriment(r1);
            hab6.setTipus(TipusHabitacio.SIMPLE);
            
            Collection<Habitacio> habLlog1 = new ArrayList<Habitacio>();
            habLlog1.add(hab1);
            habLlog1.add(hab2);
            
            Collection<Habitacio> habLlog2 = new ArrayList<Habitacio>();
            habLlog2.add(hab3);
            habLlog2.add(hab4);
            
            Collection<Habitacio> habLlog3 = new ArrayList<Habitacio>();
            habLlog3.add(hab5);
            habLlog3.add(hab6);
            
            Collection<Habitacio> habLlog4 = new ArrayList<Habitacio>();
            habLlog4.add(hab5);
            habLlog4.add(hab6);
            
            Collection<Habitacio> habLlog5 = new ArrayList<Habitacio>();
            habLlog5.add(hab3);
            habLlog5.add(hab6);
            
            Collection<Habitacio> habLlog6 = new ArrayList<Habitacio>();
            habLlog5.add(hab2);
            habLlog5.add(hab4);
            
            
            /* inserting data */
            /* you have to exclude the id autogenerated from the list of columns if you have use it. */
            String data[] = new String[]{
                "INSERT INTO " + schema + ".Habitacio VALUES (NEXT VALUE FOR COMMENT_GEN, 'descripcio habitacio 1', 'C/Mossen martí 33 3r 3a', 'Valls',"+TipusHabitacio.SIMPLE+", 500.10,"+r1+","+ll1+"')",
                "INSERT INTO " + schema + ".Habitacio VALUES (NEXT VALUE FOR COMMENT_GEN, 'descripcio habitacio 2', 'C/Major 72', 'Montferri',"+TipusHabitacio.DOBLE+", 400,"+r1+","+ll1+"')",
                "INSERT INTO " + schema + ".Habitacio VALUES (NEXT VALUE FOR COMMENT_GEN, 'descripcio habitacio 3', 'Avda. Paisos Catalans 12', 'Reus',"+TipusHabitacio.MOBLADA+", 600.10,"+r1+","+ll1+"')",
                "INSERT INTO " + schema + ".Habitacio VALUES (NEXT VALUE FOR COMMENT_GEN, 'descripcio habitacio 4', 'Rambla nova 5', 'Tarragona',"+TipusHabitacio.INTERIOR+", 100.22,"+r2+","+ll2+"')",
                "INSERT INTO " + schema + ".Habitacio VALUES (NEXT VALUE FOR COMMENT_GEN, 'descripcio habitacio 5', 'C/Rafael de Casanova', 'Valls',"+TipusHabitacio.EXTERIOR+", 120.33,"+r2+","+ll2+"')",
                "INSERT INTO " + schema + ".Habitacio VALUES (NEXT VALUE FOR COMMENT_GEN, 'descripcio habitacio 6', 'Avda. Catalunya 2', 'Vallmoll',"+TipusHabitacio.SIMPLE+", 200.10,"+r2+","+ll2+"')",
                
                "INSERT INTO " + schema + ".Llogater VALUES (NEXT VALUE FOR COMMENT_GEN,"+i1+","+habLlog1+"')",
                "INSERT INTO " + schema + ".Llogater VALUES (NEXT VALUE FOR COMMENT_GEN,"+i1+","+habLlog2+"')",
                "INSERT INTO " + schema + ".Llogater VALUES (NEXT VALUE FOR COMMENT_GEN,"+i1+","+habLlog3+"')",
                "INSERT INTO " + schema + ".Llogater VALUES (NEXT VALUE FOR COMMENT_GEN,"+i2+","+habLlog4+"')",
                "INSERT INTO " + schema + ".Llogater VALUES (NEXT VALUE FOR COMMENT_GEN,"+i2+","+habLlog5+"')",
                "INSERT INTO " + schema + ".Llogater VALUES (NEXT VALUE FOR COMMENT_GEN,"+i2+","+habLlog6+"')",
            };
            for (String datum : data) {
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
