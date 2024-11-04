import controller.UserController;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IUserRepository;
import repository.UserRepository;
import service.IUserService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserProgramTest {
    private IUserRepository repository;
    private UserController controller;

    @BeforeEach
    public void setUp() throws SQLException {
        // Khởi tạo tất cả các phụ thuộc trước mỗi bài kiểm tra
        repository = new UserRepository();
        IUserService service = new UserService(repository);
        controller = new UserController(service);

        // Tùy chọn đặt lại hoặc khởi tạo cơ sở dữ liệu kiểm tra ở đây
        // Ví dụ: repository.resetDatabase();
    }

    @Test
    public void testSaveAndRetrieveUser() throws SQLException {
        User user = new User();
        user.setFullName("Nguyễn Văn Khoa");
        user.setEmail("khoa.nv@gmail.com");
        user.setPassword("123456Q");
        user.setRole("EMPLOYEE");  // Thiết lập vai trò bằng chuỗi
        user.setProSkill("Java");
        user.setExpInYear(0);  // Có thể đặt giá trị mặc định cho kinh nghiệm năm

        // Lưu người dùng qua controller
        controller.saveUser(user);  // Giả sử phương thức 'create' tồn tại trong controller

        // Lấy người dùng từ repository để xác minh
        User retrievedUser = repository.findByEmailAndPassword("khoa.nv@gmail.com", "123456Q");
        assertNotNull(retrievedUser);
        assertEquals(user.getFullName(), retrievedUser.getFullName());
        assertEquals(user.getEmail(), retrievedUser.getEmail());
        assertEquals(user.getProSkill(), retrievedUser.getProSkill());
        assertEquals(user.getRole(), retrievedUser.getRole());
    }

    @Test
    public void testDeleteUser() throws SQLException {
        User user = new User();
        user.setFullName("Jane Doe");
        user.setEmail("jane@example.com");
        user.setPassword("123456Q");
        user.setRole("ADMIN");  // Thiết lập vai trò cho người dùng
        user.setProSkill(null);  // Tùy chọn
        user.setExpInYear(0);  // Đặt số năm kinh nghiệm

        controller.saveUser(user);  // Lưu người dùng

        // Lấy ID người dùng để xóa
        User savedUser = repository.findByEmailAndPassword("jane@example.com", "123456Q");
        controller.deleteById(savedUser.getId());

        // Kiểm tra xem người dùng đã bị xóa chưa
        User deletedUser = repository.findByEmailAndPassword("jane@example.com", "123456Q");
        assertNull(deletedUser);
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        // Lưu nhiều người dùng qua controller
        User user1 = new User();
        user1.setFullName("Alice");
        user1.setEmail("alice@example.com");
        user1.setPassword("123456Q");
        user1.setRole("EMPLOYEE");
        user1.setProSkill("C++");
        user1.setExpInYear(2);

        User user2 = new User();
        user2.setFullName("Bob");
        user2.setEmail("bob@example.com");
        user2.setPassword("123456Q");
        user2.setRole("ADMIN");
        user2.setProSkill("Python");
        user2.setExpInYear(3);

        controller.saveUser(user1);
        controller.saveUser(user2);

        // Lấy danh sách người dùng từ controller
        List<User> users = controller.findAll();  // Giả sử phương thức này tồn tại

        // Kiểm tra xem danh sách có chứa các người dùng đã lưu hay không
        assertEquals(5, users.size()); // Điều chỉnh kích thước mong đợi dựa trên dữ liệu ban đầu của bạn
        assertTrue(users.stream().anyMatch(u -> u.getEmail().equals(user1.getEmail())));
        assertTrue(users.stream().anyMatch(u -> u.getEmail().equals(user2.getEmail())));
    }

    @Test
    public void testSaveUserWithMissingName() {
        User user = new User();
        user.setFullName(null); // Thiếu tên
        user.setEmail("missingname@example.com");
        user.setPassword("123456Q");
        user.setRole("EMPLOYEE");  // Vai trò mặc định
        user.setProSkill(null);  // Tùy chọn
        user.setExpInYear(0);  // Tùy chọn

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            controller.saveUser(user); // Cố gắng lưu người dùng không hợp lệ
        });

        assertEquals("Tên người dùng không thể để trống", exception.getMessage());
    }
}
