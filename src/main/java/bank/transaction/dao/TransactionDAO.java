package bank.transaction.dao;


import bank.transaction.models.BankAccount;
import bank.transaction.models.Transaction;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDAO {
    private  final String URL = "jdbc:mysql://localhost:3306/bank.transaction";
    private  final String USERNAME = "root";
    private  final String PASSWORD = "";

    private Connection connection;


    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public List<Transaction> index () {
        List<Transaction> bankAccounts = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM transaction ";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()) {
                Transaction transaction = new Transaction();

                transaction.setTransactionType(resultSet.getString("TransactionType"));
                transaction.setTransactionStatus(resultSet.getString("TransactionStatus"));
                transaction.setDate(resultSet.getString("date"));

                bankAccounts.add(transaction);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bankAccounts;
    }

    public Transaction show(int id) {
        Transaction transaction = null;
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM transaction WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            transaction = new Transaction();
            transaction.setId(resultSet.getInt("id"));
            transaction.setTransactionType(resultSet.getString("TransactionType"));
            transaction.setTransactionStatus(resultSet.getString("TransactionStatus"));
            transaction.setDate(resultSet.getString("date"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return transaction;
    }


    public void save(Transaction transaction) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO transaction VALUES(10,?,?,? )");
            preparedStatement.setString(1, transaction.getTransactionType());
            preparedStatement.setString(2, transaction.getTransactionStatus());
            preparedStatement.setString(3,transaction.getDate());

            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }






}
