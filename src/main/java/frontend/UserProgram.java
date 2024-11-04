package frontend;

import controller.UserController;
import repository.IUserRepository;
import repository.UserRepository;
import service.IUserService;
import service.UserService;

public class UserProgram {
    public static void main(String[] args) {
        IUserRepository repository = new UserRepository();
        IUserService service = new UserService(repository);
        UserController controller = new UserController(service);
        UserFunction function = new UserFunction(controller);
        function.showMenu();
    }
}