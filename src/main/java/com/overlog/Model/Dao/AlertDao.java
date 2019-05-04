package com.overlog.Model.Dao;

import com.overlog.Model.Alert;

import java.util.List;

public interface AlertDao {

    public List<Alert> getAlert(long userid , String type);
    public void deleteAlert(int id);
}
