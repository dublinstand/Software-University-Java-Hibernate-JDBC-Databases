package _02_jdbc_advanced.mini_hibernate.orm;

import _02_jdbc_advanced.mini_hibernate.orm.interfaces.DBContext;
import _02_jdbc_advanced.mini_hibernate.persistance.Column;
import _02_jdbc_advanced.mini_hibernate.persistance.Entity;
import _02_jdbc_advanced.mini_hibernate.persistance.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EntityManager implements DBContext {

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <E> boolean persist(E entity) throws SQLException, IllegalAccessException {
        Field primary = this.getId(entity.getClass());

        //in case the field we have is a primary key
        primary.setAccessible(true);

        //will try to create the table if it does not exist
        this.doCreate(entity, primary);

        //get the value of the primary key
        Object value = primary.get(entity);

        //if the object value is null, will insert it into the new table
        if (value == null || (Long)value <= 0) {
            return this.doInsert(entity, primary);
        }
        else{
            //if does not insert it will update it
            this.doUpdate(entity, primary);
        }

        return true;
    }

    private <E> boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String sqlUpdate = "UPDATE " + tableName + " SET ";
        String where = "WHERE ";

        //we put all fields in the array for the Class we use
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            //if the field is primary key we put it in the WHERE clause
            if (field.getName().equals(primary.getName())) {
                String primaryColumnName = this.getFieldName(primary);
                Long primaryColumnValue = (Long) primary.get(entity);
                where += "`" + primaryColumnName + "`" + " = '" + primaryColumnValue + "'";
                continue;
            }

            //we get the value of the field from them entity we passed
            Object value = field.get(entity);

            //if the value is of instance of Date we need to change the format for SQL
            if (value instanceof Date) {
                sqlUpdate += "`" +this.getFieldName(field) + "` = "
                        + "'" + new SimpleDateFormat("yyyyMMdd").format(value) + "'";
            } else {
                sqlUpdate += "`" + this.getFieldName(field) + "` = "
                        + "'" + value + "'";
            }

            if (i < fields.length - 1) {
                sqlUpdate += ", ";
            }
        }

        sqlUpdate += where;

        return this.connection.prepareStatement(sqlUpdate).execute();
    }

    private <E> boolean doInsert(E entity, Field primary) throws IllegalAccessException, SQLException {
        String tableName = this.getTableName(entity.getClass());
        String sqlInsert = "INSERT INTO " + tableName + "(";

        String columns = "";
        String values = "";
        Field[] fields = entity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);

            if (!field.getName().equals(primary.getName())) {
                columns += "`" + this.getFieldName(field) + "`";

                Object value = field.get(entity);
                if (value instanceof Date) {
                    values += "'" + new SimpleDateFormat("yyyy-MM-dd").format(value) + "'";
                } else {
                    values += "'" + value + "'";
                }

                if (i < fields.length - 1) {
                    columns += ", ";
                    values += ", ";
                }
            }
        }

        sqlInsert += columns + ") "
                + "VALUES(" + values + ")";

        return this.connection.prepareStatement(sqlInsert).execute();
    }

    @Override
    public <E> List<E> find(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException {
        return this.find(table, null);
    }

    @Override
    public <E> List<E> find(Class<E> table, String where) throws IllegalAccessException, InstantiationException, SQLException {
        String tableName = this.getTableName(table);
        String sqlSelect = "SELECT * FROM " + tableName +" WHERE 1=1";
        if(where != null){
            sqlSelect += " AND " + where;
        }

        List<E> entities = new ArrayList<>();

        ResultSet rs = connection.prepareStatement(sqlSelect).executeQuery();
        while (rs.next()){
            E entity = table.newInstance();
            this.fillValues(entity,rs);
            entities.add(entity);
        }

        return Collections.unmodifiableList(entities);
    }

    private <E> void fillValues (E entity, ResultSet rs) throws SQLException, IllegalAccessException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = this.getFieldName(field);
            Object value = Mapper.getFieldValue(field, fieldName, rs);
            field.set(entity, value);
        }
    }

    @Override
    public <E> E findFirst(Class<E> table) throws IllegalAccessException, SQLException, InstantiationException {
        return this.findFirst(table, null);
    }

    @Override
    public <E> E findFirst(Class<E> table, String where) throws IllegalAccessException, InstantiationException, SQLException {
        String tableName = this.getTableName(table);
        String sqlSelect = "SELECT * FROM " + tableName +" WHERE 1=1";
        if(where != null){
            sqlSelect += " AND " + where;
        }

        ResultSet rs = connection.prepareStatement(sqlSelect).executeQuery();
        rs.first();

        E entity = table.newInstance();

        this.fillValues(entity,rs);

        return entity;
    }

    static class Mapper{
        public static Object getFieldValue(Field field,String fieldName, ResultSet rs) throws SQLException {
            if(field.getType() == Integer.class || field.getType() == int.class){
                return rs.getInt(fieldName);
            } else if(field.getType() == Long.class || field.getType() == long.class) {
                return rs.getLong(fieldName);
            }else if(field.getType() == String.class) {
                return rs.getString(fieldName);
            }else if(field.getType() == Date.class) {
                return rs.getDate(fieldName);
            }

            return null;
        }
    }

    private <E> String getTableName(Class<E> entity) {
        String tableName = "";

        if (entity.isAnnotationPresent(Entity.class)) {
            Entity entityAnnotation = entity.getAnnotation(Entity.class);
            tableName = entityAnnotation.name();
        }

        if (tableName.equals("")) {
            tableName = entity.getSimpleName();
        }

        return tableName;
    }

    private String getFieldName(Field field) {
        String fieldName = "";

        if (field.isAnnotationPresent(Column.class)) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            fieldName = columnAnnotation.name();
        }

        if (fieldName.equals("")) {
            fieldName = field.getName();
        }

        return fieldName;
    }

    private Field getId(Class c) {
        return Arrays.stream(c.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new IllegalAccessError(""));
    }

    private <E> boolean doCreate(E entity, Field primary) throws SQLException {
        String tableName = this.getTableName(entity.getClass());

        //have the table name based on the name of the class (or the entity annotation)
        String sqlCreate = "CREATE TABLE IF NOT EXISTS " + tableName + "( ";

        String columns = "";

        //we put all fields in the array for the Class we use
        Field[] fields = entity.getClass().getDeclaredFields();

        //loop through all fields in the array
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];

            //in order to be able to access the private fields
            field.setAccessible(true);

            //in case the field we have is a primary key
            if (field.getName().equals(primary.getName())) {
                columns += "`" + this.getFieldName(field) + "` "
                        + " BIGINT " +
                        " PRIMARY KEY AUTO_INCREMENT ";
            } else {
                columns += "`" + this.getFieldName(field) + "` "
                        + this.getDatabaseType(field);
            }

            //for all but last column we add a comma
            if (i < fields.length - 1) {
                columns += ", ";
            }
        }

        sqlCreate += columns + ")";

        return connection.prepareStatement(sqlCreate).execute();
    }

    private String getDatabaseType(Field field) {

        //we get the type of the field we pass and put it to lower case
        switch (field.getType().getSimpleName().toLowerCase()) {
            case "int":
                return "INT";
            case "string":
                return "VARCHAR(50)";
            case "long":
                return "LONG";
            case "date":
                return "DATE";
        }

        return null;
    }
}
