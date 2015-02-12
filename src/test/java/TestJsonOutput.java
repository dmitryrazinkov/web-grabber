import grub.app.Config;
import grub.entities.StringScriptOutput;
import grub.withCasper.CasperAccessor;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Config.class)
public class TestJsonOutput {
    @Autowired
    CasperAccessor casperAccessor;

    @Test
    public void testJSON() throws JSONException {
        StringScriptOutput result = null;
        try {
            result = casperAccessor.execute(ResourceUtils.getFile("classpath:casperJs/vk.js")
                    .getPath(), "email=asdewqzxcv@mail.ru\npass=2222demon2441\npage=https://vk.com/best_pikachu");
        } catch (FileNotFoundException e) {
        }
        JSONObject obj = new JSONObject(result.getStringResult());

        System.out.println(obj.getString("result"));
    }

}
