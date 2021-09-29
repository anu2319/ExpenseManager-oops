//commit
package com.anu.expenseManager;//User.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class User {
    private int id;
    private String email, name;

    public User(int id, String email, String name)
    {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getEmail()
    {
        return email;
    }
}
class LoginManager
{
    static int currentId = 0;
    static Map<Integer, String> userIdVsPassword = new HashMap<>();
    static Map<Integer, User> userIdVsUser = new HashMap<>();
    public static Map<String, User> userEmailVsUser = new HashMap<>();
    public static Map<Integer, User> userList = new HashMap<>();

    public static User signup(String name, String email, String password)
    {
        User user = new User(currentId++, name, email);
        userIdVsPassword.put(user.getId(), password);
        userIdVsUser.put(user.getId(), user);
        userEmailVsUser.put(email, user);
        return user;
    }

    public static User login(String email, String password) throws Exception
    {
        User user = userEmailVsUser.get(email);
        String actualPassword = userIdVsPassword.get(user.getId());
        if(password.equals(actualPassword))
        {
            userList.put(user.getId(), user);
            return user;
        }
        throw new Exception("Check your username or password.");
    }
}

class Group
{
     static Map<Integer, User> group = new HashMap<>();
     private User user;
     public Group(String email, String password)
     {
         try
         {
             user = LoginManager.login(email, password);
         }
         catch(Exception ex)
         {
             System.out.println(ex.getMessage());
         }

         if(LoginManager.userList.containsKey(user.getId()))
         {
             group.put(user.getId(), user);
         }
     }
}


