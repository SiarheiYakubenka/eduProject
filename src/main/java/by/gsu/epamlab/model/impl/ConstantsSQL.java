package by.gsu.epamlab.model.impl;

public class ConstantsSQL {
    public static final String SELECT_USER = "select  password, roles.role" +
            " from users join roles on users.roleId = roles.id" +
            " where login=?";
    public static final int INDEX_ONE = 1;
    public static final int INDEX_TWO = 2;
    public static final int INDEX_THREE = 3;
    public static final int INDEX_FOUR = 4;
    public static final int INDEX_FIVE = 5;
    public static final int INDEX_SIX = 6;
    public static final String PARAM_ROLE = "role";
    public static final String PARAM_PASS = "password";
    public static final String ADD_USER = "insert into users (login, password) values (?, ?)";
    public static final String USER_ID = "select id from users where login=?";
    public static final String SELECT_TASKS = "select description, completionsDate from '?'_tasks where '?'";
    public static final String CREATE_TASKS =  "create table ?_tasks (" +
            "  id int not null auto_increment," +
            "  description varchar(255) not null," +
            "  completionsDate datetime not null ," +
            "  isFixed bool not null default false," +
            "  isDelMarked bool not null default false," +
            "  fileId int(4)," +
            "  primary key (id)," +
            "  foreign key (fileId) references binary_data (id)" +
            ");";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE = "completionsDate";
    public static final String KEY_IS_FIXED = "isFixed";
    public static final String KEY_IS_DEL = "isDelMarked";
    public static final String KEY_ID = "id";
    public static final String ADD_TASK = "insert into ?_tasks (description, completionsDate)" +
            " values (?, ?);";
    public static final String DELETE_TASK = "delete from ?_tasks" +
            " where description=? and completionsDate=?;";
    public static final String TASK_ID = "select id from ?_tasks" +
            " where description=? and completionsDate=?;";
    public static final String UPDATE_TASK = "update ?_tasks set" +
            "  description=?," +
            "  completionsDate=?," +
            "  isFixed=?," +
            "  isDelMarked=?" +
            "  where id=?";
}
