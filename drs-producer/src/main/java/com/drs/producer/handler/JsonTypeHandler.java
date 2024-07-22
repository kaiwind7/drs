package com.drs.producer.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.mass.model.enums.ImageOperationType;
import com.mass.model.image.ImageOptionInfo;
import com.mass.upload.deserializer.ImageOptionInfoMapDeserializer;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class JsonTypeHandler extends BaseTypeHandler<Map<ImageOperationType, ImageOptionInfo>> {

    private final ObjectMapper objectMapper;

    public JsonTypeHandler () {
        objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Map.class, new ImageOptionInfoMapDeserializer());
        objectMapper.registerModule(simpleModule);
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<ImageOperationType, ImageOptionInfo> parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (IOException e) {
            throw new SQLException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<ImageOperationType, ImageOptionInfo> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getString(columnName));
    }

    @Override
    public Map<ImageOperationType, ImageOptionInfo> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getString(columnIndex));
    }

    @Override
    public Map<ImageOperationType, ImageOptionInfo> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getString(columnIndex));
    }

    private Map<ImageOperationType, ImageOptionInfo> parseJson(String json) throws SQLException {
        try {
            if (json == null || json.isEmpty()) return null;
            // Define a type reference for deserialization based on the generic types
            TypeReference<Map<ImageOperationType, ImageOptionInfo>> typeRef = new TypeReference<Map<ImageOperationType, ImageOptionInfo>>() {};
            return objectMapper.readValue(json, typeRef);
        } catch (IOException e) {
            throw new SQLException("Error parsing JSON to map", e);
        }
    }
}

