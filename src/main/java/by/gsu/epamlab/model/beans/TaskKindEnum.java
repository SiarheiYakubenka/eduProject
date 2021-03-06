package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.command.ifaces.ActionCommand;
import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.DAOFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public enum TaskKindEnum {
    TODAY {
        @Override
        public List<Task> getList(HttpServletRequest request) {
            return getList(request, Constants.TODAY);
        }
    },
    TOMORROW {
        @Override
        public List<Task> getList(HttpServletRequest request) {
            return getList(request, Constants.TOMORROW);
        }
    },
    SOMEDAY{
        @Override
        public List<Task> getList(HttpServletRequest request) {
            return getList(request, Constants.SOMEDAY);
        }
    },
    FIXED {
        @Override
        public List<Task> getList(HttpServletRequest request) {
            return getList(request, Constants.FIXED);
        }
    },
    TRASH {
        @Override
        public List<Task> getList(HttpServletRequest request) {
            return getList(request, Constants.TRASH);
        }
    };
    public abstract List<Task> getList(HttpServletRequest request);

    public static List<Task> getList(HttpServletRequest request, String condition) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String userName = user.getName();
        ITaskDAO taskDAO = DAOFactory.getDAO(ITaskDAO.class);
        return taskDAO.getList(userName, condition);
    }

    public static TaskKindEnum getKind(HttpServletRequest request) {
        String kind = request.getParameter(Constants.KEY_KIND);
        return TaskKindEnum.valueOf(kind.toUpperCase());
    }

}
