package test.java.org.n52.hibernate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hsqldb.ras.RasUtil;

import rasj.RasGMArray;
import rasj.RasMArrayByte;
import rasj.RasMArrayDouble;
import rasj.RasMArrayFloat;
import rasj.RasMArrayInteger;
import rasj.RasMArrayLong;
import rasj.RasMArrayShort;
import rasj.RasMInterval;


/**
 * Test the asqldb select tests from hibernate
 * @author simona
 */
public class HibernateMArrayQueryTest {
    private static SessionFactory sessionFactory = null;
    public static final String DEFAULT_DB_FILE = "/var/hsqldb/test/db";

    private String dbFile = DEFAULT_DB_FILE;

    public static RastestConst executeConstAsqlQuery(String asqlQuery) {
        Session       session = null;
        RastestConst  rastest = null;

        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Executing non array test");
                Transaction tx = session.beginTransaction();
                SQLQuery query = session.createSQLQuery(asqlQuery);
                query.addEntity(RastestConst.class);
                rastest = (RastestConst) query.uniqueResult();

                tx.commit();
                System.out.println("Select Done");
            } catch (Exception e) {
//                e.printStackTrace();
                System.err.println("The query can't be executed. RastestConst");
                return rastest;
            }
        } finally {
            session.close();
        }
        return rastest;
    }

    public static RastestConstByte executeConstVarCharAsqlQuery(String asqlQuery) {
        Session         session = null;
        RastestConstByte  rastest = null;

        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Executing non array test");
                Transaction tx = session.beginTransaction();
                SQLQuery query = session.createSQLQuery(asqlQuery);
                query.addEntity(RastestConstByte.class);
                rastest = (RastestConstByte) query.uniqueResult();


                tx.commit();
                System.out.println("Select Done");
            } catch (Exception e) {
//                e.printStackTrace();
                System.err.println("The query can't be executed. RastestConstVarChar");
                return rastest;
            }
        } finally {
            session.close();
        }
        return rastest;
    }

    public static RastestNoArray executeNoArrayAsqlQuery(String asqlQuery) {
        Session         session = null;
        RastestNoArray  rastest = null;

        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Executing non array test");
                Transaction tx = session.beginTransaction();
                SQLQuery query = session.createSQLQuery(asqlQuery);
                query.addEntity(RastestNoArray.class);
                rastest = (RastestNoArray) query.uniqueResult();

                tx.commit();
                System.out.println("Select Done");
            } catch (Exception e) {
//                e.printStackTrace();
                System.err.println("The query can't be executed");
                return rastest;
            }
        } finally {
            session.close();
        }
        return rastest;
    }


    public static Rastest executeMArrayAsqlQuery(String asqlQuery) {
        Session         session = null;
        Rastest         rastest = null;

        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Executing multidimensional array test!");
                Transaction tx = session.beginTransaction();
                SQLQuery query = session.createSQLQuery(asqlQuery);
                query.addEntity(Rastest.class);
                rastest = (Rastest) query.uniqueResult();

                tx.commit();
                System.out.println("Select Done");
            } catch (Exception e) {
                //                e.printStackTrace();
                System.err.println("The query can not be executed");
                return rastest;
            }
        } finally {
            session.close();
        }
        return rastest;
    }

    public static RastestMInterval executeMIntervalAsqlQuery(String asqlQuery) {
        Session         session = null;
        RastestMInterval         rastest = null;

        try 
        {
            try
            {
                sessionFactory = AppFactory.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Executing multidimensional array test!");
                Transaction tx = session.beginTransaction();
                SQLQuery query = session.createSQLQuery(asqlQuery);
                query.addEntity(RastestMInterval.class);
                rastest = (RastestMInterval) query.uniqueResult();

                tx.commit();
                System.out.println("Select Done");
            } catch (Exception e) {
                //                e.printStackTrace();
                System.err.println("The query can not be executed");
                return rastest;
            }
        } finally {
            session.close();
        }
        return rastest;
    }

    public Connection getConnection() throws SQLException {
        Connection conn;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "SA");
        connectionProps.put("password", "");

        try {

            Class.forName("org.hsqldb.jdbc.JDBCDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Could not load the hsqldb JDBCDriver", e);
        }

        final String jdbcUrl = "jdbc:hsqldb:file:" + dbFile;
        conn = DriverManager.getConnection(
                jdbcUrl,
                connectionProps
                );
        System.out.println("Connected to database: "+jdbcUrl);
        return conn;
    }

    private void setUp(final Connection connection) throws SQLException {
        RasUtil.openDatabase(RasUtil.adminUsername, RasUtil.adminPassword, true);
        try {
            RasUtil.executeRasqlQuery("create collection rastest GreySet",
                    false, false);
            RasUtil.executeRasqlQuery("insert into rastest values marray x in [0:250, 0:250] values 1c",
                    false, false);
            RasUtil.executeRasqlQuery("create collection rastest2 GreySet",
                    false, false);
            RasUtil.executeRasqlQuery("insert into rastest2 values marray x in [0:250, 0:250] values 2c",
                    true, false);
        } catch (Exception ignored) {  }

        String oidQuery = "select oid(c) from rastest as c";
        String oid = RasUtil.executeRasqlQuery(oidQuery, true, false).toString();
        oid = oid.replaceAll("[\\[\\]]", "");
        oidQuery = "select oid(c) from rastest2 as c";
        String oid2 = RasUtil.executeRasqlQuery(oidQuery, true, false).toString();
        oid2 = oid2.replaceAll("[\\[\\]]", "");
        oidQuery = "select oid(c) from rgb as c";
        String oid3 = RasUtil.executeRasqlQuery(oidQuery, true, false).toString();
        oid3 = oid3.replaceAll("[\\[\\]]", "");

        String[] queries = new String[]{
                "create table RASTEST (ID integer NOT NULL, COLL varchar(40) ARRAY NOT NULL, PRIMARY KEY (ID))",
                "create table RASTEST2 (ID integer NOT NULL, COLL varchar(40) ARRAY NOT NULL, " +
                        "COLL2 varchar(40) ARRAY NOT NULL, PRIMARY KEY (ID))",
                        "create table RGB (ID integer NOT NULL, COLL varchar(40) ARRAY NOT NULL, " +
                                "COLL2 varchar(40) ARRAY NOT NULL, PRIMARY KEY (ID))",
                                "INSERT INTO RASTEST VALUES(0, ARRAY['rastest:" + Double.valueOf(oid).intValue() + "'])",
                                "INSERT INTO RASTEST2 VALUES(0, ARRAY['rastest:" + Double.valueOf(oid).intValue() + "'], " +
                                        "ARRAY['rastest2:" + Double.valueOf(oid2).intValue() + "'])",
                                        "INSERT INTO RGB VALUES(0, ARRAY['rgb:" + Double.valueOf(oid3).intValue() + "'], " +
                                                "ARRAY['rgb:" + Double.valueOf(oid3).intValue() + "'])",
        };
        for (String query : queries) {
            executeQuery(connection, query);
        }
    }

    private boolean executeQuery(final Connection conn, final String query) throws SQLException{
        System.out.printf("Executing query: "+ query);
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            return false;
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return true;
    }

    /**
     * 
     * @param conn
     * @param query: The asqldb query
     * @param index: the index of the column to be returned
     * @return The object returned by executing the query using the JDBC
     * @throws SQLException
     */
    private Object getAsqlResult(final Connection conn, final String query, int index) throws SQLException {
        System.out.printf("Executing asql query: "+ query);
        Statement   stmt = null;
        byte[]      array = null;
        try {
            stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {
                Object colVal = (Object)result.getObject(index);
                return colVal;
            }
        } catch (SQLException e) {
            return array;
        } finally {
            if (stmt != null) { stmt.close(); }
        }
        return array;
    }

    /**
     * Determine if the object is a rasdaman multidimensional
     * array object
     * @param obj: The object returned in the ResultSet
     * @return true if the object is instance of any
     *         of the RasMArray* types
     */
    private boolean isArrayColumn(Object obj) {
        if (obj instanceof RasGMArray)
            return true;
        if (obj instanceof RasMArrayByte)
            return true;
        if (obj instanceof RasMArrayDouble)
            return true;
        if (obj instanceof RasMArrayFloat)
            return true;
        if (obj instanceof RasMArrayInteger)
            return true;
        if (obj instanceof RasMArrayLong)
            return true;
        if (obj instanceof RasMArrayShort)
            return true;
        return false;
    }

    /**
     * compare two rasdaman multidimensional arrays
     * @param asqlResult
     * @param rasqlResult
     * @return
     */
    public boolean compareArrays(Object asqlResult, Object rasqlResult) {
        System.out.println("Intra aici");
        RasGMArray asqlArray = (RasGMArray) asqlResult;
        RasGMArray rasqlArray = (RasGMArray) rasqlResult;

        byte[] asql = asqlArray.getArray();
        byte[] rasql = rasqlArray.getArray();

        if (asql.length != rasql.length) {
            return false;
        }

        for (int i = 0; i < asql.length; i++) {
            if (asql[i] != rasql[i]) {
                System.out.println("Difera ceva " + i);
                return false;
            }
        }
        return true;
    }

    /**
     * Compare an asqldb result and a rasql result
     * @param asqlResult
     * @param rasqlResult
     * @return
     */
    private boolean compareResults(Object asqlResult, Object rasqlResult) {
        if (asqlResult == null && rasqlResult == null) {
            return true;
        }

        if (asqlResult == null || rasqlResult == null) {
            return false;
        }

        // compare intervals
        if (asqlResult instanceof RasMInterval &&
                rasqlResult instanceof RasMInterval) {
            RasMInterval asqlInterval = (RasMInterval)asqlResult;
            RasMInterval rasqlInterval = (RasMInterval)rasqlResult;

            return asqlInterval.equals(rasqlInterval);
        }

        //compare array results
        if (isArrayColumn(asqlResult) && isArrayColumn(rasqlResult)) {
            return compareArrays(asqlResult, rasqlResult);
        } else {
            // compare non array results - only numerical values
            if (!isArrayColumn(asqlResult) && !isArrayColumn(rasqlResult)) {
                Double resAsql = Double.parseDouble(asqlResult.toString());
                Double resRasql = Double.parseDouble(rasqlResult.toString());
                return resAsql.equals(resRasql);
            }
        }
        return false;
    }

    boolean[] results = null;
    int count = 0;
    int totalTests = 0;



    public void runTestArray(Connection connection, 
            HashMap<String, File> map, String folder, 
            int index) throws IOException, SQLException {
        File arraydir = new File(folder);
        final File[] arrayFiles = arraydir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".rasql");
            }
        });

        for (int i = 0; i < arrayFiles.length; i++) {
            File fileHibernate = arrayFiles[i];
            File fileAsql = map.get(fileHibernate.getName());

            totalTests++;

            BufferedReader reader = new BufferedReader(new FileReader(fileAsql));
            BufferedReader reader1 = new BufferedReader(new FileReader(fileHibernate));

            String asqldbQuery = reader.readLine();
            String hibernateQuery = reader1.readLine();

            System.out.println("File: " + fileHibernate.getAbsolutePath());
            System.out.println("AsqlQuery : " + asqldbQuery);

            Object asqlResult = getAsqlResult(connection, asqldbQuery, index);
            hibernateQuery = escape(hibernateQuery);
            System.out.println("Hibernate Query :" + hibernateQuery);
            Object tocompare = null;
            if (folder.contains("array")) {
                Rastest result = executeMArrayAsqlQuery(hibernateQuery);
                if (result != null)
                    tocompare = result.getColl();
            } else if (folder.contains("string")) {
                RastestConstByte result = executeConstVarCharAsqlQuery(hibernateQuery);
                if (result != null)
                    tocompare = result.getColl();
            } else if (folder.contains("const")) {
                RastestConst result = executeConstAsqlQuery(hibernateQuery);
                if (result != null)
                    tocompare = result.getColl();
            } else if (folder.contains("interval")) {
                RastestMInterval result = executeMIntervalAsqlQuery(hibernateQuery);
                if (result != null)
                    tocompare = result.getColl();
            }
            results[totalTests-1] = compareResults(asqlResult, tocompare);
            if (!results[totalTests - 1]) {
                count++;
                System.out.println("-");
            } else {
                System.out.print('+');
            }
        }

    }

    public void runTest() throws SQLException, IOException {
        final Connection connection = getConnection();
        setUp(connection);

        String asqldbFolder = "testrun/asqldb/select";
        String arrayFolder = "testrun/asqldb/hibernate/array";
        String stringFolder = "testrun/asqldb/hibernate/string";
        String constFolder = "testrun/asqldb/hibernate/const";
        String intervalFolder = "testrun/asqldb/hibernate/interval";

        File dirAsqldb = new File(asqldbFolder);

        final File[] asqldbFiles = dirAsqldb.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".rasql");
            }
        });

        // hashmap of filename and File reference
        HashMap<String, File> map = new HashMap<String, File>();
        for (int i = 0; i < asqldbFiles.length; i++) {
            map.put(asqldbFiles[i].getName(), asqldbFiles[i]);
        }

        results = new boolean[asqldbFiles.length];

        System.out.print("Running tests...");
        runTestArray(connection, map, arrayFolder, 2);
        runTestArray(connection, map, intervalFolder, 2);
        runTestArray(connection, map, constFolder, 1);
        runTestArray(connection, map, stringFolder, 1);

        System.out.println(String.format("%d/%d tests failed:", count, totalTests));

        for (int i = 0, filesLength = asqldbFiles.length; i < filesLength; i++) {
            if (!results[i]) {
                System.out.println(asqldbFiles[i].getName());
            }
        }

        if (count > 0) {
            System.out.println(String.format("%d/%d tests failed:", count, totalTests));
            System.out.println("--- TESTS FAILED ---");
        } else {
            System.out.println("+++ All tests PASSED +++");
        }
    }

    public static String escape(String query) {
        StringBuilder str = new StringBuilder(query);
        int index = 0;
        while (true) {
            index = str.indexOf(":", index);
            System.out.println(index);
            if (index == -1) break;
            str.insert(index, "\\");
            index += 2;
        }
        return str.toString();
    }

    public static void main(String[] args) throws SQLException {  
        HibernateMArrayQueryTest hib = new HibernateMArrayQueryTest();
        try {
            hib.runTest();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
