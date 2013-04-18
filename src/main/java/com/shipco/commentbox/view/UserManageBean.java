/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shipco.commentbox.view;

import com.shipco.commentbox.model.User;
import com.shipco.commentbox.service.UserService;
import com.shipco.commentbox.utils.EmailUtils;
import com.shipco.commentbox.utils.SpringUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author wjirawong
 */
@ManagedBean
@SessionScoped
public class UserManageBean implements Serializable {

    private UserService userService;
    private List<User> users;
    private String username;
    private String email;

    @PostConstruct
    public void init() {
        userService = SpringUtils.getBean(UserService.class);
        users = userService.getAllUser();

    }

    public void addNewUser() {
        User addNewUser = userService.addNewUser(username, email);
        if (addNewUser != null) {
            users = userService.getAllUser();
            StringBuilder builder = new StringBuilder();
            builder.append("Hi,<br>");
            builder.append("you have invite for <b>Comment Box Control Panel</b><br><br>");
            builder.append("<u><b>your account detail</b></u><br>");
            builder.append("URL : <a href='http://172.16.73.50:8080/commentbox/login.xhtml'>http://172.16.73.50:8080/commentbox/login.xhtml</a><br>");
            builder.append("Username : <b>");
            builder.append(addNewUser.getUsername());
            builder.append("</b><br>");
            builder.append("Password : <b>");
            builder.append(addNewUser.getPassword());
            builder.append("</b><br>");
            EmailUtils.sendEmailMessage("Welcome to Comment Box!!!", builder.toString(), email);
        } else {
            popupMessage("Add Failed", "Duplicate username found.");
        }
    }

    private void popupMessage(String summary, String detail) {
        FacesMessage msg = new FacesMessage(summary, detail);
        FacesContext.getCurrentInstance().addMessage("messages", msg);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
