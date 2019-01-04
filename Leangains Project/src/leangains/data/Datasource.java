package leangains.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import leangains.model.TrainingLog;
import leangains.model.User;
import leangains.model.WeightTracker;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Datasource {

    // Static final fields for the Weight Tracker.
    public static final String DB_NAME = "src/leangains/data/db.sqlite";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_NAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_GENDER = "gender";
    public static final String COLUMN_USER_AGE = "age";
    public static final String COLUMN_USER_HEIGHT = "height";
    public static final String COLUMN_USER_BODYFAT = "bodyfat";
    public static final String COLUMN_USER_MUSCLEMASS = "musclemass";
    public static final String COLUMN_USER_ACTIVITY = "activity";


    public static final String TABLE_WEIGHTTRACKER = "weighttracker";
    public static final String COLUMN_WEIGHTTRACKER_ID = "_id";
    public static final String COLUMN_WEIGHTTRACKER_USER = "username";
    public static final String COLUMN_WEIGHTTRACKER_DATE = "date";
    public static final String COLUMN_WEIGHTTRACKER_WEIGHT = "weight";
    public static final String COLUMN_WEIGHTTRACKER_WAIST = "waist";
    public static final String COLUMN_WEIGHTTRACKER_ABDOMINAL = "abdominal";
    public static final String COLUMN_WEIGHTTRACKER_HIPS = "hips";
    public static final String COLUMN_WEIGHTTRACKER_CHEST = "chest";
    public static final String COLUMN_WEIGHTTRACKER_BICEPS = "biceps";
    public static final String COLUMN_WEIGHTTRACKER_NECK = "neck";

    public static final String TABLE_TRAININGLOG = "traininglog";
    public static final String COLUMN_TRAININGLOG_ID = "_id";
    public static final String COLUMN_TRAININGLOG_USER = "username";
    public static final String COLUMN_TRAININGLOG_DATE = "date";
    public static final String COLUMN_TRAININGLOG_EXERCISE = "exercise";
    public static final String COLUMN_TRAININGLOG_WEIGHTFIRSTSET = "weightFirstSet";
    public static final String COLUMN_TRAININGLOG_WEIGHTSECONDSET = "weightSecondSet";
    public static final String COLUMN_TRAININGLOG_WEIGHTTHIRDSET = "weightThirdSet";
    public static final String COLUMN_TRAININGLOG_WEIGHTFOURTHSET = "weightFourthSet";
    public static final String COLUMN_TRAININGLOG_WEIGHTFIFTHSET = "weightFifthSet";
    public static final String COLUMN_TRAININGLOG_REPSFIRSTSET = "repsFirstSet";
    public static final String COLUMN_TRAININGLOG_REPSSECONDSET = "repsSecondSet";
    public static final String COLUMN_TRAININGLOG_REPSTHIRDSET = "repsThirdSet";
    public static final String COLUMN_TRAININGLOG_REPSFOURTHSET = "repsFourthSet";
    public static final String COLUMN_TRAININGLOG_REPSFIFTHSET = "repsFifthSet";
    public static final String COLUMN_TRAININGLOG_COMMENTS = "comments";

    // SQLLite statement to create a table for users.
    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_NAME + " TEXT," +
            COLUMN_USER_PASSWORD + " TEXT, " +
            COLUMN_USER_GENDER + " TEXT, " +
            COLUMN_USER_AGE + " INTEGER, " +
            COLUMN_USER_HEIGHT + " INTEGER, " +
            COLUMN_USER_BODYFAT + " INTEGER, " +
            COLUMN_USER_MUSCLEMASS + " INTEGER, " +
            COLUMN_USER_ACTIVITY + " INTEGER)";

    // SQLLite statement to create a table for weight tracker.
    public static final String CREATE_WEIGHTTRACKER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_WEIGHTTRACKER + "(" +
            COLUMN_WEIGHTTRACKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_WEIGHTTRACKER_USER + " TEXT, " +
            COLUMN_WEIGHTTRACKER_DATE + " TEXT, " +
            COLUMN_WEIGHTTRACKER_WEIGHT + " REAL, " +
            COLUMN_WEIGHTTRACKER_WAIST + " INTEGER, " +
            COLUMN_WEIGHTTRACKER_ABDOMINAL + " INTEGER, " +
            COLUMN_WEIGHTTRACKER_HIPS + " INTEGER, " +
            COLUMN_WEIGHTTRACKER_CHEST + " INTEGER, " +
            COLUMN_WEIGHTTRACKER_BICEPS + " INTEGER, " +
            COLUMN_WEIGHTTRACKER_NECK + " INTEGER);";

    // SQLLite statement to create a table for the training log.
    public static final String CREATE_TRAININGLOG_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TRAININGLOG + "(" +
            COLUMN_TRAININGLOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_TRAININGLOG_USER + " TEXT," +
            COLUMN_TRAININGLOG_EXERCISE + " TEXT," +
            COLUMN_TRAININGLOG_DATE + " TEXT," +
            COLUMN_TRAININGLOG_WEIGHTFIRSTSET + " REAL," +
            COLUMN_TRAININGLOG_WEIGHTSECONDSET + " REAL," +
            COLUMN_TRAININGLOG_WEIGHTTHIRDSET + " REAL," +
            COLUMN_TRAININGLOG_WEIGHTFOURTHSET + " REAL," +
            COLUMN_TRAININGLOG_WEIGHTFIFTHSET + " REAL," +
            COLUMN_TRAININGLOG_REPSFIRSTSET + " INTEGER," +
            COLUMN_TRAININGLOG_REPSSECONDSET + " INTEGER," +
            COLUMN_TRAININGLOG_REPSTHIRDSET + " INTEGER," +
            COLUMN_TRAININGLOG_REPSFOURTHSET + " INTEGER," +
            COLUMN_TRAININGLOG_REPSFIFTHSET + " INTEGER," +
            COLUMN_TRAININGLOG_COMMENTS + " TEXT);";

    // SQLLite statement to insert data into users table.
    public static final String INSERT_USER = "INSERT INTO " + TABLE_USERS + "( " +
            COLUMN_USER_NAME + ", " +
            COLUMN_USER_PASSWORD + ", " +
            COLUMN_USER_GENDER + ", " +
            COLUMN_USER_AGE + ", " +
            COLUMN_USER_HEIGHT + ", " +
            COLUMN_USER_BODYFAT + ", " +
            COLUMN_USER_MUSCLEMASS + ", " +
            COLUMN_USER_ACTIVITY + ") VALUES (?,?,?,?,?,?,?,?)";

    // SQLLite statement to insert data into weight tracker table.
    public static final String INSERT_WEIGHTTRACKER = "INSERT INTO " + TABLE_WEIGHTTRACKER + "(" +
            COLUMN_WEIGHTTRACKER_ID + ", " +
            COLUMN_WEIGHTTRACKER_USER + ", " +
            COLUMN_WEIGHTTRACKER_DATE + ", " +
            COLUMN_WEIGHTTRACKER_WEIGHT + ", " +
            COLUMN_WEIGHTTRACKER_WAIST + ", " +
            COLUMN_WEIGHTTRACKER_ABDOMINAL + ", " +
            COLUMN_WEIGHTTRACKER_HIPS + ", " +
            COLUMN_WEIGHTTRACKER_CHEST + ", " +
            COLUMN_WEIGHTTRACKER_BICEPS + ", " +
            COLUMN_WEIGHTTRACKER_NECK + ") VALUES (?,?,?,?,?,?,?,?,?,?)";

    // SQLLite statement to insert data into training log table.
    public static final String INSERT_TRAININGLOG = "INSERT INTO " + TABLE_TRAININGLOG + "(" +
            COLUMN_TRAININGLOG_ID + ", " +
            COLUMN_TRAININGLOG_USER + ", " +
            COLUMN_TRAININGLOG_EXERCISE + ", " +
            COLUMN_TRAININGLOG_DATE + ", " +
            COLUMN_TRAININGLOG_WEIGHTFIRSTSET + ", " +
            COLUMN_TRAININGLOG_WEIGHTSECONDSET + ", " +
            COLUMN_TRAININGLOG_WEIGHTTHIRDSET + ", " +
            COLUMN_TRAININGLOG_WEIGHTFOURTHSET + ", " +
            COLUMN_TRAININGLOG_WEIGHTFIFTHSET + ", " +
            COLUMN_TRAININGLOG_REPSFIRSTSET + ", " +
            COLUMN_TRAININGLOG_REPSSECONDSET + ", " +
            COLUMN_TRAININGLOG_REPSTHIRDSET + ", " +
            COLUMN_TRAININGLOG_REPSFOURTHSET + ", " +
            COLUMN_TRAININGLOG_REPSFIFTHSET + ", " +
            COLUMN_TRAININGLOG_COMMENTS + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    // SQLLite statement to query users from the database.
    public static final String QUERY_USERS = "SELECT " + COLUMN_USER_ID + " FROM " +
            TABLE_USERS + " WHERE " + COLUMN_USER_NAME + " =?";

    // SQLLite statement to query weight tracker records from the database.
    public static final String QUERY_WEIGHTTRACKER = "SELECT " + COLUMN_WEIGHTTRACKER_ID + " FROM " +
            TABLE_WEIGHTTRACKER + " WHERE " + COLUMN_WEIGHTTRACKER_USER + " =?" + " ORDER BY " + COLUMN_WEIGHTTRACKER_DATE + " DESC";

    // SQLLite statement to query training log records from the database.
    public static final String QUERY_TRAININGLOG = "SELECT " + COLUMN_WEIGHTTRACKER_ID + " FROM " +
            TABLE_TRAININGLOG + " WHERE " + COLUMN_TRAININGLOG_USER + " =?" + " ORDER BY " + COLUMN_WEIGHTTRACKER_DATE + " DESC";

    // SQLLite statement to update users table.
    public static final String UPDATE_USER = "UPDATE " + TABLE_USERS + " SET " +
            COLUMN_USER_NAME + "=?," +
            COLUMN_USER_PASSWORD + "=?," +
            COLUMN_USER_GENDER + "=?," +
            COLUMN_USER_AGE + "=?," +
            COLUMN_USER_HEIGHT + "=?," +
            COLUMN_USER_BODYFAT + "=?," +
            COLUMN_USER_MUSCLEMASS + "=?," +
            COLUMN_USER_ACTIVITY + "=?" +
            " WHERE " + COLUMN_USER_NAME + "=\"default\"";

    /**
     * Constructor.
     */
    private Datasource() {
    }

    /**
     * Reference to Datasource. We can use it in different classes to open connection, insert, query tables etc.
     */
    public static Datasource instance = new Datasource();

    /**
     * Getter for the Datasource instance.
     *
     * @return
     */
    public static Datasource getInstance() {
        return instance;
    }

    private Connection conn;

    private PreparedStatement createUsersTable;
    private PreparedStatement insertIntoUsers;
    private PreparedStatement queryUsers;
    private PreparedStatement updateUser;

    private PreparedStatement createWeightTrackerTable;
    private PreparedStatement insertIntoWeightTracker;
    private PreparedStatement queryWeightTracker;

    private PreparedStatement createTrainingLogTable;
    private PreparedStatement insertIntoTrainingLog;
    private PreparedStatement queryTrainingLog;

    /**
     * Open connection, create tables and execute prepared statements.
     *
     * @return
     */
    public boolean open() {
        try {
            // Open connection.
            conn = DriverManager.getConnection(CONNECTION_STRING);

            // Statements for the USERS_TABLE.
            createUsersTable = conn.prepareStatement(CREATE_USER_TABLE);
            createUsersTable.execute(); // remember to execute the statement!
            insertIntoUsers = conn.prepareStatement(INSERT_USER);
            queryUsers = conn.prepareStatement(QUERY_USERS);
            updateUser = conn.prepareStatement(UPDATE_USER);

            // Statements for the WEIGHTTRACKER_TABLE.
            createWeightTrackerTable = conn.prepareStatement(CREATE_WEIGHTTRACKER_TABLE);
            createWeightTrackerTable.execute(); // remember to execut the statement!
            insertIntoWeightTracker = conn.prepareStatement(INSERT_WEIGHTTRACKER);
            queryWeightTracker = conn.prepareStatement(QUERY_WEIGHTTRACKER);

            // Statements for the TRAININGLOG_TABLE.
            createTrainingLogTable = conn.prepareStatement(CREATE_TRAININGLOG_TABLE);
            createTrainingLogTable.execute();
            insertIntoTrainingLog = conn.prepareStatement(INSERT_TRAININGLOG);
            queryTrainingLog = conn.prepareStatement(QUERY_TRAININGLOG);

            return true;
        } catch (SQLException e) {
            System.out.println("Connection error: ");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Close database connections.
     */
    public void close() {
        try {
            if (createUsersTable != null) {
                createUsersTable.close();
            }

            if (insertIntoUsers != null) {
                insertIntoUsers.close();
            }

            if (queryUsers != null) {
                queryUsers.close();
            }

            if (updateUser != null) {
                updateUser.close();
            }

            if (createWeightTrackerTable != null) {
                createWeightTrackerTable.close();
            }

            if (insertIntoWeightTracker != null) {
                insertIntoWeightTracker.close();
            }

            if (queryWeightTracker != null) {
                queryWeightTracker.close();
            }

            if (createTrainingLogTable != null) {
                createTrainingLogTable.close();
            }

            if (insertIntoTrainingLog != null) {
                insertIntoTrainingLog.close();
            }

            if (queryTrainingLog != null) {
                queryTrainingLog.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close the connection: ");
            e.printStackTrace();
        }
    }

    /**
     * Insert data into users table.
     *
     * @return method returns generatedKey()
     */
    public int insertUser(String userName, String password, String gender, int age, int height,
                          int bodyFat, int muscleMass, int activity) throws SQLException {
        queryUsers.setString(1, userName); // Select a record with the specified user name.

        ResultSet results = queryUsers.executeQuery(); // Execute prepared statement and get all of the users.

        // If the user exists just return his id.
        if (results.next()) {
            return results.getInt(1);
        } else {
            // If the user doesn't exist insert data into the database.
            insertIntoUsers.setString(1, userName);
            insertIntoUsers.setString(2, password);
            insertIntoUsers.setString(3, gender);
            insertIntoUsers.setInt(4, age);
            insertIntoUsers.setInt(5, height);
            insertIntoUsers.setInt(6, bodyFat);
            insertIntoUsers.setInt(7, muscleMass);
            insertIntoUsers.setInt(8, activity);

            // Execute prepared insert statement and return how many rows were affected.
            int affectedRows = insertIntoUsers.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Could not insert a user."); // If no rows were affected throw an error.
            }

            // Generate keys from the users table.
            ResultSet generatedKeys = insertIntoUsers.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Could not get _id for user.");
            }
        }
    }

    /**
     * Select a user from database.
     */
    public User queryUser(String userName) {
        // Prepare statement to select user with the specific user name.
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_USERS);
        sb.append(" WHERE ");
        sb.append(COLUMN_USER_NAME);
        sb.append("=\"");
        sb.append(userName);
        sb.append("\"");

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) { // Create a statement and execute it.

            User user = new User(); // Create temporary User object.

            // Set data in the user object from the statement we earlier prepared.
            user.setGender(results.getString(4));
            user.setAge(results.getInt(5));
            user.setHeight(results.getInt(6));
            user.setBodyFat(results.getInt(7));
            user.setMuscleMass(results.getInt(8));
            user.setActivity(results.getInt(9));

            return user;
        } catch (SQLException e) {
            System.out.println("Could not select the user: ");
            return null;
        }

    }

    /**
     * Update user in the database.
     *
     * @return return true if the record was updated and false if not
     */
    public boolean updateUser(String userName, String password, String gender, int age, int height,
                              int bodyFat, int muscleMass, int activity) {
        try {
            updateUser.setString(1, userName);
            updateUser.setString(2, password);
            updateUser.setString(3, gender);
            updateUser.setInt(4, age);
            updateUser.setInt(5, height);
            updateUser.setInt(6, bodyFat);
            updateUser.setInt(7, muscleMass);
            updateUser.setInt(8, activity);

            int affectedRecords = updateUser.executeUpdate(); // Exectute statement and return affected rows.

            return affectedRecords == 1; // Return true if we have updated the record.
        } catch (SQLException e) {
            System.out.println("Update failed:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Insert data into WeightTracker table.
     *
     * @return generated key for the record
     */
    public int insertWeightTracker(int id, String userName, LocalDate date, double weight, int waist, int abdominal, int hips,
                                   int chest, int biceps, int neck) throws SQLException {
        // We pass an integer value to VALUES (?,?,?,?,?,?,?,?,?,?) in the prepared INSERT_WEIGHTTRACKER statement.
        // ? is the index of the column to be updated, starts with 1.
        insertIntoWeightTracker.setInt(1, id);
        insertIntoWeightTracker.setString(2, userName);
        insertIntoWeightTracker.setString(3, date.toString());
        insertIntoWeightTracker.setDouble(4, weight);
        insertIntoWeightTracker.setInt(5, waist);
        insertIntoWeightTracker.setInt(6, abdominal);
        insertIntoWeightTracker.setInt(7, hips);
        insertIntoWeightTracker.setInt(8, chest);
        insertIntoWeightTracker.setInt(9, biceps);
        insertIntoWeightTracker.setInt(10, neck);

        int affectedRows = insertIntoWeightTracker.executeUpdate(); // Execute statement and return rows affected.

        // If didn't affect any rows in the table then we gonna throw an exception.
        if (affectedRows != 1) {
            throw new SQLException("Could not insert a WeightTracker record");
        }

        // Return generated key for the record.
        ResultSet generatedKeys = insertIntoWeightTracker.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Could not get _id for user.");
        }
    }

    /**
     * Select WeightTracker from database.
     *
     * @return ObservableList of type WeightTracker.
     */
    public ObservableList<WeightTracker> queryWeightTracker(String userName) {
        // Prepare statement to select all of the rows for the specific user.
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_WEIGHTTRACKER);
        sb.append(" WHERE ");
        sb.append(COLUMN_WEIGHTTRACKER_USER);
        sb.append("=\"");
        sb.append(userName);
        sb.append("\"");

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            ObservableList<WeightTracker> weightTrackers = FXCollections.observableArrayList(); // Create a new temporary list to later return it from the method.

            while (results.next()) {
                WeightTracker weightTracker = new WeightTracker(); // Temporary WeightTracker object.

                // Get the data from the database and set values in the WeightTracker object.
                weightTracker.setId(results.getInt(1));
                weightTracker.setUserName(results.getString(2));

                // Parse a date from the String into LocalDate.
                String newDate = results.getString(3);
                LocalDate date = LocalDate.parse(newDate);
                weightTracker.setDate(date);

                // ... continue adding data.
                weightTracker.setWeight(results.getDouble(4));
                weightTracker.setWaist(results.getInt(5));
                weightTracker.setAbdominal(results.getInt(6));
                weightTracker.setHips(results.getInt(7));
                weightTracker.setChest(results.getInt(8));
                weightTracker.setNeck(results.getInt(9));
                weightTracker.setBiceps(results.getInt(10));

                // Add new WeightTracker object into weightTrackers list.
                weightTrackers.add(weightTracker);
            }
            return weightTrackers;
        } catch (SQLException e) {
            System.out.println("Could not select the user: ");
            return null;
        }
    }

    /**
     * Update WeightTracker in the database;
     *
     * @return return true if the user was added or false if wasn't
     */
    public boolean updateWeightTracker(int id, LocalDate date, double weight, int waist, int abdominal, int hips,
                                       int chest, int biceps, int neck) {

        // Format date from the LocalDate into a String instance.
        LocalDate newDate = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String stringDate = newDate.format(formatter);

        // Create a statement to update the record in the Weight Tracker table. It should have been done in  the
        // PreparedStatement on the top of the file but I didn't know how to pass the id parameter.
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(TABLE_WEIGHTTRACKER + " SET ");
        sb.append(COLUMN_WEIGHTTRACKER_DATE + "=\"" + stringDate + "\",");
        sb.append(COLUMN_WEIGHTTRACKER_WEIGHT + "=" + weight + ",");
        sb.append(COLUMN_WEIGHTTRACKER_WAIST + "=" + waist + ",");
        sb.append(COLUMN_WEIGHTTRACKER_ABDOMINAL + "=" + abdominal + ",");
        sb.append(COLUMN_WEIGHTTRACKER_HIPS + "=" + hips + ",");
        sb.append(COLUMN_WEIGHTTRACKER_CHEST + "=" + chest + ",");
        sb.append(COLUMN_WEIGHTTRACKER_BICEPS + "=" + biceps + ",");
        sb.append(COLUMN_WEIGHTTRACKER_NECK + "=" + neck);
        sb.append(" WHERE " + COLUMN_WEIGHTTRACKER_ID + "=" + id);

        // Execute statement.
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            return true;
        } catch (SQLException e) {
            System.out.println("Update failed:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete the record from the Weight Tracker table.
     *
     * @param id of the WeightTracker record to be deleted.
     */
    public void deleteWeightTracker(int id) {

        // Prepare statement.
        StringBuilder sb = new StringBuilder("DELETE FROM " + TABLE_WEIGHTTRACKER);
        sb.append(" WHERE _id=\'" + id + "\'");

        // Execute statement.
        try (Statement statement = conn.createStatement()) {
            statement.execute(sb.toString());
        } catch (SQLException e) {
            System.out.println("Could not delete the record.");
            e.printStackTrace();
        }
    }

    /**
     * Insert data into TrainingLog table.
     *
     * @return generated key for the record
     */
    public int insertTrainingLog(int id, String userName, LocalDate date, String exercise, double weightFirstSet,
                                 double weightSecondSet, double weightThirdSet, double weightFourthSet,
                                 double weightFifthSet, int repsFirstSet, int repsSecondSet, int repsThirdSet,
                                 int repsFourthSet, int repsFifthSet, String comment) throws SQLException {
        insertIntoTrainingLog.setInt(1, id);
        insertIntoTrainingLog.setString(2, userName);
        insertIntoTrainingLog.setString(3, exercise);
        insertIntoTrainingLog.setString(4, date.toString());
        insertIntoTrainingLog.setDouble(5, weightFirstSet);
        insertIntoTrainingLog.setDouble(6, weightSecondSet);
        insertIntoTrainingLog.setDouble(7, weightThirdSet);
        insertIntoTrainingLog.setDouble(8, weightFourthSet);
        insertIntoTrainingLog.setDouble(9, weightFifthSet);
        insertIntoTrainingLog.setInt(10, repsFirstSet);
        insertIntoTrainingLog.setInt(11, repsSecondSet);
        insertIntoTrainingLog.setInt(12, repsThirdSet);
        insertIntoTrainingLog.setInt(13, repsFourthSet);
        insertIntoTrainingLog.setInt(14, repsFifthSet);
        insertIntoTrainingLog.setString(15, comment);

        int affectedRows = insertIntoTrainingLog.executeUpdate(); // Execute statement and return rows affected.

        // If didn't affect any rows in the table then we gonna throw an exception.
        if (affectedRows != 1) {
            throw new SQLException("Could not insert a WeightTracker record");
        }
        // Return generated key for the record.
        ResultSet generatedKeys = insertIntoTrainingLog.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        } else {
            throw new SQLException("Could not get _id for user.");
        }
    }

    /**
     * Select TrainingLog from database.
     *
     * @return ObservableList of type TrainingLog.
     */
    public ObservableList<TrainingLog> queryTrainingLog(String userName) {
        // Prepare statement to select all of the rows for the specific user.
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_TRAININGLOG);
        sb.append(" WHERE ");
        sb.append(COLUMN_TRAININGLOG_USER);
        sb.append("=\"");
        sb.append(userName);
        sb.append("\"");

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            ObservableList<TrainingLog> trainingLogs = FXCollections.observableArrayList(); // Create a new temporary list to later return it from the method.

            while (results.next()) {
                TrainingLog trainingLog = new TrainingLog(); // Temporary TrainingLog object.

                // Get the data from the database and set values in the TrainingLog object.
                trainingLog.setId(results.getInt(1));
                trainingLog.setUserName(results.getString(2));
                trainingLog.setExercise(results.getString(3));

                // Parse a date from the String into LocalDate.
                String newDate = results.getString(4);
                LocalDate date = LocalDate.parse(newDate);
                trainingLog.setDate(date);

                // ... continue adding data.
                trainingLog.setWeightFirstSet(results.getInt(5));
                trainingLog.setWeightSecondSet(results.getDouble(6));
                trainingLog.setWeightThirdSet(results.getDouble(7));
                trainingLog.setWeightFourthSet(results.getDouble(8));
                trainingLog.setWeightFifthSet(results.getDouble(9));
                trainingLog.setRepsFirstSet(results.getInt(10));
                trainingLog.setRepsSecondSet(results.getInt(11));
                trainingLog.setRepsThirdSet(results.getInt(12));
                trainingLog.setRepsFourthSet(results.getInt(13));
                trainingLog.setRepsFifthSet(results.getInt(14));
                trainingLog.setComments(results.getString(15));

                // Add new TrainingLog object into trainingLogs list.
                trainingLogs.add(trainingLog);
            }
            return trainingLogs;
        } catch (SQLException e) {
            System.out.println("Could not select the user: ");
            return null;
        }
    }

    /**
     * Update TrainingLog in the database;
     *
     * @return return true if the user was added or false if wasn't
     */
    public boolean updateTrainingLog(int id, String userName, String exercise, LocalDate date, double weightFirstSet,
                                     double weightSecondSet, double weightThirdSet, double weightFourthSet,
                                     double weightFifthSet, int repsFirstSet, int repsSecondSet, int repsThirdSet,
                                     int repsFourthSet, int repsFifthSet, String comment) {

        // Format date from the LocalDate into a String instance.
        LocalDate newDate = date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String stringDate = newDate.format(formatter);

        // Create a statement to update the record in the TrainingLog table. It should have been done in  the
        // PreparedStatement on the top of the file but I didn't know how to pass the id parameter.
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(TABLE_TRAININGLOG + " SET ");
        sb.append(COLUMN_TRAININGLOG_EXERCISE + "=" + exercise + ",");
        sb.append(COLUMN_TRAININGLOG_DATE + "=\"" + stringDate + "\",");
        sb.append(COLUMN_TRAININGLOG_WEIGHTFIRSTSET + "=" + weightFirstSet + ",");
        sb.append(COLUMN_TRAININGLOG_WEIGHTSECONDSET + "=" + weightSecondSet + ",");
        sb.append(COLUMN_TRAININGLOG_WEIGHTTHIRDSET + "=" + weightThirdSet + ",");
        sb.append(COLUMN_TRAININGLOG_WEIGHTFOURTHSET + "=" + weightFourthSet + ",");
        sb.append(COLUMN_TRAININGLOG_WEIGHTFIFTHSET + "=" + weightFifthSet + ",");
        sb.append(COLUMN_TRAININGLOG_REPSFIRSTSET + "=" + repsFirstSet + ",");
        sb.append(COLUMN_TRAININGLOG_REPSSECONDSET + "=" + repsSecondSet);
        sb.append(COLUMN_TRAININGLOG_REPSTHIRDSET + "=" + repsThirdSet);
        sb.append(COLUMN_TRAININGLOG_REPSFOURTHSET + "=" + repsFourthSet);
        sb.append(COLUMN_TRAININGLOG_REPSFIFTHSET + "=" + repsFifthSet);
        sb.append(COLUMN_TRAININGLOG_COMMENTS + "=" + comment);
        sb.append(" WHERE " + COLUMN_TRAININGLOG_ID + "=" + id);

        // Execute statement.
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate(sb.toString());
            return true;
        } catch (SQLException e) {
            System.out.println("Update failed:");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete the record from the TrainingLog table.
     *
     * @param id of the TrainingLog record to be deleted.
     */
    public void deleteTrainingLog(int id) {

        // Prepare statement.
        StringBuilder sb = new StringBuilder("DELETE FROM " + TABLE_TRAININGLOG);
        sb.append(" WHERE _id=\'" + id + "\'");

        // Execute statement.
        try (Statement statement = conn.createStatement()) {
            statement.execute(sb.toString());
        } catch (SQLException e) {
            System.out.println("Could not delete the record.");
            e.printStackTrace();
        }
    }
}