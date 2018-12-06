package sample;

import java.sql.*;


public class DB {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    public void connect() {
        for (int i=0; i<3; i++){
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn =
                        DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/maciejw",
                                "maciejw", "dEgqnqsG8ripMcK3");
                if (conn != null) {
                    break;
                }
            } catch (SQLException ex){
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            System.out.println("Connected succesfuly!");
        }
        else {
            System.out.println("Couldn't connect to database :(");
        }

    }

    public void listNames() {
        try {
            connect();
            stmt = conn.createStatement();

            // Wyciagamy wszystkie pola z kolumny name
            // znajdujące się w tabeli users
            rs = stmt.executeQuery("SELECT nazwisko FROM tabela1");

            while (rs.next()) {
                String name = rs.getString(1);
                System.out.println("Uzytkownik: " + name);
            }
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
    }

    public void createTable() {
        try {
            connect();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.executeUpdate(
                    "CREATE TABLE tabela1 ("
                            + "priKey INT NOT NULL AUTO_INCREMENT, "
                            + "nazwisko VARCHAR(64), PRIMARY KEY (priKey))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addUser() {
        try {
            connect();
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            stmt.executeUpdate(
                    "INSERT INTO tabela1 (nazwisko) "
                            + "values ('Wilk')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void listBooks() {
        try {
            connect();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM books");
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    result.append(rsmd.getColumnName(i) + ": " + rs.getString(i) + "  ");
                }
                System.out.println(result.toString());
            }
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
    }

    public void serchAuthor(String name) {
        try {
            connect();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM books where author LIKE '%" + name + "'");

            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                StringBuilder result = new StringBuilder();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    result.append(rsmd.getColumnName(i) + ": " + rs.getString(i) + "  ");
                }
                System.out.println(result.toString());
            }
        } catch (SQLException ex) {
            // handle any errors

        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
    }


    public void addBook(String isbn, String title, String author, String year) {
        try {
            connect();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO books (isbn, title, author, year) VALUES  (?, ?, ?, ?)");
            pstmt.setString(1, isbn);
            pstmt.setString(2, title);
            pstmt.setString(3, author);
            pstmt.setString(4, year);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }

    }
}
