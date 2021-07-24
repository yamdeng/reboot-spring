package reboot.spring.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import reboot.spring.bean.vo.FirstVo;

public class FirstDao {

    private List<FirstVo> voList = new ArrayList<>();

    public FirstDao() {
        for(int index=0; index<10; index++) {
            voList.add(
                new FirstVo("yamdeng" + (index+1), "yamdeng" + (index+1) + "@gmail.com", "1234", true)
            );
        }
    }

    public void create(FirstVo firstVo) {
        boolean isExistVo = voList.stream().anyMatch(vo -> vo.getLoginId().equals(firstVo.getLoginId()));
        if(isExistVo) {
            System.out.println("already firstVo : " + firstVo);
        } else {
            voList.add(firstVo);
        }
    }

    public void update(FirstVo updateVo) {
        Optional<FirstVo> optionalFirstVo = voList.stream()
                .filter(vo -> vo.getLoginId().equals(updateVo.getLoginId())).findAny();
        if(optionalFirstVo.isPresent()) {
            FirstVo listVo = optionalFirstVo.get();
            listVo.setName(updateVo.getName());
            listVo.setPassword(updateVo.getPassword());
            listVo.setEnabled(updateVo.isEnabled());
        } else {
            System.out.println("not found updateVo : " + updateVo.getLoginId());
        }
    }

    public List<FirstVo> list() {
        return voList;
    }

    public List<FirstVo> searchByName(String name) {
        List<FirstVo> list = voList.stream().filter(vo -> vo.getName().equals(name)).collect(Collectors.toList());
        return list;
    }

    public FirstVo getInfo(String loginId) {
        FirstVo result = null;
        Optional<FirstVo> optionalFirstVo = voList.stream()
            .filter(vo -> vo.getLoginId().equals(loginId)).findAny();
        if(optionalFirstVo.isPresent()) {
            return optionalFirstVo.get();
        }
        return result;
    }

    public void delete(String loginId) {
        boolean removed = voList.removeIf(vo -> vo.getLoginId().equals(loginId));
        if(!removed) {
            System.out.println("remove fail : " + loginId);
        }
    }

    public void initManual() {
        System.out.println("FirstDao initManual");
    }

    public void destroyManual() {
        System.out.println("FirstDao destroyManual");
    }

}
