package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage{

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbURL, String dbUserName, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbURL, dbUserName, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute(ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        }, "UPDATE resume SET full_name = ? WHERE uuid = ?");
    }

    @Override
    public void save(Resume r) {
        /* runtimeexception possible*/ sqlHelper.execute(ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        }, "INSERT INTO resume (uuid, full_name) VALUES (?,?)");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        }, "SELECT * FROM resume r WHERE r.uuid =?");
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute(ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        }, "DELETE FROM resume WHERE uuid = ?");
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute(ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return resumes;
        }, "SELECT * FROM resume ORDER BY full_name, uuid");
    }

    @Override
    public int size() {
        return sqlHelper.execute(st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        }, "SELECT count(*) FROM resume");
    }
}
