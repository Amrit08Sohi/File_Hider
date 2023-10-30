package Service;

import Model.User;
import dao.UserDAO;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(User user) {
        try {
            if(UserDAO.isUserExist(user.getEmail())) {
                return 0;
            } else {
                return UserDAO.saveUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
