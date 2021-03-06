package test;

import com.alibaba.fastjson.JSON;
import net.kernal.spiderman.worker.extract.extractor.Extractor;
import net.kernal.spiderman.worker.extract.extractor.impl.HtmlCleanerExtractor;
import net.kernal.spiderman.worker.extract.schema.Model;

/**
 * @author 赖伟威 l.weiwei@163.com
 * @author <a href='http://krbit.github.io'>Krbit</a>
 * @version V0.1.0
 */
public class TestHtmlCleanerExtractor {
    public static void main(String[] args) {
        String html = "<html><title>Hello</title><targets>dfd<target name='vivi' /><target name='linda' /></targets></html>";
        Extractor extractor = new HtmlCleanerExtractor(html);
        Model page = new Model("page");
        page.addField("title").set("xpath", "//title/text()");
        page.addField("target").set("xpath", "//target").set("isAutoExtractAttrs", true).set("isArray", true);
        extractor.addModel(page);
        extractor.extract(new Extractor.Callback() {
            public void onModelExtracted(ModelEntry entry) {
                System.out.println(entry.getModel().getName() + "->\r\n" + JSON.toJSONString(entry.getFields(), true) + "\r\n\r\n");
            }

            public void onFieldExtracted(FieldEntry entry) {
            }
        });
    }
}
