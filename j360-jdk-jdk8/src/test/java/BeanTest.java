import me.j360.jdk.jdk8.stream.UserFullInfoDTO;
import me.j360.jdk.jdk8.stream.UserMobileDTO;
import me.j360.jdk.jdk8.stream.UserNameDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Package: PACKAGE_NAME
 * User: min_xu
 * Date: 2017/6/22 下午5:59
 * 说明：模拟分批获取对象再二次组合,可以使用异步线程二次调用
 */

public class BeanTest {


    @org.junit.Test
    public void beanTest() {

        UserFullInfoDTO dto1 = new UserFullInfoDTO();
        UserFullInfoDTO dto2 = new UserFullInfoDTO();
        UserFullInfoDTO dto3 = new UserFullInfoDTO();
        dto1.setId(1L);
        dto2.setId(2L);
        dto3.setId(3L);

        List<UserFullInfoDTO> list = new ArrayList<>();
        list.add(dto1);
        list.add(dto2);
        list.add(dto3);

        UserMobileDTO mobileDTO1 = new UserMobileDTO();
        UserMobileDTO mobileDTO2 = new UserMobileDTO();
        UserMobileDTO mobileDTO3 = new UserMobileDTO();

        mobileDTO1.setId(1L);
        mobileDTO1.setMobile("1");
        mobileDTO2.setId(2L);
        mobileDTO2.setMobile("2");
        mobileDTO3.setId(3L);
        mobileDTO3.setMobile("3");

        List<UserMobileDTO> list2 = new ArrayList<>();
        list2.add(mobileDTO1);
        list2.add(mobileDTO2);
        list2.add(mobileDTO3);

        UserNameDTO nameDTO1 = new UserNameDTO();
        UserNameDTO nameDTO2 = new UserNameDTO();
        UserNameDTO nameDTO3 = new UserNameDTO();

        nameDTO1.setId(1L);
        nameDTO1.setName("1");
        nameDTO2.setId(2L);
        nameDTO2.setName("2");
        nameDTO3.setId(3L);
        nameDTO3.setName("3");

        List<UserNameDTO> list3 = new ArrayList<>();
        list3.add(nameDTO1);
        list3.add(nameDTO2);
        list3.add(nameDTO3);

        Map<Long,UserMobileDTO> map2 = list2.stream().collect(
                Collectors.toMap(UserMobileDTO::getId,(p) -> p));


        Map<Long,UserNameDTO> map3 = list3.stream().collect(
                Collectors.toMap(UserNameDTO::getId,(p) -> p));


        list.stream().forEach(p -> {
            UserMobileDTO mobileDTO = map2.get(p.getId());
            UserNameDTO nameDTO = map3.get(p.getId());

            p.setName(nameDTO.getName());
            p.setMobile(mobileDTO.getMobile());

        });





    }

}
