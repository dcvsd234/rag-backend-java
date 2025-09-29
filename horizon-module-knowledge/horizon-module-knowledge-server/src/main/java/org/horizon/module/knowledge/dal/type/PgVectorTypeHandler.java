package org.horizon.module.knowledge.dal.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.postgresql.util.PGobject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis TypeHandler，用于 pgvector 列与 Java float[] 的映射
 */
public class PgVectorTypeHandler extends BaseTypeHandler<float[]> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, float[] parameter, JdbcType jdbcType)
            throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int idx = 0; idx < parameter.length; idx++) {
            if (idx > 0) sb.append(',');
            sb.append(parameter[idx]);
        }
        sb.append(']');

        PGobject obj = new PGobject();
        obj.setType("vector"); // pgvector 类型
        obj.setValue(sb.toString());

        ps.setObject(i, obj);
    }

    @Override
    public float[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null; // 如果需要从 DB 读出 vector 转 float[]，可以在这里实现
    }

    @Override
    public float[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public float[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}