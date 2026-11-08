package kr.go.egov.pungdong.domain.menu;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class MenuMapperXmlTest {
    @Test void loadsCommonMapperStatementsAndBindsParameters() throws Exception {
        Configuration configuration = new Configuration();
        String resource = "mybatis/mapper/menu/MenuMapper.xml";
        try (var stream = new ClassPathResource(resource).getInputStream()) {
            new XMLMapperBuilder(stream, configuration, resource, configuration.getSqlFragments()).parse();
        }
        for (String id : new String[]{"selectMenuList", "selectMenu", "selectMaxSortOrder", "countChildren",
                "countMenuKey", "insertMenu", "updateMenu", "deleteMenu"}) {
            assertTrue(configuration.hasStatement("CmsMenu." + id), id);
        }
        var sql = configuration.getMappedStatement("CmsMenu.countMenuKey")
            .getBoundSql(Map.of("menuKey", "test", "menuId", 1L));
        assertEquals(2, sql.getParameterMappings().size());
        assertFalse(sql.getSql().contains("test"));
    }
}
