package DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CustomerDAO extends DAO {

    int getID(String divName) throws SQLException;

}
