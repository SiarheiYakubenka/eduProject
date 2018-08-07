package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.services.ConstantsDB;
import by.gsu.epamlab.model.services.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskImplDB implements ITaskDAO {
    private static final Logger LOGGER = Logger.getLogger(UserImplDB.class.getName());

    @Override
    public List<Task> getList(String userName) {
        return getList(userName, ConstantsSQL.KEY_ID);
    }

    public List<Task> getList(String userName, String condition) {
        List<Task> tasks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBManager.getConnection();
            LOGGER.info(ConstantsDB.CONNECT_SUCCESS);
            statement = connection.prepareStatement("select * from " + userName
                    + "_tasks where " + condition);
            resultSet = statement.executeQuery();
            String description;
            Date completionsDate;
            boolean isFixed, isDelMarked;
            while (resultSet.next()) {
                description = resultSet.getString(ConstantsSQL.KEY_DESCRIPTION);
                completionsDate = resultSet.getDate(ConstantsSQL.KEY_DATE);
                isFixed = resultSet.getBoolean(ConstantsSQL.KEY_IS_FIXED);
                isDelMarked = resultSet.getBoolean(ConstantsSQL.KEY_IS_DEL);
                tasks.add(new Task(description, completionsDate, isFixed, isDelMarked));
            }
            return tasks;
        } catch (DaoException | SQLException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            return null;
        } finally {
            DBManager.closeResultSets(resultSet);
            DBManager.closeStatements(statement);
            DBManager.closeConnection(connection);
        }
    }

    @Override
    public void add(String userName, Task task) {
        actionTask(userName, task, ConstantsSQL.ADD_TASK);
    }

    @Override
    public void edit(String userName, Task oldTask, Task newTask) {
        Connection connection = null;
        PreparedStatement statementTask = null;
        PreparedStatement statementId = null;
        try {
            connection = DBManager.getConnection();
            statementTask = connection.prepareStatement(ConstantsSQL.UPDATE_TASK);
            statementId = connection.prepareStatement(ConstantsSQL.TASK_ID);
            int taskId = getId(userName, oldTask, statementId);
            statementTask.setString(ConstantsSQL.INDEX_ONE, userName);
            statementTask.setString(ConstantsSQL.INDEX_TWO, newTask.getDescription());
            statementTask.setDate(ConstantsSQL.INDEX_THREE, newTask.getCompletionsDate());
            statementTask.setBoolean(ConstantsSQL.INDEX_FOUR, newTask.isFixed());
            statementTask.setBoolean(ConstantsSQL.INDEX_FIVE, newTask.isDelMarked());
            statementTask.setInt(ConstantsSQL.INDEX_SIX, taskId);

            statementTask.executeUpdate();


        } catch (DaoException | SQLException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(e);
        } finally {
            DBManager.closeStatements(statementTask, statementId);
            DBManager.closeConnection(connection);
        }
    }

    @Override
    public void delete(String userName, Task task) {
        actionTask(userName, task, ConstantsSQL.DELETE_TASK);
    }


    private int getId(String userName, Task task, PreparedStatement statement) throws SQLException {
        int id = 0;
        ResultSet rs = null;
        try{
            statement.setString(ConstantsSQL.INDEX_ONE, userName);
            statement.setString(ConstantsSQL.INDEX_TWO, task.getDescription());
            statement.setDate(ConstantsSQL.INDEX_THREE, task.getCompletionsDate());
            rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt(ConstantsSQL.INDEX_ONE);
            }
            return id;
        } finally {
            DBManager.closeResultSets(rs);
        }

    }

    private void actionTask(String userName, Task task, String sql) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBManager.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(ConstantsSQL.INDEX_ONE, userName);
            statement.setString(ConstantsSQL.INDEX_TWO, task.getDescription());
            statement.setDate(ConstantsSQL.INDEX_THREE, task.getCompletionsDate());

            statement.executeUpdate();


        } catch (DaoException | SQLException | IllegalArgumentException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new DaoException(e);
        } finally {
            DBManager.closeStatements(statement);
            DBManager.closeConnection(connection);
        }
    }

}
