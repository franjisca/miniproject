package miniproject.camping.user.controller;

import java.sql.SQLException;
import java.util.List;
import miniproject.camping.user.model.dao.UserDAO;
import miniproject.camping.user.model.vo.User;

public class UserController {
 UserDAO userDao = new UserDAO();
 
 public List<User> user_Info() {
 return userDao.User_Info();
 } 
 public User one_User(String id) {
return userDao.one_User(id);	 
 } 
 public int insertUser(User user) {
 return userDao.insertUser(user);
 }
public int updateUser(User user) {
 return userDao.update(user);
 }
public int deleteUser(String id) throws ClassNotFoundException {
return userDao.delete(id);
}
public int logIn(String id, String pw) {
return userDao.logIn(id, pw);
}
 public int signUp(String id) {
return userDao.dao_signUp(id);
 }
}