import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "Root@0704";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\nHotel Management System");
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservation");
                System.out.println("5. Delete Reservation");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> reserveRoom(con, sc);
                    case 2 -> viewReservations(con);
                    case 3 -> getRoomNum(con, sc);
                    case 4 -> updateReservation(con, sc);
                    case 5 -> deleteReservation(con, sc);
                    case 0 -> {
                        exit();
                        sc.close();
                        con.close();
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reserveRoom(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Guest Name: ");
            String guestName = sc.next();

            System.out.print("Enter Room Number: ");
            int roomNumber = sc.nextInt();

            System.out.print("Enter Contact Number: ");
            String contactNumber = sc.next();

            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) " +
                    "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";

            Statement st = con.createStatement();
            int rows = st.executeUpdate(sql);

            if (rows > 0) System.out.println("Reservation Successful");
            else System.out.println("Reservation Failed");

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservations(Connection con) {
        String sql = "SELECT * FROM reservations";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            System.out.println("Current Reservations:");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest Name      | Room Number   | Contact Number      | Reservation Date        |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            while (rs.next()) {
                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-23s |\n",
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_number"),
                        rs.getString("contact_number"),
                        rs.getTimestamp("reservation_date").toString());
            }

            System.out.println("+----------------+-----------------+---------------+----------------------+-------------------------+");

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void getRoomNum(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Reservation ID: ");
            int reservationId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Guest Name: ");
            String guestName = sc.nextLine();

            String sql = "SELECT room_number FROM reservations WHERE reservation_id = " +
                    reservationId + " AND guest_name = '" + guestName + "'";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next())
                System.out.println("Room Number: " + rs.getInt("room_number"));
            else
                System.out.println("Reservation not found");

            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void updateReservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Reservation ID: ");
            int reservationId = sc.nextInt();
            sc.nextLine();

            if (!reservationExists(con, reservationId)) {
                System.out.println("Invalid Reservation ID");
                return;
            }

            System.out.print("Enter New Guest Name: ");
            String guestName = sc.nextLine();

            System.out.print("Enter New Room Number: ");
            int roomNumber = sc.nextInt();

            System.out.print("Enter New Contact Number: ");
            String contactNumber = sc.next();

            String sql = "UPDATE reservations SET guest_name = '" + guestName +
                    "', room_number = " + roomNumber +
                    ", contact_number = '" + contactNumber +
                    "' WHERE reservation_id = " + reservationId;

            Statement st = con.createStatement();
            int rows = st.executeUpdate(sql);

            if (rows > 0) System.out.println("Reservation Updated");
            else System.out.println("Update Failed");

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteReservation(Connection con, Scanner sc) {
        try {
            System.out.print("Enter Reservation ID: ");
            int reservationId = sc.nextInt();

            if (!reservationExists(con, reservationId)) {
                System.out.println("Reservation Not Found");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationId;

            Statement st = con.createStatement();
            int rows = st.executeUpdate(sql);

            if (rows > 0) System.out.println("Reservation Deleted");
            else System.out.println("Delete Failed");

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection con, int id) {
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT reservation_id FROM reservations WHERE reservation_id = " + id);
            boolean exists = rs.next();
            rs.close();
            st.close();
            return exists;
        } catch (SQLException e) {
            return false;
        }
    }

    private static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.print(".");
        }
        System.out.println("\nThank You For Using Hotel Reservation System");
    }
}
