package frontend;

import controller.UserController;
import entity.User;
import lombok.AllArgsConstructor;
import util.ScannerUtil;

import java.util.List;

@AllArgsConstructor
public class UserFunction {
    private UserController controller;

    public void showMenu() {
        while (true) {
            System.out.println("1. Đăng nhập");
            System.out.println("2. Hiển thị danh sách user");
            System.out.println("3. Tìm kiếm user theo id");
            System.out.println("4. Thoát chương trình");
            System.out.println("Mời bạn chọn chức năng:");
            int menu = ScannerUtil.inputInt();
            if (menu == 1) {
                findByEmailAndPassword();
            } else if (menu == 2) {
                findAll();
            } else if (menu == 3) {
                findById();
            } else if (menu == 4) {
                return;
            } else {
                System.err.println("Yêu cầu chọn đúng năng");
            }
        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("1. Đăng xuất");
            System.out.println("2. Hiển thị danh sách user");
            System.out.println("3. Tìm kiếm user theo id");
            System.out.println("4. Tạo user");
            System.out.println("5. Xóa user theo id");
            System.out.println("Mời bạn chọn chức năng:");
            int menu = ScannerUtil.inputInt();
            if (menu == 1) {
                return;
            } else if (menu == 2) {
                findAll();
            } else if (menu == 3) {
                findById();
            } else if (menu == 4) {
                create();
            } else if (menu == 5) {
                deleteById();
            } else {
                System.err.println("Yêu cầu chọn đúng năng");
            }
        }
    }

    private void showEmployeeMenu() {
        while (true) {
            System.out.println("1. Đăng xuất");
            System.out.println("2. Hiển thị danh sách user");
            System.out.println("3. Tìm kiếm user theo id");
            System.out.println("Mời bạn chọn chức năng:");
            int menu = ScannerUtil.inputInt();
            if (menu == 1) {
                return;
            } else if (menu == 2) {
                findAll();
            } else if (menu == 3) {
                findById();
            } else {
                System.err.println("Yêu cầu chọn đúng năng");
            }
        }
    }

    private void findAll() {
        List<User> users = controller.findAll();
        System.out.println("+------+--------------------+--------------------+");
        System.out.printf("| %-4s | %-18s | %-18s |%n", "ID", "FULL NAME", "EMAIL");
        System.out.println("+------+--------------------+--------------------+");
        if (users.isEmpty()) {
            System.out.printf("| %-4s | %-18s | %-18s |%n", "NULL", "NULL", "NULL");
            System.out.println("+------+--------------------+--------------------+");
        } else {
            for (User user : users) {
                System.out.printf(
                        "| %-4s | %-18s | %-18s |%n",
                        user.getId(), user.getFullName(), user.getEmail()
                );
                System.out.println("+------+--------------------+--------------------+");
            }
        }
    }

    private void findById() {
        System.out.println("Mời bạn nhập vào id cần tìm:");
        int id = ScannerUtil.inputInt();
        User user = controller.findById(id);
        System.out.println("+------+--------------------+--------------------+");
        System.out.printf("| %-4s | %-18s | %-18s |%n", "ID", "FULL NAME", "EMAIL");
        System.out.println("+------+--------------------+--------------------+");
        if (user == null) {
            System.out.printf("| %-4s | %-18s | %-18s |%n", "NULL", "NULL", "NULL");
            System.out.println("+------+--------------------+--------------------+");
        } else {
            System.out.printf(
                    "| %-4s | %-18s | %-18s |%n",
                    user.getId(), user.getFullName(), user.getEmail()
            );
            System.out.println("+------+--------------------+--------------------+");
        }
    }

    private void findByEmailAndPassword() {
        System.out.println("Mời bạn nhập vào email:");
        String email = ScannerUtil.inputEmail();
        System.out.println("Mời bạn nhập vào password:");
        String password = ScannerUtil.inputPassword();
        User user = controller.findByEmailAndPassword(email, password);
        if (user == null) {
            System.err.println("Đăng nhập thất bại");
        } else {
            System.out.printf(
                    "Đăng nhập thành công: %s - %s%n",
                    user.getFullName(), user.getRole()
            );
            if (user.getRole() == User.Role.ADMIN) {
                showAdminMenu();
            } else if (user.getRole() == User.Role.EMPLOYEE) {
                showEmployeeMenu();
            }
        }
    }

    private void create() {
        System.out.println("Mời bạn nhập vào full name:");
        String fullName = ScannerUtil.inputFullName();
        System.out.println("Mời bạn nhập vào email:");
        String email = ScannerUtil.inputEmail();
        int created = controller.create(fullName, email);
        System.out.println("Tạo thành công " + created + " user");
    }

    private void deleteById() {
        System.out.println("Mời bạn nhập vào id:");
        int id = ScannerUtil.inputInt();
        int deleted = controller.deleteById(id);
        System.out.println("Xóa thành công " + deleted + " user");
    }
}