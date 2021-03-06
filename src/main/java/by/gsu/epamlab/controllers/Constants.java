package by.gsu.epamlab.controllers;

public class Constants {
    public static final String NAME_CLASSES_ROOT = "WEB-INF\\classes\\";
    public static final String NAME_PROJECT_ROOT = "/";
    public static final String KEY_USER = "user";
    public static final String JUMP_LOGIN = "/WEB-INF/views/login.jsp";
    public static final String JUMP_INDEX = "/index.jsp";
    public static final String JUMP_REG = "/WEB-INF/views/reg.jsp";
    public static final String JUMP_TASKS = "/WEB-INF/views/tasks.jsp";
    public static final String KEY_ERROR_MESSAGE = "errorMessage";
    public static final String KEY_EMPTY = "";
    public static final String REG = "/reg?errorMessage=";
    public static final String ERROR_NULL = "Login or password is missing.";
    public static final String ERROR_EMPTY = "Login or password is empty.";
    public static final String ERROR_PASSWORD = "Wrong password.";
    public static final String ERROR_PASSWORDS = "Passwords do not match.";
    public static final String ERROR_SOURCE = "Data source proccessing problems";
    public static final String ERROR_EXIST = "Login already exist";
    public static final String JUMP_WELCOME = "/welcome.html";
    public static final String FIXED = "isFixed=true";
    public static final String TODAY = "completionsDate<=CURDATE() AND isFixed=false AND  isDelMarked=false";
    public static final String TOMORROW = "completionsDate=(CURDATE()+ interval 1 DAY)  " +
            "AND isFixed=false AND  isDelMarked=false;";
    public static final String SOMEDAY = "completionsDate NOT IN (CURDATE(), CURDATE()+ interval 1 DAY)";
    public static final String TRASH = "isDelMarked=true";
    public static final String KEY_KIND = "kind";
    public static final String TYPE_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
}
