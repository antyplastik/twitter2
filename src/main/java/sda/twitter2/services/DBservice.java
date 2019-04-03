package sda.twitter2.services;

public interface DBservice <R> {

    boolean create (R r);
    boolean update (R... r);
    boolean isExist(R r);
    R find (R r);
    boolean delete (R r);

}
