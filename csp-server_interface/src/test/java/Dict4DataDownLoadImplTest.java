
import com.lmbx.csp.data.conf.domain.CspConfCheckin;
import com.lmbx.csp.data.conf.domain.CspConfCheckinPerson;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinMapper;
import com.lmbx.csp.data.conf.mapper.CspConfCheckinPersonMapper;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wanghongwei
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.lmbx.csp.InterfaceServerApplication.class})
public class Dict4DataDownLoadImplTest {

    @Autowired
    private CspConfCheckinPersonMapper mycspConfCheckinPersonMapper;

    @Autowired
    private CspConfCheckinMapper cspConfCheckinMapper;

    @Test
    public void testDict4DataDownLoad() {
        int b = 1010;
        CspConfCheckin checkin = new CspConfCheckin();
        CspConfCheckinPerson person = new CspConfCheckinPerson();
        for (int i = 0; i < b; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            checkin.setConfId("ae86d0203b874448a40df1b952e9d13f");
            checkin.setId(uuid);
            person.setId(uuid);
            person.setCreateBy("admin");
            person.setCheckinId(uuid);
            cspConfCheckinMapper.insert(checkin);
            mycspConfCheckinPersonMapper.insert(person);
        }

    }

}
