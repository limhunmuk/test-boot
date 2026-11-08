package kr.go.egov.pungdong.common.mapper;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

/** SQL IDs are selected by server-side services, never supplied by HTTP clients. */
@Repository
public class CommonMapper {
    private final SqlSessionTemplate session;

    public CommonMapper(SqlSessionTemplate session) { this.session = session; }

    public <T> T selectOne(String statement, Object parameter) { return session.selectOne(statement, parameter); }
    public <T> List<T> selectList(String statement, Object parameter) { return session.selectList(statement, parameter); }
    public int insert(String statement, Object parameter) { return session.insert(statement, parameter); }
    public int update(String statement, Object parameter) { return session.update(statement, parameter); }
    public int delete(String statement, Object parameter) { return session.delete(statement, parameter); }
}
