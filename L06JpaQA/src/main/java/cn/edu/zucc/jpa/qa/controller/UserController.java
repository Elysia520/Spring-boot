package cn.edu.zucc.jpa.qa.controller;

import cn.edu.zucc.jpa.qa.entity.UserEntity;
import cn.edu.zucc.jpa.qa.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "*")
@RestController
public class UserController{
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @ResponseBody
    @PostMapping ("/user")
    public UserEntity test(@RequestBody Map<String, String> body){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userRepository.findAll();
        UserEntity user = new UserEntity();
        user.setName(body.get("username"));
        user.setPassword(body.get("password"));
        for(UserEntity u:users) {
            if(u.getName().equals(user.getName()) && u.getPassword().equals(user.getPassword())) return u;
        }
        System.out.println(user.getName());
        user.setType("null");
        return user;
    }


    @PostMapping ("/checkUser")
    public Boolean checkUser(@RequestBody Map<String, String> body){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userRepository.findAll();
        UserEntity user = new UserEntity();
        user.setName(body.get("username"));
        for(UserEntity u:users) {
            if(u.getName().equals(user.getName())) return true;
        }
        return false;
    }

    @PostMapping ("/createUser")
    public Boolean createUser(@RequestBody Map<String, String> body){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userRepository.findAll();
        UserEntity user = new UserEntity();
        user.setName(body.get("username"));
        user.setPassword(body.get("password"));
        user.setType("visitor");
        user.setImg(body.get("pic").getBytes());
        userRepository.save(user);
        System.out.println(user.getName());
        return true;
    }

    @GetMapping ("/user")
    public UserEntity getUser(@RequestParam("id") int id){
        System.out.println(id);
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userRepository.findAll();
        UserEntity user = new UserEntity();
        for(UserEntity u:users) {
            if(u.getId()==id) {
                System.out.println(u.getImg());
                System.out.println(id);
                return u;
            }
        }
        return null;
    }

    @GetMapping ("/userlist")
    public List<UserEntity> getUserList(){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users = userRepository.findAll();
        return users;
    }

    @DeleteMapping("/user")
    public String deleteEmployee(@RequestParam("id") int id) {
        UserEntity user = new UserEntity();
        System.out.println(id);
        if(userRepository.findById(id)!=null) {
            userRepository.deleteById(id);
            return "success";
        }
        return "no such user";
    }
    @PatchMapping("/user")
    public UserEntity updateUser(@RequestParam("id") int id) {
        UserEntity user = userRepository.findById(id);
        user.setType("admin");
        userRepository.deleteById(id);
        userRepository.save(user);
        return user;
    }

    @PatchMapping("/pic")
    public UserEntity updateUserPic(@RequestParam("id") int id,@RequestBody Map<String, String> body) {

        userRepository.updatePicById(id,body.get("pic").getBytes());
        UserEntity user = userRepository.findById(id);
        return user;
    }

    @GetMapping("/isAdmin")
    public boolean isAdmin(@RequestParam("id") int id) {
        UserEntity user = userRepository.findById(id);
        if(user.getType() == "admin"){
            return true;
        };
        return false;
    }
}