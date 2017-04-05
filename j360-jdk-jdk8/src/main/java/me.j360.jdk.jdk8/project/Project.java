package me.j360.jdk.jdk8.project;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Package: me.j360.jdk.jdk8.project
 * User: min_xu
 * Date: 2017/4/5 下午1:45
 * 说明：
 */
public class Project {

    private ServiceTemplate serviceTemplate;

    private UserService userService;


    public User getUserById() {
        return null;
    }


    /**
     * map和foreach的区别，当我们只是操作list元素内部的对象时，可以用foreach。
        当我们生成一个新的对象的时候，使用map会更好。
     * @param type
     * @return
     */
    public List<User> listUsers(String type) {
        return userService.listUserByType(type).stream().map(user -> userService.getUserById(user.getId())).collect(Collectors.toList());
    }

}
